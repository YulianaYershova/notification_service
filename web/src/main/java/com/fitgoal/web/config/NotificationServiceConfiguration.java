package com.fitgoal.web.config;

import com.fitgoal.service.config.MailerConfiguration;
import com.fitgoal.dao.config.MongoDBConfiguration;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationServiceConfiguration extends Configuration {

    private MailerConfiguration mailerConfiguration;
    private MongoDBConfiguration mongoDBConfiguration;

    public MailerConfiguration getMailerConfiguration() {
        return mailerConfiguration;
    }

    public void setMailConfiguration(final MailerConfiguration mailConfiguration) {
        this.mailerConfiguration = mailConfiguration;
    }

    public MongoDBConfiguration getMongoDBConfiguration() {
        return mongoDBConfiguration;
    }

    public void setMongoDBConfiguration(final MongoDBConfiguration mongoDBConfiguration) {
        this.mongoDBConfiguration = mongoDBConfiguration;
    }
}
