package com.fitgoal;

import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.HttpMethod;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.jetty.http.HttpStatus.NO_CONTENT_204;

public class NotificationClientTest {

    private static final String NOTIFICATIONS = "notifications";
    private static final String REGISTER = "register";
    private static final String RESET_PASSWORD = "resetPassword";
    private static final String CONFIRM = "confirm";

    @Rule
    public MockWebServer mockWebServer = new MockWebServer();
    private HttpUrl resourceRoot = mockWebServer.url("/");
    private NotificationClient notificationClient = new NotificationClient(new OkHttpClient(), resourceRoot);

    @Test
    public void register() throws InterruptedException {
        UserVerification userVerification = new UserVerification("test@test.com", "link");

        mockWebServer.enqueue(new MockResponse().setResponseCode(NO_CONTENT_204));

        notificationClient.register(userVerification);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        HttpUrl httpUrl = buildSubresourceHttpUrl(REGISTER);

        assertThat(recordedRequest.getRequestUrl()).isEqualTo(httpUrl);
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.POST);
    }

    @Test
    public void confirmRegistration() throws InterruptedException {
        Recipient recipient = new Recipient("test@test.com");

        mockWebServer.enqueue(new MockResponse().setResponseCode(NO_CONTENT_204));

        notificationClient.confirmRegistration(recipient);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        HttpUrl httpUrl = buildSubresourceHttpUrl(REGISTER, CONFIRM);

        assertThat(recordedRequest.getRequestUrl()).isEqualTo(httpUrl);
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.POST);
    }

    @Test
    public void resetPassword() throws InterruptedException {
        UserVerification userVerification = new UserVerification("test@test.com", "link");

        mockWebServer.enqueue(new MockResponse().setResponseCode(NO_CONTENT_204));

        notificationClient.resetPassword(userVerification);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        HttpUrl httpUrl = buildSubresourceHttpUrl(RESET_PASSWORD);

        assertThat(recordedRequest.getRequestUrl()).isEqualTo(httpUrl);
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.POST);
    }

    @Test
    public void confirmPasswordReset() throws InterruptedException {
        Recipient recipient = new Recipient("test@test.com");

        mockWebServer.enqueue(new MockResponse().setResponseCode(NO_CONTENT_204));

        notificationClient.confirmPasswordReset(recipient);
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        HttpUrl httpUrl = buildSubresourceHttpUrl(RESET_PASSWORD, CONFIRM);

        assertThat(recordedRequest.getRequestUrl()).isEqualTo(httpUrl);
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.POST);
    }

    private HttpUrl buildSubresourceHttpUrl(String... subresourcePaths) {
        HttpUrl.Builder builder = resourceRoot.newBuilder();
        builder.addPathSegment(NOTIFICATIONS);
        Arrays.stream(subresourcePaths).forEach(builder::addPathSegment);
        return builder.build();
    }
}
