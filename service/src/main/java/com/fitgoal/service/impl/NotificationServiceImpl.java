package com.fitgoal.service.impl;

import com.fitgoal.api.NotificationService;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.api.domain.UserVerification;
import com.fitgoal.service.AuditService;
import com.fitgoal.service.enums.Notification;
import com.fitgoal.service.util.MailSender;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Date;

public class NotificationServiceImpl implements NotificationService {

    private final AuditService auditService;
    private final MailSender mailSender;

    @Inject
    public NotificationServiceImpl(AuditService auditService, MailSender mailSender) {
        this.auditService = auditService;
        this.mailSender = mailSender;
    }

    @Override
    public void register(UserVerification userVerification) {
        String link = "user_service/verify/".concat(userVerification.getVerificationLink());
        sendNotification(userVerification.getEmail(), Notification.REGISTER, link);
    }

    @Override
    public void registerSuccess(String email) {
        sendNotification(email, Notification.SUCCESS_REGISTRATION);
    }

    @Override
    public void resetPassword(UserVerification userVerification) {
        String link = "user_service/verify/".concat(userVerification.getVerificationLink());
        sendNotification(userVerification.getEmail(), Notification.RESET_PASSWORD, link);
    }

    @Override
    public void resetPasswordSuccess(String email) {
        sendNotification(email, Notification.SUCCESS_RESET_PASSWORD);
    }

    private void sendNotification(String email, Notification notification) {
        mailSender.sendMail(email, notification.getSubject(), notification.getMessage());
        auditService.create(new AuditDto("user_service", "sending successful notification", new Date()));
    }

    private void sendNotification(String email, Notification notification, String link) {
        String message = notification.getMessage().concat(link);
        mailSender.sendMail(email, notification.getSubject(), message);
        auditService.create(new AuditDto("user_service", "sending successful notification", new Date()));
    }
}
