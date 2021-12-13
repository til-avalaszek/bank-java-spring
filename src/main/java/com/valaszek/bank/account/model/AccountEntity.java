package com.valaszek.bank.account.model;

import com.valaszek.bank.account.dto.AccountDto;
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
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
  @Column(name = "idt_account")
  private BigInteger accountId;

//  @ColumnTransformer(
//      forColumn = "desc_document_number",
//      read = "pgp_sym_decrypt(desc_document_number::bytea, 'secret')",
//      write = "pgp_sym_encrypt(?, 'secret')")
//  @Column(name = "desc_document_number", columnDefinition = "bytea", nullable = false)
  @Column(name = "desc_document_number", nullable = false)
  private String documentNumber;

  @Column(name = "dat_creation", updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  public static AccountEntity of(AccountDto accountDto) {
    return AccountEntity.builder()
        .accountId(accountDto.getAccountId())
        .documentNumber(accountDto.getDocumentNumber())
        .createdAt(LocalDateTime.now())
        .build();
  }
}
