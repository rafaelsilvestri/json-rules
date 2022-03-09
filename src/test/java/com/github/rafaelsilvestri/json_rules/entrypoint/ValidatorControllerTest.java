package com.github.rafaelsilvestri.json_rules.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rafaelsilvestri.json_rules.usecase.validation.ValidationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ValidatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldValidateSuccessfullyUsingHttpValidator() throws Exception {
        // given
        final var request = ValidationRequest.builder().onStep("step1").type("xpto").processName("process_valid").build();

        // when
        final var result = mockMvc.perform(post("/validators")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result
                .andExpect(status().isOk()) // means the validation performs successfully
                .andExpect(jsonPath("$.isValid", is(true)));
    }

    @Test
    void shouldFailUsingHttpValidatorWhenEndpointNotFound() throws Exception {
        // given
        final var request = ValidationRequest.builder().onStep("step1").type("xpto").processName("return_404").build();

        // when
        final var result = mockMvc.perform(post("/validators")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result
                .andExpect(status().isOk()) // means the validation performs successfully
                .andExpect(jsonPath("$.isValid", is(false))) // but the rules fail.
                .andExpect(jsonPath("$.errors[0].status", is(HttpStatus.BAD_REQUEST.value()))) // but the rules fail.
                .andExpect(jsonPath("$.errors[0].message", startsWith("404 Not Found")))
                .andExpect(jsonPath("$.errors[0].cause", is("")));
    }

    @Test
    void shouldFailUsingHttpValidator() throws Exception {
        // given
        final var request = ValidationRequest.builder().onStep("step1").type("xpto").processName("process_xpto").build();

        // when
        final var result = mockMvc.perform(post("/validators")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result
                .andExpect(status().isOk()) // means the validation performs successfully
                .andExpect(jsonPath("$.errors[0].status", is(HttpStatus.BAD_REQUEST.value()))) // but the rules fail.
                .andExpect(jsonPath("$.errors[0].message", is("Oops! Something went wrong!!!")))
                .andExpect(jsonPath("$.errors[0].cause", is("the_cause_of_error")));
    }

    @Test
    void shouldValidateSuccessfullyUsingBarValidator() throws Exception {
        // given
        final var request = ValidationRequest.builder().onStep("step2").type("bar").processName("process_bar").build();

        // when
        final var result = mockMvc.perform(post("/validators")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid", is(true)));
    }

    @Test
    void shouldFailUsingBarValidator() throws Exception {
        // given
        final var request = ValidationRequest.builder().onStep("stepInvalid").type("bar").processName("process_bar").build();

        // when
        final var result = mockMvc.perform(post("/validators")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result
                .andExpect(status().isOk()) // means the validation performs successfully
                .andExpect(jsonPath("$.errors[0].status", is(HttpStatus.BAD_REQUEST.value()))) // but the rules fail.
                .andExpect(jsonPath("$.errors[0].message", is("Step not allowed!")));
    }

}