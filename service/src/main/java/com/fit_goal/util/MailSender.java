package com.fit_goal.util;

import lombok.experimental.UtilityClass;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

@UtilityClass
public class MailSender {
/*    private Mailer mailer;


    public MailSenderService(Mailer mailer) {
        this.mailer = mailer;

    }

    public Mailer getMailer() {
        return this.mailer;
    }*/

    public void sendMail(String to, String subject, String text) {
        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.mailtrap.io", 25, "358574e4794fb4", "2202a3db602b18").buildMailer();
        Email email = EmailBuilder.startingBlank()
                .from("Fit Goal", "fitgoal@gmail.com")
                .to(to)
                .withSubject(subject)
                .withPlainText(text)
                .buildEmail();
        mailer.sendMail(email);
    }
}
