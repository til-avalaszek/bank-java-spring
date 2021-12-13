package com.valaszek.bank.transaction.repository;

import com.valaszek.bank.transaction.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, BigInteger> {
}
