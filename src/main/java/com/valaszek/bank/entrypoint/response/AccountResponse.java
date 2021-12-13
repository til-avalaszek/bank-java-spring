package com.valaszek.bank.entrypoint.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valaszek.bank.account.model.AccountEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class AccountResponse {

  @JsonProperty("account_id")
  private BigInteger accountId;

  @JsonProperty("document_number")
  private String documentNumber;

  public static AccountResponse of(AccountEntity accountEntity) {
    return AccountResponse.builder()
        .accountId(accountEntity.getAccountId())
        .documentNumber(accountEntity.getDocumentNumber())
        .build();
  }
}
