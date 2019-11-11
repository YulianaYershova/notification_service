package com.fitgoal.web.resources;

import com.codahale.metrics.annotation.Timed;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.UserVerification;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificatorResource {

    private final NotificationService notificationService;

    @Inject
    public NotificatorResource(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @POST
    @Path("/register")
    @Timed
    public void register(@NotNull @Valid UserVerification userVerification) {
        notificationService.register(userVerification);
    }

    @POST
    @Path("/register/success")
    @Timed
    public void registerSuccess(@NotNull @Valid Recipient recipient) {
        notificationService.registerSuccess(recipient);
    }

    @POST
    @Path("/resetPassword")
    @Timed
    public void resetPassword(@NotNull @Valid UserVerification userVerification) {
        notificationService.resetPassword(userVerification);
    }

    @POST
    @Path("/resetPassword/success")
    @Timed
    public void resetPasswordSuccess(@NotNull @Valid Recipient recipient) {
        notificationService.resetPasswordSuccess(recipient);
    }
}
