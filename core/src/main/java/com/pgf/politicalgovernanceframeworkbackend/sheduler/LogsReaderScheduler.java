package com.pgf.politicalgovernanceframeworkbackend.sheduler;

import com.pgf.politicalgovernanceframeworkbackend.entity.PaymentReceivedEvent;
import com.pgf.politicalgovernanceframeworkbackend.repository.PaymentReceivedEventRepository;
import com.pgf.politicalgovernanceframeworkbackend.service.Web3Service;
import io.reactivex.disposables.Disposable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

//    @Scheduled(fixedRate = 20000)
    public void task() {
        log.info("Scheduled Task executed!");
        Optional<PaymentReceivedEvent> latestPaymentReceivedEvent = paymentReceivedEventRepository.findTopByOrderByIdDesc();
        DefaultBlockParameter startingBlock = latestPaymentReceivedEvent
                .map(receivedEvent -> DefaultBlockParameter.valueOf(receivedEvent.getBlockNumber().add(BigInteger.ONE)))
                .orElse(DefaultBlockParameterName.EARLIEST);
        Disposable subscribe = web3Service.getPaymentReceivedLogs(startingBlock, DefaultBlockParameterName.LATEST).subscribe(event -> {
            PaymentReceivedEvent paymentReceivedEvent = new PaymentReceivedEvent();
            paymentReceivedEvent.setTaxIdentifier(event.taxIdentifier);
            paymentReceivedEvent.setAmount(event.amount);
            paymentReceivedEvent.setBlockNumber(event.log.getBlockNumber());
            paymentReceivedEvent.setTransactionHash(event.log.getTransactionHash());
            String blockHash = event.log.getBlockHash();

            EthBlock.Block block = web3Service.getBlockByHash(blockHash);
            paymentReceivedEvent.setTimestamp(block.getTimestamp());
            paymentReceivedEventRepository.save(paymentReceivedEvent);
        });
//        subscribe.dispose();
    }
}
