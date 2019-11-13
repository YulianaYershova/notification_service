package com.fitgoal.web.config;

import com.fitgoal.service.config.MailerConfiguration;
import com.fitgoal.dao.config.MongoDBConfiguration;
import com.fitgoal.service.config.SenderConfiguration;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationServiceConfiguration extends Configuration {

    private MailerConfiguration mailerConfiguration;
    private MongoDBConfiguration mongoDBConfiguration;
    private SenderConfiguration senderConfiguration;

}
