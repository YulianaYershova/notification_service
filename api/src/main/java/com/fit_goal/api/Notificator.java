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

   /* *//**
     * Sends verification link when user tries to register
     *//*
    void register(UserVerification userVerification, Notification notification);

    *//**
     * Sends notifications to user about successful registration
     *//*
    void registerSuccess(String email, Notification notification);

    *//**
     * Sends verification link when user tries to reset password
     *//*
    void resetPassword (UserVerification userVerification, Notification notification);

    *//**
     * Sends notifications to user about successful password resetting
     *//*
    void resetPasswordSuccess(String email, Notification notification);*/
}
