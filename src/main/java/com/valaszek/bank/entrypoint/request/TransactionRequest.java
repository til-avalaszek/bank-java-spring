package com.valaszek.bank.entrypoint.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class TransactionRequest {

  @NotNull(message = "idt_account não pode ser nulo")
  @JsonProperty("account_id")
  private BigInteger accountId;

  @NotNull(message = "operation_type_id não pode ser nulo")
  @JsonProperty("operation_type_id")
  private Integer operationTypeId;

  @NotNull(message = "amount não pode ser nulo")
  @DecimalMin("0.0")
  private BigDecimal amount;
}
