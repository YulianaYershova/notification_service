package com.fitgoal.web;

import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.impl.AuditDaoImpl;
import com.fitgoal.web.config.NotificationServiceConfiguration;
import com.fitgoal.web.resources.NotificatorResource;
import com.fitgoal.service.AuditService;
import com.fitgoal.service.impl.AuditServiceImpl;
import com.fitgoal.service.impl.NotificationServiceImpl;
import com.fitgoal.service.util.MailSender;
import com.fitgoal.api.NotificationService;
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
                bind(NotificationServiceImpl.class)
                        .to(NotificationService.class)
                        .in(Singleton.class);
                bind(AuditServiceImpl.class)
                        .to(AuditService.class)
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
