package com.fitgoal.service.impl;

import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.domain.AuditDto;
import com.fitgoal.api.domain.UserVerification;
import com.fitgoal.service.config.UserServiceConfiguration;
import com.fitgoal.service.enums.Notification;
import com.fitgoal.service.mail.MailSender;
import org.apache.http.client.utils.URIBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class NotificationServiceImpl implements NotificationService {

    private final AuditDao auditDao;
    private final MailSender mailSender;
    private final UserServiceConfiguration userServiceConfiguration;

    @Inject
    public NotificationServiceImpl(AuditDao auditDao, MailSender mailSender, UserServiceConfiguration userServiceConfiguration) {
        this.auditDao = auditDao;
        this.mailSender = mailSender;
        this.userServiceConfiguration = userServiceConfiguration;
    }

    @Override
    public void register(UserVerification userVerification) {
        URI uri = buildUri(userVerification.getVerificationLink());
        String message = Notification.REGISTER.getMessage() + uri;
        sendMessage(userVerification.getEmail(), Notification.REGISTER.getSubject(), message);
        registerEvent("user_service", "sending verification link");
    }

    @Override
    public void registerSuccess(Recipient recipient) {
        String subject = Notification.SUCCESS_REGISTRATION.getSubject();
        String message = Notification.SUCCESS_REGISTRATION.getMessage();
        sendMessage(recipient.getEmail(), subject, message);
        registerEvent("user_service", "sending successful notification");
    }

    @Override
    public void resetPassword(UserVerification userVerification) {
        URI uri = buildUri(userVerification.getVerificationLink());
        String message = Notification.RESET_PASSWORD.getMessage() + uri;
        sendMessage(userVerification.getEmail(), Notification.RESET_PASSWORD.getSubject(), message);
        registerEvent("user_service", "sending verification link");
    }

    @Override
    public void resetPasswordSuccess(Recipient recipient) {
        String subject = Notification.SUCCESS_RESET_PASSWORD.getSubject();
        String message = Notification.SUCCESS_RESET_PASSWORD.getMessage();
        sendMessage(recipient.getEmail(), subject, message);
        registerEvent("user_service", "sending successful notification");
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

    private URI buildUri(String verificationLink) {
        try {
            return new URIBuilder()
                    .setScheme(userServiceConfiguration.getScheme())
                    .setHost(userServiceConfiguration.getHost())
                    .setPort(userServiceConfiguration.getPort())
                    .setPathSegments("verify", verificationLink).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
