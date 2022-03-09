package com.github.rafaelsilvestri.json_rules.usecase.validation;

import java.util.Map;

public interface Validator {

    /**
     * initialize validator arguments defined on json definition.
     *
     * @param args a map of arguments to initialize the instance.
     */
    void init(Map<String, String> args);

    /**
     * Validation execution method.
     *
     * @param validationRequest the common request data for validation
     * @return {{@link ValidationResult}}
     * @throws ValidationException if something goes wrong.
     */
    ValidationResult validate(ValidationRequest validationRequest) throws ValidationException;
}
