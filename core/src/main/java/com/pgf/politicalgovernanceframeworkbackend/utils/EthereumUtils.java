package com.pgf.politicalgovernanceframeworkbackend.utils;


import java.math.BigDecimal;
import java.math.BigInteger;
import org.web3j.utils.Convert;

public final class EthereumUtils {
    public static BigInteger ethToWei(double ethAmount) {
        BigDecimal ethBigDecimal = BigDecimal.valueOf(ethAmount);
        return Convert.toWei(ethBigDecimal, Convert.Unit.ETHER).toBigInteger();
    }

}
