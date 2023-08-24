package ru.comavp.client;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class RestClientImp implements RestClient {

    public String executePostRequest(String url, RequestBodyWrapper requestBodyWrapper) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        RequestBody body = RequestBody.create(MediaType.parse(requestBodyWrapper.getMediaType()), requestBodyWrapper.getBody());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        log.info("Request with url: " + url  + " was sent");
        log.debug("Response code: " + response.code());

        return response.body().string();
    }

    public String executeGetRequest(String url, Map<String, String> queryParams, Map<String, String> headers) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        queryParams.forEach(urlBuilder::addQueryParameter);

        Request.Builder requestBuilder = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get();
        headers.forEach(requestBuilder::addHeader);
        Request request = requestBuilder.build();

        Response response = client.newCall(request).execute();
        log.info("Request with url: " + request.url()  + " was sent");
        log.debug("Response code: " + response.code());

        return response.body().string();
    }
}
