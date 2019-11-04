package com.fit_goal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class MongoDBConfiguration {
    @NotNull
    private String host;
    @NotNull
    private int port;
}
