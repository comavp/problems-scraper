package ru.comavp.client;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class RestClientImp implements RestClient {

    public String executePostRequest(String url, RequestBodyWrapper requestBodyWrapper) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse(requestBodyWrapper.getMediaType()), requestBodyWrapper.getBody());

        return executeRequest(new Request.Builder()
                .url(url)
                .post(body));
    }

    public String executePostRequest(String url, RequestBodyWrapper requestBodyWrapper, Map<String, String> headers) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse(requestBodyWrapper.getMediaType()), requestBodyWrapper.getBody());

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(body);
        headers.forEach(requestBuilder::addHeader);

        return executeRequest(requestBuilder);
    }

    public String executeGetRequest(String url) throws IOException {
        return executeRequest(new Request.Builder()
                .url(url)
                .get());
    }

    public String executeGetRequest(String url, Map<String, String> queryParams) throws IOException {
        HttpUrl.Builder urlBuilder = getUrlBuilderWithQueryParams(url, queryParams);
        return executeRequest(new Request.Builder()
                .url(urlBuilder.build().toString())
                .get());
    }

    public String executeGetRequest(String url, Map<String, String> queryParams, Map<String, String> headers) throws IOException {
        HttpUrl.Builder urlBuilder = getUrlBuilderWithQueryParams(url, queryParams);

        Request.Builder requestBuilder = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get();
        headers.forEach(requestBuilder::addHeader);

        return executeRequest(requestBuilder);
    }

    private HttpUrl.Builder getUrlBuilderWithQueryParams(String url, Map<String, String> queryParams) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        queryParams.forEach(urlBuilder::addQueryParameter);
        return urlBuilder;
    }

    private String executeRequest(Request.Builder requestBuilder) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = requestBuilder.build();

        Response response = client.newCall(request).execute();
        log.info("Request " + request.method() + " " + request.url()  + " was sent");
        log.debug("Response code: " + response.code());

        return response.body().string();
    }
}
