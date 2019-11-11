package com.fitgoal.service.mail;

import com.fitgoal.service.config.MailerConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderImplTest {

    @Mock
    private Mailer mailer;
    @Mock
    private MailerConfiguration mailerConfiguration;
    private MailSender mailSender;

    @Before
    public void setupMailer() {
        when(mailerConfiguration.getHost()).thenReturn("smtp.mailtrap.io");
        when(mailerConfiguration.getPort()).thenReturn(25);
        when(mailerConfiguration.getUsername()).thenReturn("b32aab2b4463b2");
        when(mailerConfiguration.getPassword()).thenReturn("a351bc2793a9bf");
        when(mailerConfiguration.getFromAddress()).thenReturn("fitgoal@gmail.com");
        when(mailerConfiguration.getFromName()).thenReturn("Fit Goal");
        mailSender = new MailSenderImpl(mailerConfiguration);
    }

    @Test
    public void sendMailTest() {
        String to = "test@test.com";
        String subject = "subject";
        String text = "text";
        mailSender.sendMail(to, subject, text);
        Email email = EmailBuilder.startingBlank()
                .from("fitgoal@gmail.com", "Fit Goal")
                .to(to)
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();
        verify(mailer, times(1)).sendMail(email);
    }
}