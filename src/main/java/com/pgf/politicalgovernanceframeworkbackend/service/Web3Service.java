package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.model.TaxOffice;
import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
public class Web3Service {

    @Value("${infura-endpoint}")
    private String INFURA_ENDPOINT;

    @Value("${private-key}")
    private String PRIVATE_KEY;

    private Web3j web3j;


    public void getPaymentReceivedLogs() {
        this.web3j = Web3j.build(new HttpService(INFURA_ENDPOINT));
        String contractAddress = "0x8EaDcAB625d964ebF2b0eD904B81132Fc55560e8";
        TaxOffice contract = TaxOffice.load(contractAddress, web3j, Credentials.create(PRIVATE_KEY), new DefaultGasProvider());
        Flowable<TaxOffice.PaymentReceivedEventResponse> paymentReceivedEventResponseFlowable = contract
                .paymentReceivedEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST);
        paymentReceivedEventResponseFlowable.subscribe(log -> {
            System.out.println(log.taxIdentifier);
            System.out.println(log.amount);
        });
    }
}
