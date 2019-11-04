package com.fit_goal.api;

import com.fit_goal.domain.UserVerification;

public interface Notificator {
    /**
     * Sends verification link when user tries to register
     */
    void register(UserVerification userVerification);

    /**
     * Sends notifications to user about successful registration
     */
    void registerSuccess(String email);

    /**
     * Sends verification link when user tries to reset password
     */
    void resetPassword(UserVerification userVerification);

    /**
     * Sends notifications to user about successful password resetting
     */
    void resetPasswordSuccess(String email);
}
