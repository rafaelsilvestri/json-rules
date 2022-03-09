package com.github.rafaelsilvestri.json_rules.usecase.validation;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationRequest {

    String onStep;
    String processName;
    String type;

    // simple data received and used on validation phase
    String foo;
    String bar;

}
