package com.fit_goal;

import com.fit_goal.api.EventRegistrar;
import com.fit_goal.api.Notificator;
import com.fit_goal.config.NotificationServiceConfiguration;
import com.fit_goal.domain.EventDto;
import com.fit_goal.impl.EventDaoImpl;
import com.fit_goal.resources.NotificatorResource;
import com.fit_goal.service.EventRegistrarService;
import com.fit_goal.service.NotificatorService;
import com.fit_goal.impl.MailSenderImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.bson.Document;
import com.mongodb.client.MongoCollection;


import javax.inject.Singleton;


public class NotificationServiceApplication extends Application<NotificationServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new NotificationServiceApplication().run(args);
    }

   /* private final HibernateBundle<NotificationServiceConfiguration> hibernate = new HibernateBundle<NotificationServiceConfiguration>(EventDto.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(NotificationServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };*/

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
        MongoClient mongoClient= new MongoClient(configuration.getMongoHost(), configuration.getMongoPort());
        MongoDBManaged mongoDBManaged = new MongoDBManaged(mongoClient);
        MongoDatabase db = mongoClient.getDatabase(configuration.getMongoDB());
        MongoCollection<Document> collection = db.getCollection(configuration.getCollectionName());
       /* EventDaoImpl eventDaoImpl = new UnitOfWorkAwareProxyFactory(hibernate).create(EventDaoImpl.class, SessionFactory.class, hibernate.getSessionFactory());*/
        EventDaoImpl eventDao = new EventDaoImpl(collection);
        eventDao.find();
        environment.lifecycle().manage(mongoDBManaged);
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(NotificatorService.class).to(Notificator.class).in(Singleton.class);
                bind(EventRegistrarService.class).to(EventRegistrar.class).in(Singleton.class);
               /* bind(eventDaoImpl).to(EventDao.class).in(Singleton.class);*/
                bind(MailSenderImpl.class).to(MailSender.class).in(Singleton.class);
            }
        });
        environment.jersey().register(NotificatorResource.class);
    }

}
