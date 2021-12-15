package com.valaszek.bank.entrypoint;

import com.valaszek.bank.IntegrationTest;
import com.valaszek.bank.account.dto.AccountDto;
import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.account.repository.AccountRepository;
import com.valaszek.bank.transaction.model.TransactionEntity;
import com.valaszek.bank.transaction.repository.TransactionRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class TransactionResourceTest extends IntegrationTest {

  @Autowired private AccountRepository accountRepository;
  @Autowired private TransactionRepository transactionRepository;

  @Test
  public void shouldCreateTransaction() throws Exception {
    AccountDto accountDto = mockAccountDto();
    accountRepository.save(AccountEntity.of(accountDto));

    JSONObject form = new JSONObject();
    form.put("account_id", 1);
    form.put("operation_type_id", 4);
    form.put("amount", 123.45);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/transactions")
                .content(String.valueOf(form))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isCreated());

    List<TransactionEntity> transactions = transactionRepository.findAll();
    assertEquals(BigInteger.valueOf(1), transactions.get(0).getAccount().getAccountId());
    assertEquals(4, transactions.get(0).getOperationType());
    assertEquals(new BigDecimal("123.45"), transactions.get(0).getAmount());
  }

  @Test
  public void shouldThrowExceptionIfAccountNotFound() throws Exception {
    JSONObject form = new JSONObject();
    form.put("account_id", 10); // NOT FOUND
    form.put("operation_type_id", 4);
    form.put("amount", 123.45);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/transactions")
                .content(String.valueOf(form))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void shouldThrowExceptionIfOperationTypeNotFound() throws Exception {
    JSONObject form = new JSONObject();
    form.put("account_id", 1);
    form.put("operation_type_id", 40); // NOT FOUND
    form.put("amount", 123.45);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/transactions")
                .content(String.valueOf(form))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  private AccountDto mockAccountDto() {
    return AccountDto.builder().accountId(BigInteger.valueOf(1)).documentNumber("123").build();
  }
}
