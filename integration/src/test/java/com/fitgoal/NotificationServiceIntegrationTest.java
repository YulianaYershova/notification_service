package com.fitgoal;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;

import okhttp3.HttpUrl;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThat;

public class NotificationServiceIntegrationTest extends AbstractIntegrationTest {

    private static NotificationService notificationService;

    @Before
    public void setup() {
        notificationService = new NotificationClient(okHttpClient, HttpUrl.get(resourcePath));
    }

    @Test
    public void registerTest() {
        UserVerification userVerification = new UserVerification("test@test.com", "verification_link");
        Response response = APP_EXTENSION.client()
                .target(resourcePath)
                .path("/register")
                .request()
                .post(Entity.json(userVerification));

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void registerIncorrectEmailTest() {
        UserVerification userVerification = new UserVerification("testtest.com", "verification_link");
        assertThatIllegalArgumentException().isThrownBy(
                () -> notificationService.register(userVerification));
    }

    @Test
    public void registerEmptyFieldsTest() {
        UserVerification userVerification = new UserVerification();
        assertThatIllegalArgumentException().isThrownBy(
                () -> notificationService.register(userVerification));
    }

    @Test
    public void registerSuccessTest() {
        Recipient recipient = new Recipient("test@test.com");
        Response response = APP_EXTENSION.client()
                .target(resourcePath)
                .path("/register/success")
                .request()
                .post(Entity.json(recipient));

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void registerSuccessIncorrectEmailTest() {
        Recipient recipient = new Recipient("testtest.com");
        assertThatIllegalArgumentException().isThrownBy(
                () -> notificationService.registerSuccess(recipient));
    }

    @Test
    public void registerSuccessEmptyFieldsTest() {
        Recipient recipient = new Recipient();
        assertThatIllegalArgumentException().isThrownBy(
                () -> notificationService.registerSuccess(recipient));
    }

    @Test
    public void resetPasswordTest() {
        UserVerification userVerification = new UserVerification("test@test.com", "verification_link");
        Response response = APP_EXTENSION.client()
                .target(resourcePath)
                .path("/resetPassword")
                .request()
                .post(Entity.json(userVerification));

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void resetPasswordSuccessTest() {
        Recipient recipient = new Recipient("test@test.com");
        Response response = APP_EXTENSION.client()
                .target(resourcePath)
                .path("/resetPassword/success")
                .request()
                .post(Entity.json(recipient));

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(204);
    }
}
