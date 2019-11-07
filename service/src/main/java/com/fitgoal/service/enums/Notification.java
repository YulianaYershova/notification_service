package com.fitgoal.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Notification {

    REGISTER("Confirm registration", "Follow the link to confirm registration "),
    RESET_PASSWORD("Reset password", "Follow the link to reset password "),
    SUCCESS_REGISTRATION("Registration notification", "Registration completed!"),
    SUCCESS_RESET_PASSWORD("Password notification", "Your password changed for your Fit Goal account!");

    private String subject;
    private String message;
}