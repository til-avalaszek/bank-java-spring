package com.valaszek.bank;

import com.valaszek.bank.account.AccountService;
import com.valaszek.bank.account.dto.AccountDto;
import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.account.repository.AccountRepository;
import com.valaszek.bank.exception.AccountNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceUnitTest {

  private static final BigInteger ACCOUNT_ID = BigInteger.valueOf(1);

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
    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(mockAccount());
    var account = accountService.findAccount(ACCOUNT_ID);

    assertThat(account).isNotNull();
    Assertions.assertEquals(ACCOUNT_ID, account.getAccountId());
  }

  @Test
  public void shouldThrowsExceptionAccountNotFound() throws Exception {
    when(accountRepository.findById(ACCOUNT_ID)).thenThrow(AccountNotFoundException.class);

    assertThrows(
        AccountNotFoundException.class,
        () -> {
          accountService.findAccount(ACCOUNT_ID);
        });
  }

  private AccountDto mockAccountDto() {
    return AccountDto.builder().accountId(ACCOUNT_ID).documentNumber("692.265.440-42").build();
  }

  private Optional<AccountEntity> mockAccount() {
    return Optional.of(
        AccountEntity.builder().accountId(ACCOUNT_ID).documentNumber("692.265.440-42").build());
  }
}
