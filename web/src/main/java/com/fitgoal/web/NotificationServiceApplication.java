package com.fitgoal.web;

import com.fitgoal.dao.AuditDao;
import com.fitgoal.dao.impl.AuditDaoImpl;
import com.fitgoal.service.config.MailerConfiguration;
import com.fitgoal.service.mail.MailSender;
import com.fitgoal.service.mail.impl.MailSenderImpl;
import com.fitgoal.web.config.NotificationServiceConfiguration;
import com.fitgoal.web.resources.NotificationResource;
import com.fitgoal.service.impl.NotificationServiceImpl;
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
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

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

        final Mailer mailer = configureMailer(configuration.getMailerConfiguration());

        jersey.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(configuration.getUserServiceConfiguration());
                bind(NotificationServiceImpl.class)
                        .to(NotificationService.class)
                        .in(Singleton.class);
                bind(AuditDaoImpl.class)
                        .to(AuditDao.class)
                        .in(Singleton.class);
                bind(MongoClients.create(getMongoClientSettings(configuration)))
                        .to(MongoClient.class)
                        .in(Singleton.class);
                bind(configuration.getSenderConfiguration());
                bind(mailer).to(Mailer.class);
                bind(MailSenderImpl.class)
                        .to(MailSender.class)
                        .in(Singleton.class);
            }
        });
        jersey.register(NotificationResource.class);
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
    private Mailer configureMailer(MailerConfiguration mailerConfiguration){
        return MailerBuilder
                .withSMTPServer(mailerConfiguration.getHost(),
                        mailerConfiguration.getPort(),
                        mailerConfiguration.getUsername(),
                        mailerConfiguration.getPassword())
                .buildMailer();
    }
}
