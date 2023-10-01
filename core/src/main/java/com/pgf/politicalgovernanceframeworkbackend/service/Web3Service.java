package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.contract.TaxOffice;
import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;

@Service
public class Web3Service {

    @Value("${infura-endpoint}")
    private String INFURA_ENDPOINT;

    @Value("${private-key}")
    private String PRIVATE_KEY;

    private Web3j web3j;

    public Flowable<TaxOffice.PaymentReceivedEventResponse> getPaymentReceivedLogs(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        this.web3j = Web3j.build(new HttpService(INFURA_ENDPOINT));
        String contractAddress = "0x8EaDcAB625d964ebF2b0eD904B81132Fc55560e8";
        TaxOffice contract = TaxOffice.load(contractAddress, web3j, Credentials.create(PRIVATE_KEY), new DefaultGasProvider());
        return contract
                .paymentReceivedEventFlowable(startBlock, endBlock);
    }

    public EthBlock.Block getBlockByHash(String transactionHash) throws IOException {
        return web3j.ethGetBlockByHash(transactionHash, true).send().getBlock();
    }
}
