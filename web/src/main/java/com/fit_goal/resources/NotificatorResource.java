package com.fit_goal.resources;

import com.codahale.metrics.annotation.Timed;
import com.fit_goal.util.Notification;
import com.fit_goal.util.enums.Message;
import com.fit_goal.api.Notificator;
import com.fit_goal.domain.User;

import com.fit_goal.util.enums.Subject;
import io.dropwizard.hibernate.UnitOfWork;

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
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificatorResource {

    private final Notificator notificator;

    @Inject
    public NotificatorResource(Notificator notificator) {
        this.notificator = notificator;
    }

    @POST
    @Path("/register")
    @Timed
    public Response register(@NotNull @Valid User user) {
        Notification notification = new Notification(Subject.REGISTER_SUBJECT, Message.REGISTER_MESSAGE);
        notificator.sendLink(user, notification);
        return Response.ok().build();
    }

    @POST
    @Path("/register/success")
    @Timed
    @UnitOfWork
    public Response registerSuccess(@NotNull @Valid User user) {
        Notification notification = new Notification(Subject.SUCCESS_REGISTRATION_SUBJECT, Message.SUCCESS_REGISTRATION_MESSAGE);
        notificator.sendNotification(user.getEmail(), notification);
        return Response.ok().build();
    }

    @POST
    @Path("/resetPassword")
    @Timed
    public Response resetPassword(@NotNull @Valid User user) {
        Notification notification = new Notification(Subject.RESET_PASSWORD_SUBJECT, Message.RESET_PASSWORD_MESSAGE);
        notificator.sendLink(user, notification);
        return Response.ok().build();
    }

    @POST
    @Path("/resetPassword/success")
    @Timed
    public Response resetPasswordSuccess(@NotNull @Valid User user) {
        Notification notification = new Notification(Subject.SUCCESS_RESET_PASSWORD_SUBJECT, Message.SUCCESS_RESET_PASSWORD_MESSAGE);
        notificator.sendNotification(user.getEmail(), notification);
        return Response.ok().build();
    }
}
