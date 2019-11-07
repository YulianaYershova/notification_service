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
import javax.ws.rs.core.Response;

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
    public Response register(@NotNull @Valid UserVerification userVerification) {
        notificationService.register(userVerification);
        return Response.ok().build();
    }

    @POST
    @Path("/register/success")
    @Timed
    public Response registerSuccess(@NotNull @Valid Recipient recipient) {
        notificationService.registerSuccess(recipient.getEmail());
        return Response.ok().build();
    }

    @POST
    @Path("/resetPassword")
    @Timed
    public Response resetPassword(@NotNull @Valid UserVerification userVerification) {
        notificationService.resetPassword(userVerification);
        return Response.ok().build();
    }

    @POST
    @Path("/resetPassword/success")
    @Timed
    public Response resetPasswordSuccess(@NotNull @Valid Recipient recipient) {
        notificationService.resetPasswordSuccess(recipient.getEmail());
        return Response.ok().build();
    }
}
