package com.fit_goal.api;

import com.fit_goal.domain.User;


public interface Notificator {
    /**
     * Sends verification link when user tries to register or reset password
     */
    void sendLink(User user);

    /**
     * Sends notifications to user about success operations
     */
    void sendNotification(User user);
}
