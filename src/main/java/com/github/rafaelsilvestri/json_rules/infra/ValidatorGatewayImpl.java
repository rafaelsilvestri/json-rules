package com.github.rafaelsilvestri.json_rules.infra;

import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationRequest;
import com.github.rafaelsilvestri.json_rules.usecase.validation.Validator;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidatorDefinition;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidatorGateway;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ValidatorGatewayImpl implements ValidatorGateway {

  private final BeanFactory beanFactory;
  private final List<ValidatorDefinition> validatorsDefinition;

  @Override
  public Optional<Validator> findValidator(ValidationRequest validationRequest) {
    Optional<ValidatorDefinition> definition =
        validatorsDefinition.stream()
            .filter(v -> validationRequest.getOnStep().equals(v.getApplyTo().getOnStep()))
            .filter(v -> validationRequest.getType().equals(v.getApplyTo().getType()))
            .filter(v -> validationRequest.getProcessName().equals(v.getApplyTo().getProcessName()))
            .findFirst();

    if (definition.isPresent()) {
      final ValidatorDefinition.Action action = definition.get().getAction();
      Validator validator = (Validator) beanFactory.getBean(action.getName());
      validator.init(action.getArgs()); // porque não no construtor?
      // validar se uma nova instancia é criada a cada execução
      log.info("Found: {}", validator.getClass().toString());
      return Optional.of(validator);
    }
    log.info("No validator was found.");
    return Optional.empty();
  }
}
