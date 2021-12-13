package com.valaszek.bank;

import com.valaszek.bank.account.dto.AccountDto;
import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.account.repository.AccountRepository;
import com.valaszek.bank.entrypoint.request.AccountRequest;
import com.valaszek.bank.utils.ValidationUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class AccountResourceTest extends IntegrationTest {

  @Autowired AccountRepository accountRepository;

  @Test
  public void shouldSaveAccount() throws Exception {
    JSONObject form = new JSONObject();
    form.put("document_number", "692.265.440-42");

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/accounts")
                .content(String.valueOf(form))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isCreated());

    List<AccountEntity> accounts = accountRepository.findAll();
    assertEquals("692.265.440-42", accounts.get(0).getDocumentNumber());
  }

  @Test
  public void shouldValidateDocument() {
    AccountRequest accountRequest = new AccountRequest();
    accountRequest.setDocumentNumber("123");

    Validator validator = ValidationUtils.buildValidator();
    Set<ConstraintViolation<AccountRequest>> constraintViolations =
        validator.validate(accountRequest);

    assertEquals("CPF ou CNPJ inválido", constraintViolations.iterator().next().getMessage());
  }

  @Test
  public void shouldThrowExceptionOnPostInvalidDocument() throws Exception {
    JSONObject form = new JSONObject();
    form.put("document_number", "123");

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/accounts")
                .content(String.valueOf(form))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  public void shouldFindAccount() throws Exception {
    AccountDto accountDto = mockAccountDto();
    accountRepository.save(AccountEntity.of(accountDto));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/accounts/" + accountDto.getAccountId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void shouldThrowExceptionAccountNotFound() throws Exception {
    mockMvc
        .perform(get("/accounts/12345").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  private AccountDto mockAccountDto() {
    return AccountDto.builder().accountId(BigInteger.valueOf(1)).documentNumber("123").build();
  }
}
