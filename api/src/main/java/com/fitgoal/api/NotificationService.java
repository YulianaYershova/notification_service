package com.fitgoal.api;

import com.fitgoal.api.domain.UserVerification;

public interface NotificationService {
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
