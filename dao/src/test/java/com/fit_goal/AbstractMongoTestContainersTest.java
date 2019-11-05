package com.fit_goal;

import com.github.dockerjava.api.model.HostConfig;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.ClassRule;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;

import org.testcontainers.containers.GenericContainer;


import java.util.Collections;
import java.util.function.Consumer;

public abstract class AbstractMongoTestContainersTest {
    private static final String MONGO_VERSION = "4.2.1";
    private static final int MONGO_PORT = 27017;
    private static final int MONGO_HOST_PORT = 27017;

/*    private static Consumer<CreateContainerCmd> portBinding = e -> e.withPortBindings(
            new PortBinding(
                    Ports.Binding.bindPort(MONGO_HOST_PORT),
                    new ExposedPort(MONGO_PORT)
            )
    );*/

    static Consumer<CreateContainerCmd> hostConfig = e -> e.withHostConfig(
            HostConfig.newHostConfig().withPortBindings(
                    new PortBinding(Ports.Binding.bindPort(MONGO_HOST_PORT),
                            new ExposedPort(MONGO_PORT))).withPublishAllPorts(true));


    @ClassRule
    public static final GenericContainer mongo = new GenericContainer("mongo:" + MONGO_VERSION)
            .withExposedPorts(MONGO_PORT)
            .withCreateContainerCmdModifier(hostConfig);

    MongoClient getClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(
                                new ServerAddress(
                                        mongo.getContainerIpAddress(),
                                        mongo.getMappedPort(MONGO_PORT)))))
                .build();

        return MongoClients.create(settings);
    }
}
