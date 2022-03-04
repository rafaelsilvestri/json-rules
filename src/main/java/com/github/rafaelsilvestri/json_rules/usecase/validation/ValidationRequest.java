package com.github.rafaelsilvestri.json_rules.usecase.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationRequest {

  String onStep;
  String processName;
  String type;

  // just simple data received and used on validation phase
  String foo;
  String bar;

}
