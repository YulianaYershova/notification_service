package com.fitgoal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
        return RequestBody.create(writeValueAsString(entity), JSON_MEDIA_TYPE);
    }

    static ObjectMapper buildDefaultMapper() {
        return Jackson.newObjectMapper();
    }

    private String writeValueAsString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    void executeRequest(Request request) {
        try {
            okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        /*try (Response response = okHttpClient.newCall(request).execute()) {
            return response.code();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }*/
    }
}
