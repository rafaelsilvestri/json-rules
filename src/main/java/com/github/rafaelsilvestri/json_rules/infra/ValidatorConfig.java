package com.github.rafaelsilvestri.json_rules.infra;

import com.github.rafaelsilvestri.json_rules.usecase.validation.Validator;
import com.github.rafaelsilvestri.json_rules.usecase.validation.impl.BarValidator;
import com.github.rafaelsilvestri.json_rules.usecase.validation.impl.HttpValidator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ValidatorConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "http")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Validator httpValidator(RestTemplate restTemplate) {
        return new HttpValidator(restTemplate);
    }

    @Bean(name = "BarValidator")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Validator barValidator() {
        return new BarValidator();
    }
}
