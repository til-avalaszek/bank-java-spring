package com.valaszek.bank.exception;

public abstract class BankException extends RuntimeException {

    private final ErrorCode code;

    public BankException(ErrorCode code ) { this.code = code; };

    public ErrorCode getCode() { return code; }
}
