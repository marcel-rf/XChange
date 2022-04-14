package org.knowm.xchange.ftx.service;

import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Wallet;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class FtxAccountInfo extends AccountInfo {

    private final BigDecimal accountTotalBalance;

    public FtxAccountInfo(
            String username, BigDecimal tradingFee, Collection<Wallet> wallets, Date timestamp, BigDecimal accountTotalBalance) {

        super(username, tradingFee, wallets, Collections.emptySet(), timestamp);
        this.accountTotalBalance = accountTotalBalance;
    }

    public BigDecimal getAccountTotalBalance() {
        return accountTotalBalance;
    }
}
