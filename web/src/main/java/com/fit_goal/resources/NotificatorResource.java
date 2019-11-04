package com.fit_goal.resources;

import com.codahale.metrics.annotation.Timed;
import com.fit_goal.domain.Recipient;
import com.fit_goal.enums.Notification;
import com.fit_goal.api.Notificator;
import com.fit_goal.domain.UserVerification;

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

    private final Notificator notificator;

    @Inject
    public NotificatorResource(Notificator notificator) {
        this.notificator = notificator;
    }

    @POST
    @Path("/register")
    @Timed
    public Response register(@NotNull @Valid UserVerification userVerification) {
        notificator.register(userVerification);
        return Response.ok().build();
    }

    @POST
    @Path("/register/success")
    @Timed
    public Response registerSuccess(@NotNull @Valid Recipient recipient) {
        notificator.registerSuccess(recipient.getEmail());
        return Response.ok().build();
    }

    @POST
    @Path("/resetPassword")
    @Timed
    public Response resetPassword(@NotNull @Valid UserVerification userVerification) {
        notificator.resetPassword(userVerification);
        return Response.ok().build();
    }

    @POST
    @Path("/resetPassword/success")
    @Timed
    public Response resetPasswordSuccess(@NotNull @Valid Recipient recipient) {
        notificator.resetPasswordSuccess(recipient.getEmail());
        return Response.ok().build();
    }
}
