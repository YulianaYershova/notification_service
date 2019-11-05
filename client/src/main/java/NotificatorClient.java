import com.fit_goal.api.Notificator;
import com.fit_goal.domain.UserVerification;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class NotificatorClient extends BaseClient implements Notificator {
    private static final String BASE_URI = "http://localhost:9090/notifications";

    NotificatorClient(OkHttpClient okHttpClient) {
        super(buildDefaultMapper(), okHttpClient);
    }

    @Override
    public void register(UserVerification userVerification) {
        Request request = new Request.Builder().url(BASE_URI + "/register").post(fromEntity(userVerification)).build();
        // return executeRequest(request);
    }

    @Override
    public void registerSuccess(String email) {
        Request request = new Request.Builder().url(BASE_URI + "/register/success").post(RequestBody.create(email.getBytes())).build();
        // return executeRequest(request);
    }

    @Override
    public void resetPassword(UserVerification userVerification) {
        Request request = new Request.Builder().url(BASE_URI + "/resetPassword").post(fromEntity(userVerification)).build();
        //return executeRequest(request);
    }

    @Override
    public void resetPasswordSuccess(String email) {
        Request request = new Request.Builder().url(BASE_URI + "/resetPassword/success").post(fromEntity(email)).build();
       // return executeRequest(request);
    }
}
