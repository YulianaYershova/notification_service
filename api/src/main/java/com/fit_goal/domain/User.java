package com.fit_goal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class User {

    @NotNull
    private String email;

    @JsonProperty
    @NotNull
    private String verificationLink;

}