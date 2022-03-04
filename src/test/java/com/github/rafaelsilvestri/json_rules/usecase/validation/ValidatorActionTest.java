package com.github.rafaelsilvestri.json_rules.usecase.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidatorActionTest {

  private static List<ValidatorDefinition> validators;
  @Autowired  private BeanFactory beanFactory;

  @BeforeAll
  static void setUp() throws IOException {
    final ObjectMapper mapper = new ObjectMapper();
    // load validators only once (reload if needed)
    var fileName = "validators.json";
    var classLoader = ValidatorActionTest.class.getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
      validators = mapper.readValue(inputStream, new TypeReference<>() {});
    }
  }

  // validar regras duplicadas/sobrepostas
  // two action block should apply the last one
}
