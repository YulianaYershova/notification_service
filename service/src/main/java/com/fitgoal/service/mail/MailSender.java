package com.fitgoal.service.mail;

public interface MailSender {
    void sendMail(String to, String subject, String text);
}
