import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NotificatorClient extends BaseClient {
    private static final String BASE_URI = "http://localhost:9090/notifications";

    NotificatorClient(OkHttpClient okHttpClient) {
        super(buildDefaultMapper(), okHttpClient);
    }

    int register(Object object) {
        Request request = new Request.Builder().url(BASE_URI + "/register").post(fromEntity(object)).build();
        return executeRequest(request);
    }


    int successRegister(Object object) {
        Request request = new Request.Builder().url(BASE_URI+"/register/success").post(fromEntity(object)).build();
        return executeRequest(request);
    }

    int resetPassword(Object object) {
        Request request = new Request.Builder().url(BASE_URI+"/resetPassword").post(fromEntity(object)).build();
        return executeRequest(request);
    }

    int successResetPassword(Object object) {
        Request request = new Request.Builder().url(BASE_URI+"/resetPassword/success").post(fromEntity(object)).build();
        return executeRequest(request);
    }
}
