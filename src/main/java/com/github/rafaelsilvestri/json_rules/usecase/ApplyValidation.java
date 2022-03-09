package com.github.rafaelsilvestri.json_rules.usecase;

import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationException;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationRequest;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationResult;
import com.github.rafaelsilvestri.json_rules.usecase.validation.Validator;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidatorGateway;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplyValidation {

    private final ValidatorGateway validatorGateway;

    public ValidationResult validate(ValidationRequest validationRequest) throws ValidationException {
        final Optional<Validator> validator = validatorGateway.findValidator(validationRequest);
        if (validator.isPresent()) {
            // apply the validation
            return validator.get().validate(validationRequest);
        }
        // otherwise, returns true
        return new ValidationResult(true, null);
    }
}
