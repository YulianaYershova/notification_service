package com.fitgoal.service.impl;

import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.api.domain.UserVerification;
import com.fitgoal.service.enums.Notification;
import com.fitgoal.service.util.MailSender;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class NotificationServiceImpl implements NotificationService {

    private final AuditDao auditDao;
    private final MailSender mailSender;

    @Inject
    public NotificationServiceImpl(AuditDao auditDao, MailSender mailSender) {
        this.auditDao = auditDao;
        this.mailSender = mailSender;
    }

    @Override
    public void register(UserVerification userVerification) {
        String link = "http://localhost:9191/verify/".concat(userVerification.getVerificationLink());
        sendVerificationLink(userVerification.getEmail(), Notification.REGISTER, link);
    }

    @Override
    public void registerSuccess(Recipient recipient) {
        sendSuccessfulNotification(recipient.getEmail(), Notification.SUCCESS_REGISTRATION);
    }

    @Override
    public void resetPassword(UserVerification userVerification) {
        String link = "http://localhost:9191/verify/".concat(userVerification.getVerificationLink());
        sendVerificationLink(userVerification.getEmail(), Notification.RESET_PASSWORD, link);
    }

    @Override
    public void resetPasswordSuccess(Recipient recipient) {
        sendSuccessfulNotification(recipient.getEmail(), Notification.SUCCESS_RESET_PASSWORD);
    }

    private void sendSuccessfulNotification(String email, Notification notification) {
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
                .date(LocalDateTime.now()).build();
        auditDao.create(auditDto);
    }
}
