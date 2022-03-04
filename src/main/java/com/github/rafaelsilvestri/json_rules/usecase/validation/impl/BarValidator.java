package com.github.rafaelsilvestri.json_rules.usecase.validation.impl;

import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationException;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationRequest;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationResult;
import com.github.rafaelsilvestri.json_rules.usecase.validation.Validator;
import java.util.Arrays;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/** Simple implementation of a validator class. */
@Slf4j
public class BarValidator implements Validator {

  @Override
  public void init(Map<String, String> args) {
    log.info(
        "Initializing {} with {} and {}",
        getClass().getSimpleName(),
        args.get("arg1"),
        args.get("arg2"));
  }

  @Override
  public ValidationResult validate(ValidationRequest validationRequest) throws ValidationException {
    final var stepAllowedList = Arrays.asList("step1", "step2", "step4");
    boolean isValid = stepAllowedList.contains(validationRequest.getOnStep());
    if (isValid) {
      ValidationResult.builder().isValid(true).build();
    }

    return ValidationResult.builder().isValid(false).error("Step not allowed").build();
  }
}
