package com.valaszek.bank.enums;

import com.valaszek.bank.exception.OperationTypeNotFoundException;

public enum OperationType {
  COMPRA_A_VISTA(1),
  COMPRA_PARCELADA(2),
  SAQUE(3),
  PAGAMENTO(4),
  UNUNKNOWN(0);

  private final Integer id;

  OperationType(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public static OperationType getById(Integer id) {
    for (OperationType e : values()) {
      if (e.id.equals(id)) return e;
    }
    throw new OperationTypeNotFoundException(id);
  }
}
