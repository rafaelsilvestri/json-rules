package com.github.rafaelsilvestri.json_rules.usecase.validation;

import java.util.Optional;

public interface ValidatorGateway {

  Optional<Validator> findValidator(ValidationRequest validationRequest);
}
