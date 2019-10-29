package com.fit_goal.api;


import com.fit_goal.Notification;
import com.fit_goal.domain.UserVerification;


public interface Notificator {
    /**
     * Sends verification link when user tries to register or reset password
     */
    void sendVerificationLink(UserVerification userVerification, Notification notification);

    /**
     * Sends notifications to user about success operations
     */
    void sendNotification(String email, Notification notification);
}
