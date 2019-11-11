package com.fitgoal.web.resources;

import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.UserVerification;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;

import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class NotificatorResourceTest {

    private static final String RESOURCE_PATH = "/notifications";
    private static final NotificationService notificationService = mock(NotificationService.class);
    public static final ResourceExtension resourceExtension = ResourceExtension.builder().addResource(new NotificatorResource(notificationService)).build();

    @BeforeClass
    public static void setUp() throws Throwable {
        resourceExtension.before();
    }

    @AfterClass
    public static void tearDown() throws Throwable {
        resourceExtension.after();
    }

    @Test
    public void registerTest() {
        UserVerification userVerification = new UserVerification("test@test.com", "link");
        resourceExtension.target(RESOURCE_PATH + "/register")
                .request()
                .method("POST", Entity.json(userVerification));
        verify(notificationService, times(1)).register(any(UserVerification.class));
    }

    @Test
    public void registerSuccessTest() {
    }

    @Test
    public void resetPasswordTest() {
    }

    @Test
    public void resetPasswordSuccessTest() {
    }
}