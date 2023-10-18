package com.pgf.politicalgovernanceframeworkbackend.sheduler;

import com.pgf.politicalgovernanceframeworkbackend.contract.TaxOffice;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.PaymentReceivedEvent;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.PaymentReceivedEventRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.TaxBeneficiaryDetailsService;
import com.pgf.politicalgovernanceframeworkbackend.service.Web3Service;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogsReaderScheduler {

    private final Web3Service web3Service;
    private final PaymentReceivedEventRepository paymentReceivedEventRepository;
    private final TaxBeneficiaryDetailsService taxBeneficiaryDetailsService;

    @Scheduled(fixedRate = 40000)
    public void task() {
        log.info("LogsReaderScheduler started!");
        List<TaxBeneficiaryDetailsDto> allTaxBeneficiariesDetails =
            taxBeneficiaryDetailsService.getAllTaxBeneficiariesDetails();

        for (TaxBeneficiaryDetailsDto taxBeneficiaryDetailsDto : allTaxBeneficiariesDetails) {

            Optional<PaymentReceivedEvent> latestPaymentReceivedEvent =
                paymentReceivedEventRepository.findTopByContractAddressOrderByTimestampDesc(
                    taxBeneficiaryDetailsDto.getSmartContractAddress());

            DefaultBlockParameter startingBlock = latestPaymentReceivedEvent
                .map(receivedEvent -> DefaultBlockParameter.valueOf(receivedEvent.getBlockNumber().add(BigInteger.ONE)))
                .orElse(DefaultBlockParameterName.EARLIEST);
            String contractAddress = taxBeneficiaryDetailsDto.getSmartContractAddress();
            Disposable subscribe =
                web3Service.getPaymentReceivedLogs(startingBlock, DefaultBlockParameterName.LATEST, contractAddress)
                    .subscribe(getPaymentReceivedEventResponseConsumer(contractAddress));
//        subscribe.dispose();
        }
    }

    @NotNull
    private Consumer<TaxOffice.PaymentReceivedEventResponse> getPaymentReceivedEventResponseConsumer(
        String contractAddress) {
        return event -> {
            PaymentReceivedEvent paymentReceivedEvent = new PaymentReceivedEvent();
            paymentReceivedEvent.setTaxIdentifier(event.taxIdentifier);
            paymentReceivedEvent.setAmount(event.amount);
            paymentReceivedEvent.setBlockNumber(event.log.getBlockNumber());
            paymentReceivedEvent.setTransactionHash(event.log.getTransactionHash());
            paymentReceivedEvent.setContractAddress(contractAddress);
            String blockHash = event.log.getBlockHash();

            EthBlock.Block block = web3Service.getBlockByHash(blockHash);
            paymentReceivedEvent.setTimestamp(block.getTimestamp());
            Optional<PaymentReceivedEvent> existingRecord =
                paymentReceivedEventRepository.findByTransactionHash(event.log.getTransactionHash());
            if (existingRecord.isEmpty()) {
                paymentReceivedEventRepository.save(paymentReceivedEvent);
            }
        };
    }
}
