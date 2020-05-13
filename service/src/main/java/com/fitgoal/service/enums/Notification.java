package com.fitgoal.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Notification {

    REGISTER("Registration", "Follow the link to confirm registration "),
    RESET_PASSWORD("Reset password", "Follow the link to reset password "),
    CONFIRM_REGISTRATION("Registration notification", "Registration completed!"),
    CONFIRM_PASSWORD_RESET("Password notification", "Your password changed for your Fit Goal account!");

    private String subject;
    private String message;
}
