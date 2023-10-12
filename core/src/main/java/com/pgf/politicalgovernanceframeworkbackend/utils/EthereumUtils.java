package com.pgf.politicalgovernanceframeworkbackend.utils;


import java.math.BigDecimal;
import java.math.BigInteger;
import org.web3j.utils.Convert;

public final class EthereumUtils {
    public static BigInteger ethToWei(double ethAmount) {
        BigDecimal ethBigDecimal = BigDecimal.valueOf(ethAmount);
        // Convert ETH to Wei
        BigInteger weiAmount = Convert.toWei(ethBigDecimal, Convert.Unit.ETHER).toBigInteger();
        return weiAmount;
    }

}
