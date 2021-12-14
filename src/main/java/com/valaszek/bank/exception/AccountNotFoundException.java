package com.valaszek.bank.exception;

import java.math.BigInteger;

public class AccountNotFoundException extends BankException {

    private final BigInteger accountId;

    public AccountNotFoundException(BigInteger accountId) {
        super(ErrorCode.ACCOUNT_NOT_FOUND);
        this.accountId = accountId;
    }
}
