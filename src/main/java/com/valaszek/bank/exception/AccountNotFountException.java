package com.valaszek.bank.exception;

import java.math.BigInteger;

public class AccountNotFountException extends BankException {

    private final BigInteger accountId;

    public AccountNotFountException(BigInteger accountId) {
        super(ErrorCode.ACCOUNT_NOT_FOUND);
        this.accountId = accountId;
    }
}
