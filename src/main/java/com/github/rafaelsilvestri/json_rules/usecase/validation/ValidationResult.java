package com.github.rafaelsilvestri.json_rules.usecase.validation;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

/**
 * The output produced by the validator implementation that contains the status of the validation
 * and a collection of errors if the validation failed.
 */
@Data
@Builder
public class ValidationResult {

  private final boolean isValid;

  @Singular
  private final List<String> errors; // todo: substituir string por classe de erro conhecida
}
