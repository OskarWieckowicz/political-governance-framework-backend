package com.pgf.politicalgovernanceframeworkbackend.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.0.
 */
@SuppressWarnings("rawtypes")
public class TaxOffice extends Contract {
    public static final String BINARY = "60a060405234801561000f575f80fd5b503373ffffffffffffffffffffffffffffffffffffffff1660808173ffffffffffffffffffffffffffffffffffffffff168152505060805161081161006a5f395f8181610125015281816102b601526102f201526108115ff3fe608060405260043610610033575f3560e01c80632b66d72e146100375780632e1a7d4d146100535780638da5cb5b1461007b575b5f80fd5b610051600480360381019061004c9190610461565b6100a5565b005b34801561005e575f80fd5b50610079600480360381019061007491906104db565b610123565b005b348015610086575f80fd5b5061008f6102f0565b60405161009c9190610545565b60405180910390f35b5f34116100e7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016100de906105de565b60405180910390fd5b7fd78903455e3bfe57a34c1b193ad1751a51ca1b1075f0562c334e3ea6a28148898134604051610118929190610675565b60405180910390a150565b7f000000000000000000000000000000000000000000000000000000000000000073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146101a8576040517f30cd747100000000000000000000000000000000000000000000000000000000815260040160405180910390fd5b478111156101eb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101e2906106ed565b60405180910390fd5b5f3373ffffffffffffffffffffffffffffffffffffffff168260405161021090610738565b5f6040518083038185875af1925050503d805f811461024a576040519150601f19603f3d011682016040523d82523d5f602084013e61024f565b606091505b5050905080610293576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161028a90610796565b60405180910390fd5b7f84511ecc081974f18e7f3e0dcc19db078b55bbd3852ddd0dd85b3aebb7bf94c27f0000000000000000000000000000000000000000000000000000000000000000836040516102e49291906107b4565b60405180910390a15050565b7f000000000000000000000000000000000000000000000000000000000000000081565b5f604051905090565b5f80fd5b5f80fd5b5f80fd5b5f80fd5b5f601f19601f8301169050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b6103738261032d565b810181811067ffffffffffffffff821117156103925761039161033d565b5b80604052505050565b5f6103a4610314565b90506103b0828261036a565b919050565b5f67ffffffffffffffff8211156103cf576103ce61033d565b5b6103d88261032d565b9050602081019050919050565b828183375f83830152505050565b5f610405610400846103b5565b61039b565b90508281526020810184848401111561042157610420610329565b5b61042c8482856103e5565b509392505050565b5f82601f83011261044857610447610325565b5b81356104588482602086016103f3565b91505092915050565b5f602082840312156104765761047561031d565b5b5f82013567ffffffffffffffff81111561049357610492610321565b5b61049f84828501610434565b91505092915050565b5f819050919050565b6104ba816104a8565b81146104c4575f80fd5b50565b5f813590506104d5816104b1565b92915050565b5f602082840312156104f0576104ef61031d565b5b5f6104fd848285016104c7565b91505092915050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f61052f82610506565b9050919050565b61053f81610525565b82525050565b5f6020820190506105585f830184610536565b92915050565b5f82825260208201905092915050565b7f5061796d656e7420616d6f756e74206d757374206265206772656174657220745f8201527f68616e207a65726f000000000000000000000000000000000000000000000000602082015250565b5f6105c860288361055e565b91506105d38261056e565b604082019050919050565b5f6020820190508181035f8301526105f5816105bc565b9050919050565b5f81519050919050565b5f5b83811015610623578082015181840152602081019050610608565b5f8484015250505050565b5f610638826105fc565b610642818561055e565b9350610652818560208601610606565b61065b8161032d565b840191505092915050565b61066f816104a8565b82525050565b5f6040820190508181035f83015261068d818561062e565b905061069c6020830184610666565b9392505050565b7f496e73756666696369656e7420636f6e74726163742062616c616e63650000005f82015250565b5f6106d7601d8361055e565b91506106e2826106a3565b602082019050919050565b5f6020820190508181035f830152610704816106cb565b9050919050565b5f81905092915050565b50565b5f6107235f8361070b565b915061072e82610715565b5f82019050919050565b5f61074282610718565b9150819050919050565b7f5472616e73666572206661696c65642e000000000000000000000000000000005f82015250565b5f61078060108361055e565b915061078b8261074c565b602082019050919050565b5f6020820190508181035f8301526107ad81610774565b9050919050565b5f6040820190506107c75f830185610536565b6107d46020830184610666565b939250505056fea264697066735822122001fbcbde263800d71ff79ee8a305b771014563c9ee8d1f54808460a8603d8d4864736f6c63430008140033";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final Event PAYMENTRECEIVED_EVENT = new Event("PaymentReceived", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PAYMENTWITHDRAWN_EVENT = new Event("PaymentWithdrawn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected TaxOffice(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TaxOffice(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TaxOffice(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TaxOffice(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<PaymentReceivedEventResponse> getPaymentReceivedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAYMENTRECEIVED_EVENT, transactionReceipt);
        ArrayList<PaymentReceivedEventResponse> responses = new ArrayList<PaymentReceivedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PaymentReceivedEventResponse typedResponse = new PaymentReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.taxIdentifier = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PaymentReceivedEventResponse getPaymentReceivedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYMENTRECEIVED_EVENT, log);
        PaymentReceivedEventResponse typedResponse = new PaymentReceivedEventResponse();
        typedResponse.log = log;
        typedResponse.taxIdentifier = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PaymentReceivedEventResponse> paymentReceivedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPaymentReceivedEventFromLog(log));
    }

    public Flowable<PaymentReceivedEventResponse> paymentReceivedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYMENTRECEIVED_EVENT));
        return paymentReceivedEventFlowable(filter);
    }

    public static List<PaymentWithdrawnEventResponse> getPaymentWithdrawnEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PAYMENTWITHDRAWN_EVENT, transactionReceipt);
        ArrayList<PaymentWithdrawnEventResponse> responses = new ArrayList<PaymentWithdrawnEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PaymentWithdrawnEventResponse typedResponse = new PaymentWithdrawnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PaymentWithdrawnEventResponse getPaymentWithdrawnEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PAYMENTWITHDRAWN_EVENT, log);
        PaymentWithdrawnEventResponse typedResponse = new PaymentWithdrawnEventResponse();
        typedResponse.log = log;
        typedResponse.receiver = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PaymentWithdrawnEventResponse> paymentWithdrawnEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPaymentWithdrawnEventFromLog(log));
    }

    public Flowable<PaymentWithdrawnEventResponse> paymentWithdrawnEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAYMENTWITHDRAWN_EVENT));
        return paymentWithdrawnEventFlowable(filter);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pay(String taxIdentifier, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(taxIdentifier)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(BigInteger amount) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TaxOffice load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TaxOffice(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TaxOffice load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TaxOffice(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TaxOffice load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TaxOffice(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TaxOffice load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TaxOffice(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TaxOffice> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TaxOffice.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<TaxOffice> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TaxOffice.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TaxOffice> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TaxOffice.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TaxOffice> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TaxOffice.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class PaymentReceivedEventResponse extends BaseEventResponse {
        public String taxIdentifier;

        public BigInteger amount;
    }

    public static class PaymentWithdrawnEventResponse extends BaseEventResponse {
        public String receiver;

        public BigInteger amount;
    }
}
