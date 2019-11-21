package com.fitgoal;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;

import static org.assertj.core.api.Assertions.assertThat;

public class NotificationServiceIntegrationTest extends AbstractIntegrationTest {

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
    public void registerIllegalArgumentsTest() {
        UserVerification userVerification = new UserVerification();
        Response response = APP_EXTENSION.client()
                .target(resourcePath)
                .path("/register")
                .request()
                .method("POST", Entity.json(userVerification));

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(422);
    }

    @Test
    public void registerIncorrectEmailTest() {
        UserVerification userVerification = new UserVerification("testtest.com", "link");

        Response response = APP_EXTENSION.client()
                .target(resourcePath)
                .path("/register")
                .request()
                .method("POST", Entity.json(userVerification));

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(422);
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
