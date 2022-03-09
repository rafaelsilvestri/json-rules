package com.github.rafaelsilvestri.json_rules.entrypoint;

import com.github.rafaelsilvestri.json_rules.usecase.ApplyValidation;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationRequest;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationResult;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidatorDefinition;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ValidatorController {

    private final List<ValidatorDefinition> validatorDefinitions;
    private final ApplyValidation applyValidation;

    /**
     * Get all validators definition.
     *
     * @return a list of validator definition
     */
    @GetMapping("/validators/definition")
    ResponseEntity<List<ValidatorDefinition>> all() {
        return ResponseEntity.ok(validatorDefinitions);
    }

    /**
     * Perform the validations.
     *
     * @param validationRequest data to be validated
     * @return validation result
     */
    @PostMapping("/validators")
    ResponseEntity<ValidationResult> validate(@RequestBody ValidationRequest validationRequest) {
        ValidationResult result = applyValidation.validate(validationRequest);
        return ResponseEntity.ok(result);
    }
}
