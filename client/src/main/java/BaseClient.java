import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import okhttp3.*;

import javax.inject.Inject;
import java.io.IOException;


abstract class BaseClient {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private final ObjectMapper mapper;
    private final OkHttpClient okHttpClient;

    @Inject
    BaseClient(ObjectMapper mapper, OkHttpClient okHttpClient) {
        this.mapper = mapper;
        this.okHttpClient = okHttpClient;
    }

    RequestBody fromEntity(Object entity) {
        return RequestBody.create(writeValueAsString(entity),JSON_MEDIA_TYPE);
    }

    static ObjectMapper buildDefaultMapper() {
        return Jackson.newObjectMapper();
    }

    private String writeValueAsString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    int executeRequest(Request request) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            String message = responseBody != null ? responseBody.string() : null;
            return response.code();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
