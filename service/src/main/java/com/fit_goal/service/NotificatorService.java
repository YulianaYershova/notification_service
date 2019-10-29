package com.fit_goal.service;

import com.fit_goal.EventRegistrar;
import com.fit_goal.Notification;
import com.fit_goal.api.Notificator;
import com.fit_goal.domain.EventDto;
import com.fit_goal.domain.UserVerification;
import com.fit_goal.util.*;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.time.LocalDateTime;


public class NotificatorService implements Notificator {

    private final EventRegistrar eventRegistrar;

    @Inject
    public NotificatorService(EventRegistrar eventRegistrar) {
        this.eventRegistrar = eventRegistrar;
    }

    @Override
    public void sendVerificationLink(UserVerification userVerification, Notification notification) {
        String link = "user_service/action/verify/" + userVerification.getVerificationLink();
        MailSender.sendMail(userVerification.getEmail(), notification.getSubject().getValue(), notification.getMessage().getValue() + link);
        eventRegistrar.create(new EventDto("user_service", "sending verification link", LocalDateTime.now()));
    }

    @Override
    public void sendNotification(String email, Notification notification) {
        MailSender.sendMail(email, notification.getSubject().getValue(), notification.getMessage().getValue());
        eventRegistrar.create(new EventDto("user_service", "sending successful notification", LocalDateTime.now()));
    }
}