package com.valaszek.bank.exception;

public class OperationTypeNotFound extends BankException {

  private final Integer id;

  public OperationTypeNotFound(Integer id) {
    super(ErrorCode.OPERATION_TYPE_NOT_FOUND);
    this.id = id;
  }
}
