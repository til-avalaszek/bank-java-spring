package com.valaszek.bank.entrypoint;

import com.valaszek.bank.account.AccountService;
import com.valaszek.bank.account.dto.AccountDto;
import com.valaszek.bank.account.model.AccountEntity;
import com.valaszek.bank.entrypoint.request.AccountRequest;
import com.valaszek.bank.entrypoint.response.AccountResponse;
import com.valaszek.bank.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountResource.class);

  private final AccountService accountService;

  @PostMapping
  public ResponseEntity<AccountResponse> create(
      @RequestBody @Valid final AccountRequest accountRequest) {
    LOGGER.info("r=ACCOUNT_CREATE {}", accountRequest);
    final AccountDto accountDto = AccountDto.of(accountRequest);
    accountService.create(accountDto);
    LOGGER.info("r=SUCCESS {}", accountRequest);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<AccountResponse> find(@PathVariable BigInteger accountId) {
    try {
      LOGGER.info("r=ACCOUNT_FIND {}", accountId);
      AccountEntity account = accountService.findAccount(accountId);
      LOGGER.info("r=SUCCESS {}", accountId);
      return ResponseEntity.ok(AccountResponse.of(account));
    } catch (AccountNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

}
