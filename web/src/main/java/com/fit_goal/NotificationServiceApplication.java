package com.fit_goal;

import com.fit_goal.api.EventRegistrar;
import com.fit_goal.api.Notificator;
import com.fit_goal.config.NotificationServiceConfiguration;
import com.fit_goal.impl.EventDaoImpl;
import com.fit_goal.resources.NotificatorResource;
import com.fit_goal.service.EventRegistrarService;
import com.fit_goal.service.NotificatorService;
import com.fit_goal.impl.MailSenderImpl;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.internal.inject.AbstractBinder;


import javax.inject.Singleton;


public class NotificationServiceApplication extends Application<NotificationServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new NotificationServiceApplication().run(args);
    }
    @Override
    public String getName() {
        return "notification_service";
    }

    @Override
    public void initialize(Bootstrap<NotificationServiceConfiguration> bootstrap) {
        /*  bootstrap.addBundle(hibernate);*/
    }

    @Override
    public void run(NotificationServiceConfiguration configuration, Environment environment) {
        MongoClient mongoClient = MongoClients.create();

        MongoDBManaged mongoDBManaged = new MongoDBManaged(mongoClient);
        /*   eventDao.insertOne();*/
        environment.lifecycle().manage(mongoDBManaged);
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(NotificatorService.class)
                        .to(Notificator.class)
                        .in(Singleton.class);
                bind(EventRegistrarService.class)
                        .to(EventRegistrar.class)
                        .in(Singleton.class);
                bind(MailSenderImpl.class)
                        .to(MailSender.class)
                        .in(Singleton.class);
                bind(EventDaoImpl.class)
                        .to(EventDao.class)
                        .in(Singleton.class);
            }
        });
        environment.jersey().register(NotificatorResource.class);
    }

}
