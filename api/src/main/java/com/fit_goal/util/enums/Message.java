package com.fit_goal.util.enums;

public enum Message {

    REGISTER_MESSAGE("Follow the link to confirm registration "),
    RESET_PASSWORD_MESSAGE("Follow the link to reset password "),
    SUCCESS_REGISTRATION_MESSAGE("Registration completed!"),
    SUCCESS_RESET_PASSWORD_MESSAGE("Your password changed for your Fit Goal account!");

    private String value;

    Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
