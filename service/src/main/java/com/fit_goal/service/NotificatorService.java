package com.fit_goal.service;

import com.fit_goal.api.EventRegistrar;
import com.fit_goal.api.Notificator;
import com.fit_goal.domain.EventDto;
import com.fit_goal.domain.User;
import com.fit_goal.MailSender;
import com.fit_goal.util.*;

import javax.inject.Inject;
import java.time.LocalDateTime;


public class NotificatorService implements Notificator {

    private final MailSender mailSender;
    private final EventRegistrar eventRegistrar;

    @Inject
    public NotificatorService(MailSender mailSender, EventRegistrar eventRegistrar) {
        this.mailSender = mailSender;
        this.eventRegistrar = eventRegistrar;
    }

    @Override
    public void sendLink(User user, Notification notification) {
        String link = "user_service/action/verify/" + user.getVerificationLink();
        mailSender.sendMail(user.getEmail(), notification.getSubject().getValue(), notification.getMessage().getValue() + link);
        eventRegistrar.registerEvent(new EventDto("user_service", "sending verification link", LocalDateTime.now()));
    }

    @Override
    public void sendNotification(String email, Notification notification) {
        mailSender.sendMail(email, notification.getSubject().getValue(), notification.getMessage().getValue());
        eventRegistrar.registerEvent(new EventDto("user_service", "sending successful notification", LocalDateTime.now()));
    }
}