package com.fit_goal;

import com.fit_goal.api.Notificator;
import com.fit_goal.config.NotificationServiceConfiguration;
import com.fit_goal.impl.AuditDaoImpl;
import com.fit_goal.resources.NotificatorResource;
import com.fit_goal.service.AuditService;
import com.fit_goal.service.NotificatorService;
import com.fit_goal.util.MailSender;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.internal.inject.AbstractBinder;

import javax.inject.Singleton;

import java.util.Collections;

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
        JerseyEnvironment jersey = environment.jersey();
        jersey.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(NotificatorService.class)
                        .to(Notificator.class)
                        .in(Singleton.class);
                bind(AuditService.class)
                        .to(Audit.class)
                        .in(Singleton.class);
                bind(AuditDaoImpl.class)
                        .to(AuditDao.class)
                        .in(Singleton.class);
                bind(MongoClients.create(getMongoClientSettings(configuration)))
                        .to(MongoClient.class)
                        .in(Singleton.class);
                bind(new MailSender(configuration.getMailerConfiguration()));
            }
        });
        jersey.register(NotificatorResource.class);
    }

    private MongoClientSettings getMongoClientSettings(NotificationServiceConfiguration configuration) {
        return MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(
                                new ServerAddress(
                                        configuration.getMongoDBConfiguration().getHost(),
                                        configuration.getMongoDBConfiguration().getPort()))))
                .build();
    }
}
