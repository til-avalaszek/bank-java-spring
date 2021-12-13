package com.valaszek.bank.transaction.model;

import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
  @Column(name = "idt_transaction")
  private BigInteger transactionId;

  @ManyToOne
  @JoinColumn(name = "idt_account", referencedColumnName = "idt_account", nullable = false)
  private AccountEntity account;
  
  @Column(name = "ind_operation_type")
  private Integer operationType;

  @Column(name = "num_amount")
  private BigDecimal amount;

  @Column(name = "dat_event")
  @CreationTimestamp
  private LocalDateTime eventDate;
}
