package com.fit_goal;

import com.fit_goal.api.Notificator;
import com.fit_goal.config.NotificationServiceConfiguration;
import com.fit_goal.impl.EventDaoImpl;
import com.fit_goal.resources.NotificatorResource;
import com.fit_goal.service.EventRegistrarService;
import com.fit_goal.service.NotificatorService;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ClusterSettings;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.internal.inject.AbstractBinder;


import javax.inject.Singleton;
import java.util.Arrays;


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
    }

    @Override
    public void run(NotificationServiceConfiguration configuration, Environment environment) {
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(NotificatorService.class)
                        .to(Notificator.class)
                        .in(Singleton.class);
                bind(EventRegistrarService.class)
                        .to(EventRegistrar.class)
                        .in(Singleton.class);
                bind(EventDaoImpl.class)
                        .to(EventDao.class)
                        .in(Singleton.class);
                bind(MongoClients.create()) //creat MongoClients with default parameters: localhost:27017
                        .to(MongoClient.class)
                        .in(Singleton.class);
            }
        });
        environment.jersey().register(NotificatorResource.class);
    }

}
