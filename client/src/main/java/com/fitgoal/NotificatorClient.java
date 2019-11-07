package com.fitgoal;

import com.fitgoal.api.NotificationService;
import com.fitgoal.api.domain.Recipient;
import com.fitgoal.api.domain.UserVerification;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NotificatorClient extends BaseClient implements NotificationService {

    private static final String BASE_URI = "http://localhost:9090/notifications";

    public NotificatorClient(OkHttpClient okHttpClient) {
        super(buildDefaultMapper(), okHttpClient);
    }

    @Override
    public void register(UserVerification userVerification) {
        Request request = new Request.Builder()
                .url(BASE_URI + "/register")
                .post(fromEntity(userVerification))
                .build();
        executeRequest(request);
    }

    @Override
    public void registerSuccess(Recipient recipient) {
        Request request = new Request.Builder()
                .url(BASE_URI + "/register/success")
                .post(fromEntity(recipient))
                .build();
        executeRequest(request);
    }

    @Override
    public void resetPassword(UserVerification userVerification) {
        Request request = new Request.Builder()
                .url(BASE_URI + "/resetPassword")
                .post(fromEntity(userVerification))
                .build();
        executeRequest(request);
    }

    @Override
    public void resetPasswordSuccess(Recipient recipient) {
        Request request = new Request.Builder()
                .url(BASE_URI + "/resetPassword/success")
                .post(fromEntity(recipient))
                .build();
        executeRequest(request);
    }
}
