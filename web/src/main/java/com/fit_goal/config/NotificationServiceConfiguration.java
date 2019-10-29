package com.fit_goal.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NotificationServiceConfiguration extends Configuration {

   /* @JsonProperty
    @NotEmpty
    private String mongoHost;

    @JsonProperty
    private int mongoPort;

    @JsonProperty
    @NotEmpty
    private String mongoDB;

    @JsonProperty
    @NotEmpty
    private String collectionName;*/

}
