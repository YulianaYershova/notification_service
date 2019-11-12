package com.fitgoal.web.resources;

import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(DropwizardExtensionsSupport.class)
public class NotificationResourceTest {

    private static final String RESOURCE_PATH = "/notifications";
    private static final NotificationService notificationService = mock(NotificationService.class);

    private static final ResourceExtension resource = ResourceExtension.builder()
            .addResource(new NotificationResource(notificationService))
            .build();

    @BeforeClass
    public static void setUp() throws Throwable {
        resource.before();
    }

    @AfterClass
    public static void tearDown() throws Throwable {
        resource.after();
    }

    @Test
    public void registerTest() {
        UserVerification userVerification = new UserVerification("test@test.com", "link");
        Response response = resource.target(RESOURCE_PATH)
                .path("/register")
                .request()
                .method("POST", Entity.json(userVerification));
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(204);
        verify(notificationService).register(any(UserVerification.class));
        verifyNoMoreInteractions(notificationService);
    }

    @Test
    public void registerSuccessTest() {
        Recipient recipient = new Recipient("test@test.com");
        Response response = resource.target(RESOURCE_PATH)
                .path("register/success")
                .request()
                .method("POST", Entity.json(recipient));
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(204);
        verify(notificationService).registerSuccess(any(Recipient.class));
        verifyNoMoreInteractions(notificationService);
    }

    @Test
    public void resetPasswordTest() {
        UserVerification userVerification = new UserVerification("test@test.com", "link");
        Response response = resource.target(RESOURCE_PATH)
                .path("/resetPassword")
                .request()
                .method("POST", Entity.json(userVerification));
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(204);
        verify(notificationService).resetPassword(any(UserVerification.class));
        verifyNoMoreInteractions(notificationService);
    }

    @Test
    public void resetPasswordSuccessTest() {
        Recipient recipient = new Recipient("test@test.com");
        Response response = resource.target(RESOURCE_PATH)
                .path("/resetPassword/success")
                .request()
                .method("POST", Entity.json(recipient));
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(204);
        verify(notificationService).resetPasswordSuccess(any(Recipient.class));
        verifyNoMoreInteractions(notificationService);
    }

}