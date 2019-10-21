package com.fit_goal.api;

public interface MailSender {
    void sendMail(String from, String to, String subject, String text);
}
