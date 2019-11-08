package com.fitgoal.service.util;

import com.fitgoal.service.config.MailerConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderTest {

    private MailSender mailSender;
    @Mock
    private MailerConfiguration mailerConfiguration;

    @Before
    public void setupMailer() {
        when(mailerConfiguration.getHost()).thenReturn("smtp.mailtrap.io");
        when(mailerConfiguration.getPort()).thenReturn(25);
        when(mailerConfiguration.getUsername()).thenReturn("b32aab2b4463b2");
        when(mailerConfiguration.getPassword()).thenReturn("a351bc2793a9bf");
        when(mailerConfiguration.getFromAddress()).thenReturn("fitgoal@gmail.com");
        when(mailerConfiguration.getFromName()).thenReturn("Fit Goal");
        mailSender = new MailSender(mailerConfiguration);
    }

    @Test
    public void sendMailTest() {
        mailSender.sendMail("test@test.com", "subject", "text");
    }
}