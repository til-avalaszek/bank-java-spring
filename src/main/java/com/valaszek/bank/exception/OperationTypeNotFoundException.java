package com.valaszek.bank.exception;

public class OperationTypeNotFoundException extends BankException {

  private final Integer id;

  public OperationTypeNotFoundException(Integer id) {
    super(ErrorCode.OPERATION_TYPE_NOT_FOUND);
    this.id = id;
  }
}
