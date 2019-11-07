package com.fitgoal.service.impl;

import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.api.domain.UserVerification;
import com.fitgoal.service.AuditService;
import com.fitgoal.service.enums.Notification;
import com.fitgoal.service.util.MailSender;

import javax.inject.Inject;
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
        String link = "http://localhost:9191/verify/".concat(userVerification.getVerificationLink());
        sendVerificationLink(userVerification.getEmail(), Notification.REGISTER, link);
    }

    @Override
    public void registerSuccess(Recipient recipient) {
        sendNotification(recipient.getEmail(), Notification.SUCCESS_REGISTRATION);
    }

    @Override
    public void resetPassword(UserVerification userVerification) {
        String link = "http://localhost:9191/verify/".concat(userVerification.getVerificationLink());
        sendVerificationLink(userVerification.getEmail(), Notification.RESET_PASSWORD, link);
    }

    @Override
    public void resetPasswordSuccess(Recipient recipient) {
        sendNotification(recipient.getEmail(), Notification.SUCCESS_RESET_PASSWORD);
    }

    private void sendNotification(String email, Notification notification) {
        mailSender.sendMail(email, notification.getSubject(), notification.getMessage());
        registerEvent("user_service", "sending successful notification");
    }

    private void sendVerificationLink(String email, Notification notification, String link) {
        String message = notification.getMessage().concat(link);
        mailSender.sendMail(email, notification.getSubject(), message);
        registerEvent("user_service", "sending verification link");
    }

    private void registerEvent(String serviceName, String event) {
        AuditDto auditDto = AuditDto.builder()
                .serviceName(serviceName)
                .event(event)
                .date(new Date()).build();
        auditService.create(auditDto);
    }
}
