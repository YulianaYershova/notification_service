package com.fitgoal;

import java.net.URI;
import java.util.function.Consumer;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.containers.GenericContainer;

import com.fitgoal.web.NotificationServiceApplication;
import com.fitgoal.web.config.NotificationServiceConfiguration;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.ConfigOverride;

import okhttp3.OkHttpClient;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AbstractIntegrationTest {

    private static final String CONFIG_PATH = "../config.yml";
    private static final String MONGO_VERSION = "4.2.1";
    private static final int MONGO_PORT = 27017;
    private static final int MONGO_HOST_PORT = 27019;

    private static final Consumer<CreateContainerCmd> hostConfig = containerCmd -> containerCmd.withHostConfig(
            HostConfig.newHostConfig().withPortBindings(
                    new PortBinding(Ports.Binding.bindPort(MONGO_HOST_PORT),
                            new ExposedPort(MONGO_PORT))).withPublishAllPorts(true));

    protected static DropwizardAppExtension<NotificationServiceConfiguration> APP_EXTENSION;
    protected final URI resourcePath = URI.create("http://localhost:" + APP_EXTENSION.getLocalPort() + "/notifications");
    protected final OkHttpClient okHttpClient = new OkHttpClient();

    @ClassRule
    public static final GenericContainer mongoContainer = new GenericContainer("mongo:" + MONGO_VERSION)
            .withExposedPorts(MONGO_PORT)
            .withCreateContainerCmdModifier(hostConfig);

    @BeforeClass
    public static void beforeClass() throws Exception {
        APP_EXTENSION =
                new DropwizardAppExtension<>(
                        NotificationServiceApplication.class,
                        CONFIG_PATH,
                        ConfigOverride.config("mongoDBConfiguration.host", mongoContainer.getContainerIpAddress()),
                        ConfigOverride.config("mongoDBConfiguration.port", String.valueOf(MONGO_HOST_PORT))
                );
        APP_EXTENSION.before();
    }
}

