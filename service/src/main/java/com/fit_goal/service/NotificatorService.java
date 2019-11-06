package com.fit_goal.service;

import com.fit_goal.Audit;
import com.fit_goal.api.Notificator;
import com.fit_goal.domain.AuditDto;
import com.fit_goal.domain.UserVerification;
import com.fit_goal.enums.Notification;
import com.fit_goal.util.MailSender;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Date;

public class NotificatorService implements Notificator {

    private final Audit audit;
    private final MailSender mailSender;

    @Inject
    public NotificatorService(Audit audit, MailSender mailSender) {
        this.audit = audit;
        this.mailSender = mailSender;
    }

    @Override
    public void register(UserVerification userVerification) {
        //TODO create method for link generating
        String link = String.format("user_service/verify/%s", userVerification.getVerificationLink());
        sendNotification(userVerification.getEmail(), Notification.REGISTER, link);
    }

    @Override
    public void registerSuccess(String email) {
        sendNotification(email, Notification.SUCCESS_REGISTRATION);
    }

    @Override
    public void resetPassword(UserVerification userVerification) {
        //TODO create method for link generating
        String link = String.format("user_service/verify/%s", userVerification.getVerificationLink());
        sendNotification(userVerification.getEmail(), Notification.RESET_PASSWORD, link);
    }

    @Override
    public void resetPasswordSuccess(String email) {
        sendNotification(email, Notification.SUCCESS_RESET_PASSWORD);
    }

    private void sendNotification(String email, Notification notification) {
        mailSender.sendMail(email, notification.getSubject(), notification.getMessage());
        audit.create(new AuditDto("user_service", "sending successful notification", new Date()));
    }

    private void sendNotification(String email, Notification notification, String link) {
        String message = String.format(notification.getMessage(),link);
        mailSender.sendMail(email, notification.getSubject(), message);
        audit.create(new AuditDto("user_service", "sending successful notification", new Date()));
    }
}
