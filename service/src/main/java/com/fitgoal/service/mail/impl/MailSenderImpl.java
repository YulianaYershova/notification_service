package com.fitgoal.service.mail.impl;

import com.fitgoal.service.config.SenderConfiguration;
import com.fitgoal.service.mail.MailSender;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

import javax.inject.Inject;

public class MailSenderImpl implements MailSender {

    private final Mailer mailer;
    private final SenderConfiguration senderConfiguration;

    @Inject
    public MailSenderImpl(Mailer mailer, SenderConfiguration senderConfiguration) {
        this.mailer = mailer;
        this.senderConfiguration = senderConfiguration;
    }

    @Override
    public void sendMail(String to, String subject, String text) {
        Email email = EmailBuilder.startingBlank()
                .from(senderConfiguration.getFromName(), senderConfiguration.getFromAddress())
                .to(to)
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();
        mailer.sendMail(email);
    }
}
