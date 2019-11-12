package com.fitgoal;

import com.fitgoal.api.domain.UserVerification;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.eclipse.jetty.http.HttpMethod;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class NotificationClientTest {

    private static final String REGISTER = "register";
    private static final String RESET_PASSWORD = "resetPassword";
    private static final String SUCCESS = "success";

    @Rule
    public MockWebServer mockWebServer = new MockWebServer();
    private HttpUrl resourceRoot =  mockWebServer.url("http://localhost:9090/notifications");
    private NotificationClient notificationClient = new NotificationClient(new OkHttpClient(), resourceRoot);

    @Test
    public void register() throws InterruptedException, IOException {
        UserVerification userVerification = new UserVerification("test@test.com", "link");
        notificationClient.register(userVerification);
        // verify the request was sent
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.POST);
        assertThat(recordedRequest.getRequestUrl()).isEqualTo(buildSubresourceHttpUrl(REGISTER));
    }

    @Test
    public void registerSuccess() {
    }

    @Test
    public void resetPassword() {
    }

    @Test
    public void resetPasswordSuccess() {
    }

    private HttpUrl buildSubresourceHttpUrl(String... subresourcePaths) {
        HttpUrl.Builder builder = resourceRoot.newBuilder();
        Arrays.stream(subresourcePaths).forEach(builder::addPathSegment);
        return builder.build();
    }
}