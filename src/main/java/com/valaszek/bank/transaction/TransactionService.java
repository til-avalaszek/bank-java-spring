package com.valaszek.bank.transaction;

import com.valaszek.bank.account.AccountService;
import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.enums.OperationType;
import com.valaszek.bank.transaction.dto.TransactionDto;
import com.valaszek.bank.transaction.model.TransactionEntity;
import com.valaszek.bank.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionService {

  private final AccountService accountService;
  private final TransactionRepository transactionRepository;

  public TransactionService(
      AccountService accountService, TransactionRepository transactionRepository) {
    this.accountService = accountService;
    this.transactionRepository = transactionRepository;
  }

  @Transactional
  public void create(TransactionDto transactionDto) {
    AccountEntity accountEntity = accountService.findAccount(transactionDto.getAccountId());
    OperationType operationType = OperationType.getById(transactionDto.getOperationTypeId());

    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setAccount(accountEntity);
    transactionEntity.setOperationType(operationType.getId());
    transactionEntity.setAmount(transactionDto.getAmount());

    transactionRepository.save(transactionEntity);
  }
}
