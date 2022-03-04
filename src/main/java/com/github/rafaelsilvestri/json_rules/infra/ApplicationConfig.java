package com.github.rafaelsilvestri.json_rules.infra;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidatorDefinition;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public List<ValidatorDefinition> validators(ObjectMapper mapper) throws IOException {
    try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("validators.json")) {
      return mapper.readValue(inputStream, new TypeReference<>() {});
    }
  }
}
