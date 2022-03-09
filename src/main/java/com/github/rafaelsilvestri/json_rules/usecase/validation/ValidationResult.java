package com.github.rafaelsilvestri.json_rules.usecase.validation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

/**
 * The output produced by the validator implementation that contains the status of the validation
 * and a collection of errors if the validation failed.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationResult {

    @JsonProperty("isValid")
    boolean isValid;

    @Singular
    List<Error> errors;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Error {
        int status;
        String cause;
        String message;
    }
}
