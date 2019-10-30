package com.fit_goal.util;

import lombok.experimental.UtilityClass;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

@UtilityClass
public class MailSender {

    //TODO configure in config.yml
    private final String HOST = "smtp.mailtrap.io";
    private final int PORT = 25;
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String FROM_NAME = "Fit Goal";
    private final String FROM_ADDRESS = "fitgoal@gmail.com";

    public void sendMail(String to, String subject, String text) {
        Mailer mailer = MailerBuilder
                .withSMTPServer(HOST, PORT, USERNAME, PASSWORD).buildMailer();
        Email email = EmailBuilder.startingBlank()
                .from(FROM_NAME, FROM_ADDRESS)
                .to(to)
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();
        mailer.sendMail(email);
    }
}
