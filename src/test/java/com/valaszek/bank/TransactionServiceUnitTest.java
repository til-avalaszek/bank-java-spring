package com.valaszek.bank;

import com.valaszek.bank.account.AccountService;
import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.exception.AccountNotFoundException;
import com.valaszek.bank.transaction.TransactionService;
import com.valaszek.bank.transaction.dto.TransactionDto;
import com.valaszek.bank.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceUnitTest {

  private static final BigInteger ACCOUNT_ID = BigInteger.valueOf(1);

  @Mock private TransactionRepository transactionRepository;

  @Mock private AccountService accountService;

  @InjectMocks private TransactionService transactionService;

  @Test
  public void shouldSaveTransaction() {
    when(accountService.findAccount(ACCOUNT_ID)).thenReturn(mockAccount());
    when(transactionRepository.save(any())).then(returnsFirstArg());

    transactionService.create(mockTransactionDto());
  }

  @Test
  public void shouldThrowsExceptionAccountNotFound() throws Exception {
    when(accountService.findAccount(ACCOUNT_ID)).thenThrow(AccountNotFoundException.class);

    assertThrows(
        AccountNotFoundException.class,
        () -> {
          transactionService.create(mockTransactionDto());
        });
  }

  private TransactionDto mockTransactionDto() {
    return TransactionDto.builder()
        .accountId(ACCOUNT_ID)
        .operationTypeId(1)
        .amount(BigDecimal.ONE)
        .build();
  }

  private AccountEntity mockAccount() {
    return AccountEntity.builder()
        .accountId(ACCOUNT_ID)
        .documentNumber("692.265.440-42")
        .build();
  }
}
