package com.fit_goal.service;

import com.fit_goal.Audit;
import com.fit_goal.Notification;
import com.fit_goal.api.Notificator;
import com.fit_goal.domain.AuditDto;
import com.fit_goal.domain.UserVerification;
import com.fit_goal.util.MailSender;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class NotificatorService implements Notificator {

    private final Audit audit;
    private final MailSender mailSender;

    @Inject
    public NotificatorService(Audit audit, MailSender mailSender) {
        this.audit = audit;
        this.mailSender = mailSender;
    }

    @Override
    public void sendVerificationLink(UserVerification userVerification, Notification notification) {
        //TODO create method for link generating
        String link = "user_service/verify/" + userVerification.getVerificationLink();
        mailSender.sendMail(userVerification.getEmail(), notification.getSubject().getValue(), notification.getMessage().getValue() + link);
        audit.create(new AuditDto("user_service", "sending verification link", LocalDateTime.now()));
    }

    @Override
    public void sendNotification(String email, Notification notification) {
        mailSender.sendMail(email, notification.getSubject().getValue(), notification.getMessage().getValue());
        audit.create(new AuditDto("user_service", "sending successful notification", LocalDateTime.now()));
    }

}