package com.fitgoal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;

import static java.net.HttpURLConnection.*;

abstract class BaseClient {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final int UNPROCESSABLE_ENTITY_STATUS_CODE = 422;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper mapper;
    private final HttpUrl resourceUrl;

    @Inject
    BaseClient(OkHttpClient okHttpClient, ObjectMapper mapper, HttpUrl resourceUrl) {
        this.okHttpClient = okHttpClient;
        this.mapper = mapper;
        this.resourceUrl = resourceUrl;
    }

    static ObjectMapper buildDefaultMapper() {
        return Jackson.newObjectMapper();
    }

    /**
     * Build {@link RequestBody} converting object to JSON
     */
    RequestBody fromEntity(Object entity) {
        return RequestBody.create(writeValueAsString(entity), JSON_MEDIA_TYPE);
    }

    private String writeValueAsString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    void executeRequest(Request request) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw convertException(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private RuntimeException convertException(Response unsuccessfulResponse) throws IOException {
        try (Response response = unsuccessfulResponse) {
            ResponseBody responseBody = response.body();
            String message = responseBody != null ? responseBody.string() : null;
            switch (response.code()) {
                case HTTP_BAD_REQUEST:
                case UNPROCESSABLE_ENTITY_STATUS_CODE:
                    return new IllegalArgumentException(message);
                default:
                    return new RuntimeException(message);
            }
        }
    }

    HttpUrl buildSubresourceHttpUrl(String... subresourcePaths) {
        HttpUrl.Builder builder = resourceUrl.newBuilder();
        Arrays.stream(subresourcePaths).forEach(builder::addPathSegment);
        return builder.build();
    }
}
