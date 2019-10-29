package com.fit_goal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
public class UserVerification {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @JsonProperty
    private String verificationLink;

}