package com.fit_goal;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class NotificationServiceApplication extends Application<NotificationServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new NotificationServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public void initialize(Bootstrap<NotificationServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(NotificationServiceConfiguration notificationServiceConfiguration, Environment environment) throws Exception {
        // nothing to do yet
    }


}
