package com.valaszek.bank.entrypoint;

import com.valaszek.bank.entrypoint.request.TransactionRequest;
import com.valaszek.bank.exception.AccountNotFoundException;
import com.valaszek.bank.exception.OperationTypeNotFoundException;
import com.valaszek.bank.transaction.TransactionService;
import com.valaszek.bank.transaction.dto.TransactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionResource.class);

  private final TransactionService transactionService;

  public TransactionResource(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public ResponseEntity<Void> create(
      @RequestBody @Valid final TransactionRequest transactionRequest) {
    try {
      LOGGER.info("r=TRANSACTION_CREATE {}", transactionRequest);
      final TransactionDto transactionDto = TransactionDto.of(transactionRequest);
      transactionService.create(transactionDto);
      LOGGER.info("r=SUCCESS {}", transactionRequest);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (AccountNotFoundException | OperationTypeNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
