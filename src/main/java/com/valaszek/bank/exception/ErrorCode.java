package com.valaszek.bank.exception;

public enum ErrorCode {
    INTERNAL,

    ACCOUNT_NOT_FOUND,

    OPERATION_TYPE_NOT_FOUND;

    public String code() {
        return "exceptionCode." + name();
    }
}
