package com.fitgoal.api;

import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;

public interface NotificationService {
    /**
     * Sends verification link when user tries to register
     */
    void register(UserVerification userVerification);

    /**
     * Sends notifications to user about successful registration
     */
    void confirmRegistration(Recipient recipient);

    /**
     * Sends verification link when user tries to reset password
     */
    void resetPassword(UserVerification userVerification);

    /**
     * Sends notifications to user about successful password resetting
     */
    void confirmPasswordReset(Recipient recipient);
}
