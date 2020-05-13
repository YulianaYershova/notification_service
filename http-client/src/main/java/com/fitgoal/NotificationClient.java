package com.fitgoal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;
import lombok.NonNull;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

/**
 * OkHttp HTTP client implementation of {@link NotificationService} API.
 */
public class NotificationClient extends BaseClient implements NotificationService {

    private static final String REGISTER = "register";
    private static final String RESET_PASSWORD = "resetPassword";
    private static final String CONFIRM = "confirm";

    public NotificationClient(OkHttpClient okHttpClient, HttpUrl resourceUrl) {
        this(okHttpClient, buildDefaultMapper(), resourceUrl);
    }

    public NotificationClient(@NonNull OkHttpClient okHttpClient, @NonNull ObjectMapper mapper, @NonNull HttpUrl resourceUrl) {
        super(okHttpClient, mapper, resourceUrl);
    }

    @Override
    public void register(UserVerification userVerification) {
        HttpUrl httpUrl = buildSubresourceHttpUrl(REGISTER);
        Request request = buildRequest(userVerification, httpUrl);
        executeRequest(request);
    }

    @Override
    public void confirmRegistration(Recipient recipient) {
        HttpUrl httpUrl = buildSubresourceHttpUrl(REGISTER, CONFIRM);
        Request request = buildRequest(recipient,httpUrl);
        executeRequest(request);
    }

    @Override
    public void resetPassword(UserVerification userVerification) {
        HttpUrl httpUrl = buildSubresourceHttpUrl(RESET_PASSWORD);
        Request request = buildRequest(userVerification, httpUrl);
        executeRequest(request);
    }

    @Override
    public void confirmPasswordReset(Recipient recipient) {
        HttpUrl httpUrl = buildSubresourceHttpUrl(RESET_PASSWORD, CONFIRM);
        Request request = buildRequest(recipient,httpUrl);
        executeRequest(request);
    }

    @NotNull
    private Request buildRequest(Object value, HttpUrl httpUrl) {
        return new Request.Builder()
                .url(httpUrl)
                .post(fromEntity(value))
                .build();
    }
}
