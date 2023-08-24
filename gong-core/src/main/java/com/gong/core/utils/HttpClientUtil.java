package com.gong.core.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class HttpClientUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final static int connectTimeout = 15;
    private final static int socketTimeout = 30;

    private static final OkHttpClient client =
        new OkHttpClient().newBuilder().connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(socketTimeout, TimeUnit.SECONDS).build();

    /**
     * get url
     * 
     * @param url
     * @return
     */
    public Optional<String> get(String url) {
        return get(url, null);
    }

    /**
     * get url
     * 
     * @param url
     * @param params
     * @return
     */
    public Optional<String> get(String url, Map<String, String> params) {
        try {
            HttpUrl.Builder httpBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    httpBuilder.addQueryParameter(param.getKey(), param.getValue());
                }
            }

            Request request = new Request.Builder().url(httpBuilder.build()).get().build();
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                assert response.body() != null;
                return Optional.of(response.body().string());
            } else {
                log.error("http get error: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("http get error: " + e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * post json
     *
     * @param url
     * @param body
     * @return
     */
    public Optional<String> postJson(String url, String body) {
        return post(url, RequestBody.create(JSON, body));
    }

    /**
     * @param url
     * @param body
     * @return
     */
    public Optional<String> post(String url, RequestBody body) {
        try {
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                assert response.body() != null;
                return Optional.of(response.body().string());
            } else {
                log.error("http get error: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("http get error: " + e.getMessage());
        }
        return Optional.empty();
    }

}
