package com.github.rafaelsilvestri.json_rules.usecase.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Data;

@Data
public class ValidatorDefinition {

  private String name;
  private String description;
  private ApplyTo applyTo;
  @JsonProperty("validator")
  private Action action;

  @Data
  public static class Action {
    private String name;
    private Map<String, String> args;
  }

  @Data
  public static class ApplyTo {
    private String onStep;
    private String type;
    private String processName;
  }
}
