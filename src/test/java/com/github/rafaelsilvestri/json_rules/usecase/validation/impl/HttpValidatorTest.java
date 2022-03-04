package com.github.rafaelsilvestri.json_rules.usecase.validation.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidatorDefinition;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationResult;
import com.github.rafaelsilvestri.json_rules.usecase.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HttpValidatorTest {

  private static List<ValidatorDefinition> validators;
  @Autowired private BeanFactory beanFactory;
/*
  @BeforeAll
  static void setUp() throws IOException {
    final ObjectMapper mapper = new ObjectMapper();
    var fileName = "validators.json";
    var classLoader = HttpValidatorTest.class.getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
      validators = mapper.readValue(inputStream, new TypeReference<>() {});
    }
  }

  @Test
  void shouldPassWhenHttpStatusIs2xx() {
    // given validator request
    var onStep = "step1";
    var type = "xpto";
    var processName = "process_xpto";

    // filter validators to apply
    var toExecute =
        validators.stream()
            .filter(v -> onStep.equals(v.getOnStep()))
            .filter(v -> type.equals(v.getType()))
            .filter(v -> processName.equals(v.getProcessName()))
            .collect(Collectors.toList());

    // then
    assertEquals(1, toExecute.size());
    final ValidatorDefinition result = toExecute.get(0);
    assertEquals("xpto", result.getType());
    assertEquals("process_xpto", result.getProcessName());
    assertEquals("FooValidator", result.getName());

    final ValidatorDefinition.Action action = result.getAction();
    Validator validator = (Validator) beanFactory.getBean(action.getName());
    validator.init(action.getArgs());
    ValidationResult validationResult = validator.validate();
    assertTrue(validationResult.isValid());
    assertTrue(validationResult.getErrors().isEmpty());
  }

  @Test
  void shouldPassWhenHttpStatusIs404() {
    // given validator request
    var onStep = "step1";
    var type = "xpto";
    var processName = "return_404";

    // filter validators to apply
    var toExecute =
        validators.stream()
            .filter(v -> onStep.equals(v.getOnStep()))
            .filter(v -> type.equals(v.getType()))
            .filter(v -> processName.equals(v.getProcessName()))
            .collect(Collectors.toList());

    // then
    assertEquals(1, toExecute.size());
    final ValidatorDefinition result = toExecute.get(0);
    assertEquals("xpto", result.getType());
    assertEquals("return_404", result.getProcessName());
    assertEquals("FooValidator", result.getName());

    final ValidatorDefinition.Action action = result.getAction();
    Validator validator = (Validator) beanFactory.getBean(action.getName());
    validator.init(action.getArgs());
    ValidationResult validationResult = validator.validate();
    assertFalse(validationResult.isValid());
    assertEquals("Not Found", validationResult.getErrors().get(0));
  }

 */
}
