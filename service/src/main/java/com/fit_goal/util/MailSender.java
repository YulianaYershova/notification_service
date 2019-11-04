package com.fit_goal.util;

import com.fit_goal.MailerConfiguration;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;


public class MailSender {

    private final Mailer mailer;
    private final String fromName;
    private final String fromAddress;

    public MailSender(MailerConfiguration mailerConfiguration) {
        mailer = MailerBuilder
                .withSMTPServer(mailerConfiguration.getHost(),
                        mailerConfiguration.getPort(),
                        mailerConfiguration.getUsername(),
                        mailerConfiguration.getPassword())
                .buildMailer();
        this.fromName = mailerConfiguration.getFromName();
        this.fromAddress = mailerConfiguration.getFromAddress();
    }

    public void sendMail(String to, String subject, String text) {
        Email email = EmailBuilder.startingBlank()
                .from(fromName, fromAddress)
                .to(to)
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();
        mailer.sendMail(email);
    }
}
