package com.fit_goal.resources;

import com.codahale.metrics.annotation.Timed;
import com.fit_goal.api.EventRegistrar;
import com.fit_goal.api.Notificator;
import com.fit_goal.domain.Event;
import com.fit_goal.domain.User;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/notifications")
@Consumes(MediaType.APPLICATION_JSON)
public class NotificatorResource {

    private final Notificator notificator;
    private final EventRegistrar eventRegistrar;

    @Inject
    public NotificatorResource(Notificator notificator, EventRegistrar eventRegistrar) {
        this.notificator = notificator;
        this.eventRegistrar = eventRegistrar;
    }

    @POST
    @Path("/register")
    @Timed
    public Response register(User user) {
        notificator.sendLink(user);
        eventRegistrar.registerEvent(new Event());
        return Response.ok().build();
    }

    @POST
    @Path("/register/success")
    @Timed
    @UnitOfWork
    public Response registerSuccess(User user) {
        return Response.ok().build();
    }

    @POST
    @Path("/resetPassword")
    @Timed
    public Response resetPassword(User user) {
        return Response.ok().build();
    }

    @POST
    @Path("/resetPassword/success")
    @Timed
    public Response resetPasswordSuccess(User user) {
        return Response.ok().build();
    }
}
