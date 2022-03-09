package com.github.rafaelsilvestri.json_rules.usecase.validation.impl;

import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationException;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationRequest;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationResult;
import com.github.rafaelsilvestri.json_rules.usecase.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

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
            final ResponseEntity<ValidationResult> result =
                    restTemplate.getForEntity(endpoint, ValidationResult.class);
            return result.getBody();
        } catch (HttpClientErrorException ex) {
            log.error(ex.getMessage(), ex);
            return ValidationResult.builder()
                    .isValid(false)
                    .error(
                            ValidationResult.Error.builder()
                                    .status(400)
                                    .cause(ex.getCause() == null ? "" : ex.getCause().toString())
                                    .message(ex.getMessage())
                                    .build())
                    .build();
        }
    }
}
