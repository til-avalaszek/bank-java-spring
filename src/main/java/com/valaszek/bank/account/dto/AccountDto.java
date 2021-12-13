package com.valaszek.bank.account.dto;

import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.entrypoint.request.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

@AllArgsConstructor
@Getter
@Builder
public class AccountDto {

  private final BigInteger accountId;
  private final String documentNumber;

  public static AccountDto of(AccountEntity accountEntity) {
    return AccountDto.builder()
        .accountId(accountEntity.getAccountId())
        .documentNumber(accountEntity.getDocumentNumber())
        .build();
  }

  public static AccountDto of(AccountRequest accountRequest) {
    return AccountDto.builder().documentNumber(accountRequest.getDocumentNumber()).build();
  }
}
