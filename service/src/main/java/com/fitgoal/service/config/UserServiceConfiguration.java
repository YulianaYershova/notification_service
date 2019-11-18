package com.fitgoal.service.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@NoArgsConstructor
@Getter
@Setter
public class UserServiceConfiguration {
    @NotNull
    private String scheme;
    @NotNull
    private String host;
    @NotNull
    private int port;
}
