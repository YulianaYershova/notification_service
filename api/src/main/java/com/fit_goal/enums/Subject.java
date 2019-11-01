package com.fit_goal.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Subject {

    REGISTER("Confirm registration "),
    RESET_PASSWORD("Reset password "),
    SUCCESS_REGISTRATION("Registration notification"),
    SUCCESS_RESET_PASSWORD("Password notification");

    private String value;

    public String getValue() {
        return this.value;
    }
}

