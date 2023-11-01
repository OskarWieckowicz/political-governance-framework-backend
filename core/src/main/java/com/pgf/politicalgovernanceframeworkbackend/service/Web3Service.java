package com.pgf.politicalgovernanceframeworkbackend.service;

import com.pgf.politicalgovernanceframeworkbackend.contract.TaxOffice;
import io.reactivex.Flowable;
import java.io.IOException;
import java.math.BigInteger;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;

public interface Web3Service {
    Flowable<TaxOffice.PaymentReceivedEventResponse> getPaymentReceivedLogs(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock, String contractAddress);
    EthBlock.Block getBlockByHash(String transactionHash) throws IOException;
    public BigInteger getEthBalance(String address) throws IOException;
}
