package com.valaszek.bank.account.repository;

import com.valaszek.bank.account.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, BigInteger> {
}
