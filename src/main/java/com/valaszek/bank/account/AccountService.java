package com.valaszek.bank.account;

import com.valaszek.bank.account.dto.AccountDto;
import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.account.repository.AccountRepository;
import com.valaszek.bank.exception.AccountNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Transactional
  public AccountEntity create(AccountDto accountDTO) {
    return accountRepository.save(AccountEntity.of(accountDTO));
  }

  public AccountEntity findAccount(BigInteger id) {
    return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
  }
}
