package com.fit_goal.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Message {

    REGISTER("Follow the link to confirm registration "),
    RESET_PASSWORD("Follow the link to reset password "),
    SUCCESS_REGISTRATION("Registration completed!"),
    SUCCESS_RESET_PASSWORD("Your password changed for your Fit Goal account!");

    private String value;

    public String getValue() {
        return this.value;
    }

}
