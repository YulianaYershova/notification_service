package com.fit_goal;

public interface MailSender {
    void sendMail(String to, String subject, String text);
}
