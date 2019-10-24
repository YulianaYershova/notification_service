package com.fit_goal.api;


import com.fit_goal.domain.User;
import com.fit_goal.util.*;


public interface Notificator {
    /**
     * Sends verification link when user tries to register or reset password
     */
    void sendLink(User user, Notification notification);

    /**
     * Sends notifications to user about success operations
     */
    void sendNotification(String email, Notification notification);
}
