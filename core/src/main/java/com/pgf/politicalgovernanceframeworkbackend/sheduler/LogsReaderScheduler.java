package com.pgf.politicalgovernanceframeworkbackend.sheduler;

import com.pgf.politicalgovernanceframeworkbackend.contract.TaxOffice;
import com.pgf.politicalgovernanceframeworkbackend.dto.TaxBeneficiaryDetailsDto;
import com.pgf.politicalgovernanceframeworkbackend.entity.pgf.PaymentReceivedEvent;
import com.pgf.politicalgovernanceframeworkbackend.repository.pgf.PaymentReceivedEventRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.impl.TaxBeneficiaryDetailsServiceImpl;
import com.pgf.politicalgovernanceframeworkbackend.service.impl.Web3ServiceImpl;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class LogsReaderScheduler {

    private final Web3ServiceImpl web3Service;
    private final PaymentReceivedEventRepository paymentReceivedEventRepository;
    private final TaxBeneficiaryDetailsServiceImpl taxBeneficiaryDetailsService;

    @Scheduled(fixedRate = 60000)
    public void task() {
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
            Disposable disposable =
                web3Service.getPaymentReceivedLogs(startingBlock, DefaultBlockParameterName.LATEST, contractAddress)
                    .subscribe(
                        getPaymentReceivedEventResponseConsumer(contractAddress),
                        throwable -> log.error(throwable.getMessage())
                    );
            if (disposable.isDisposed()) {
                disposable.dispose();
            }
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
