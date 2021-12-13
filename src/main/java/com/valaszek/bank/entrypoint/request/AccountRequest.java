package com.valaszek.bank.entrypoint.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.valaszek.bank.utils.CPFOrCNPJ;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AccountRequest {

  @NotEmpty(message = "document_number não pode ser vazio.")
  @CPFOrCNPJ
  @JsonProperty("document_number")
  private String documentNumber;
}
