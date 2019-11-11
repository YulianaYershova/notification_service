package com.fitgoal.service.mail;

import com.fitgoal.service.config.MailerConfiguration;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

import javax.inject.Inject;

public class MailSenderImpl implements MailSender {

    private final Mailer mailer;
//    private final String fromName;
//    private final String fromAddress;
    private final MailerConfiguration mailerConfiguration;

    @Inject
    public MailSenderImpl(MailerConfiguration mailerConfiguration) {
        this.mailerConfiguration = mailerConfiguration;
        mailer = MailerBuilder
                .withSMTPServer(mailerConfiguration.getHost(),
                        mailerConfiguration.getPort(),
                        mailerConfiguration.getUsername(),
                        mailerConfiguration.getPassword())
                .buildMailer();
        /*this.fromName = mailerConfiguration.getFromName();
        this.fromAddress = mailerConfiguration.getFromAddress();*/
    }

    @Override
    public void sendMail(String to, String subject, String text) {
        Email email = EmailBuilder.startingBlank()
                .from(mailerConfiguration.getFromName(), mailerConfiguration.getFromAddress())
                .to(to)
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();
        mailer.sendMail(email);
    }
}
