package com.fit_goal;

import com.fit_goal.api.EventRegistrar;
import com.fit_goal.api.Notificator;
import com.fit_goal.config.NotificationServiceConfiguration;
import com.fit_goal.domain.EventDto;
import com.fit_goal.impl.EventDaoImpl;
import com.fit_goal.resources.NotificatorResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.internal.inject.AbstractBinder;

import javax.inject.Singleton;


public class NotificationServiceApplication extends Application<NotificationServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new NotificationServiceApplication().run(args);
    }

    private final HibernateBundle<NotificationServiceConfiguration> hibernate = new HibernateBundle<NotificationServiceConfiguration>(EventDto.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(NotificationServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "notification_service";
    }

    @Override
    public void initialize(Bootstrap<NotificationServiceConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(NotificationServiceConfiguration notificationServiceConfiguration, Environment environment) {
        EventDaoImpl eventDao = new EventDaoImpl(hibernate.getSessionFactory());
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(NotificatorService.class).to(Notificator.class).in(Singleton.class);
                bind(EventRegistrarService.class).to(EventRegistrar.class).in(Singleton.class);
                bind(eventDao).to(EventDao.class).in(Singleton.class);
            }
        });
        environment.jersey().register(NotificatorResource.class);
    }

}
