package com.fit_goal.enums;

public enum Subject {

    REGISTER_SUBJECT("Confirm registration "),
    RESET_PASSWORD_SUBJECT("Reset password "),
    SUCCESS_REGISTRATION_SUBJECT("Registration notification"),
    SUCCESS_RESET_PASSWORD_SUBJECT("Password notification");

    private String value;

    Subject(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

