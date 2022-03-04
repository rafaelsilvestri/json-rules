package com.github.rafaelsilvestri.json_rules.usecase.validation.impl;

import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationException;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationRequest;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationResult;
import com.github.rafaelsilvestri.json_rules.usecase.validation.Validator;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
/**
 * Generic validator that make requests to external APIs to perform the validations.
 *
 * <p>All external APIs should follow the {@link ValidationRequest} contract
 */
@Slf4j
public class HttpValidator implements Validator {

  private final RestTemplate restTemplate;
  private String endpoint;

  public HttpValidator(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public void init(Map<String, String> args) {
    this.endpoint = args.get("endpoint");
  }

  @Override
  public ValidationResult validate(ValidationRequest validationRequest) throws ValidationException {
    try {
      // fazer uma requisição post para jsonplaceholder para teste
      // https://medium.com/code-prestige/como-criar-um-a-api-rest-fake-para-testes-jsonplaceholder-7cc106ea3bd6
      ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);
      boolean isValid =
          response.getStatusCodeValue() == 200 || response.getStatusCodeValue() == 201;
      return ValidationResult.builder().isValid(isValid).build();
    } catch (HttpClientErrorException ex) {
      log.error(ex.getMessage(), ex);
      return ValidationResult.builder().isValid(false).error(ex.getStatusText()).build();
    }
  }
}
