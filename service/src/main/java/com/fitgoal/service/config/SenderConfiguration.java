package com.fitgoal.service.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class SenderConfiguration {
    @NotNull
    private String fromName;
    @NotNull
    private String fromAddress;
}
