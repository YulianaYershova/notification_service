package com.fitgoal.service.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class MailerConfiguration {
    @NotNull
    private String host;
    @NotNull
    private int port;
    @NotNull
    private String username;
    @NotNull
    private String password;
}
