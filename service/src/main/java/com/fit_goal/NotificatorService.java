package com.fit_goal;

import com.fit_goal.api.Notificator;
import com.fit_goal.domain.User;
import com.fit_goal.util.MailSender;

public class NotificatorService implements Notificator {



    @Override
    public void sendLink(User user) {
        MailSender mailSender = new MailSender();
        mailSender.sendMail(user.getEmail(),"subject","text");
    }

    @Override
    public void sendNotification(User user) {

    }
}
