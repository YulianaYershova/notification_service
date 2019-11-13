package com.fitgoal;

import com.fitgoal.api.domain.UserVerification;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.HttpMethod;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class NotificationClientTest {

    private static final String REGISTER = "register";
    private static final String RESET_PASSWORD = "resetPassword";
    private static final String SUCCESS = "success";

    @Rule
    public MockWebServer mockWebServer = new MockWebServer();
    private HttpUrl resourceRoot = mockWebServer.url("/");
    private NotificationClient notificationClient = new NotificationClient(new OkHttpClient(), resourceRoot);

    @Test
    public void register() throws InterruptedException, IOException {
        UserVerification userVerification = new UserVerification("test@test.com", "link");

        mockWebServer.enqueue(new MockResponse().setResponseCode(204));
        notificationClient.register(userVerification);
        // verify the request was sent
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest.getMethod()).isEqualTo(HttpMethod.POST);
    }

    @Test
    public void registerSuccess() throws InterruptedException {

    }

    @Test
    public void resetPassword() {
    }

    @Test
    public void resetPasswordSuccess() {
    }
  /*  private HttpUrl buildSubresourceHttpUrl(String... subresourcePaths) {
        HttpUrl.Builder builder = resourceRoot.newBuilder();
        Arrays.stream(subresourcePaths).forEach(builder::addPathSegment);
        return builder.build();
    }*/
}