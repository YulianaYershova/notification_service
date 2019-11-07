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
        String message = Notification.REGISTER.getMessage().concat(link);
        sendMessage(userVerification.getEmail(), Notification.SUCCESS_REGISTRATION.getSubject(), message);
    }

    @Override
    public void registerSuccess(Recipient recipient) {
        String subject = Notification.SUCCESS_REGISTRATION.getSubject();
        String message = Notification.SUCCESS_REGISTRATION.getMessage();
        sendMessage(recipient.getEmail(), subject, message);
        registerEvent("user_service", "sending verification link");
    }

    @Override
    public void resetPassword(UserVerification userVerification) {
        String link = "http://localhost:9191/verify/".concat(userVerification.getVerificationLink());
        String message = Notification.RESET_PASSWORD.getMessage().concat(link);
        sendMessage(userVerification.getEmail(), Notification.SUCCESS_REGISTRATION.getSubject(), message);
        registerEvent("user_service", "sending verification link");
    }

    @Override
    public void resetPasswordSuccess(Recipient recipient) {
        String subject = Notification.RESET_PASSWORD.getSubject();
        String message = Notification.RESET_PASSWORD.getMessage();
        sendMessage(recipient.getEmail(), subject, message);
        registerEvent("user_service", "sending verification link");
    }

    private void sendMessage(String email, String subject, String message) {
        mailSender.sendMail(email, subject, message);
    }

    private void registerEvent(String serviceName, String event) {
        AuditDto auditDto = AuditDto.builder()
                .serviceName(serviceName)
                .event(event)
                .date(LocalDateTime.now()).build();
        auditDao.create(auditDto);
    }
}
