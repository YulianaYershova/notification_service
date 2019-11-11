package com.fitgoal.service.mail.impl;

import com.fitgoal.service.config.SenderConfiguration;
import com.fitgoal.service.mail.MailSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@RunWith(MockitoJUnitRunner.class)
public class MailSenderImplTest {

    @Mock
    private Mailer mailer;
    @Mock
    private SenderConfiguration senderConfiguration;

    private MailSender mailSender;

    @Before
    public void setupMailer() {
        mailSender = new MailSenderImpl(mailer,senderConfiguration);
    }

    @Test
    public void sendMailTest() {
        String to = "test@test.com";
        String subject = "subject";
        String text = "text";
        String fromAddress = "gitgoal@gmail.com";
        String fromName = "Fit Goal";

        when(senderConfiguration.getFromAddress()).thenReturn(fromAddress);
        when(senderConfiguration.getFromName()).thenReturn(fromName);

        Email email = EmailBuilder.startingBlank()
                .from(fromName, fromAddress)
                .to(to)
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();

        mailSender.sendMail(to, subject, text);

        verify(mailer, times(1)).sendMail(email);
    }
}