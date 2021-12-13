package com.valaszek.bank.transaction.dto;

import com.valaszek.bank.entrypoint.request.TransactionRequest;
import com.valaszek.bank.transaction.model.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@Getter
@Builder
public class TransactionDto {

  private BigInteger accountId;
  private Integer operationTypeId;
  private BigDecimal amount;

  public static TransactionDto of(TransactionEntity transactionEntity) {
    return TransactionDto.builder()
        .accountId(transactionEntity.getAccount().getAccountId())
        .operationTypeId(transactionEntity.getOperationType())
        .amount(transactionEntity.getAmount())
        .build();
  }

  public static TransactionDto of(TransactionRequest transactionRequest) {
    return TransactionDto.builder()
        .accountId(transactionRequest.getAccountId())
        .operationTypeId(transactionRequest.getOperationTypeId())
        .amount(transactionRequest.getAmount())
        .build();
  }
}
