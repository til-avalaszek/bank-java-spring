package com.valaszek.bank;

import com.valaszek.bank.account.dto.AccountDto;
import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.account.repository.AccountRepository;
import com.valaszek.bank.account.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceUnitTest {

  @Mock private AccountRepository accountRepository;

  @InjectMocks private AccountService accountService;

  @Test
  void shouldSaveAccount() {
    when(accountRepository.save(any())).then(returnsFirstArg());

    AccountEntity account = accountService.create(mockAccountDto());

    assertThat(account).isNotNull();
  }

  @Test
  void shouldReturnAccount() {
    when(accountRepository.findById(BigInteger.valueOf(1))).thenReturn(mockAccount());
    var account = accountService.findAccount(BigInteger.valueOf(1));

    assertThat(account).isNotNull();
    Assertions.assertEquals(BigInteger.valueOf(1), account.getAccountId());
  }

  @Test
  void shouldThrowsExceptionAccountNotFound() {

  }

  private AccountDto mockAccountDto() {
    return AccountDto.builder()
        .accountId(BigInteger.valueOf(1))
        .documentNumber("692.265.440-42")
        .build();
  }

  private Optional<AccountEntity> mockAccount() {
    return Optional.of(
        AccountEntity.builder()
            .accountId(BigInteger.valueOf(1))
            .documentNumber("692.265.440-42")
            .build());
  }
}
