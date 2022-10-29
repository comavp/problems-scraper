package ru.comavp.client;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import ru.comavp.entity.Author;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class RestClientImp implements RestClient {

    public String executePostRequest(String url, Author author) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        String payload = "Action=getsubmit&JudgeID=" + author.getJudgeId() + "&Password=" + author.getPassword();
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        log.info("Request with url: " + url  + " was sent");
        log.debug("Response code: " + response.code());

        return response.body().string();
    }

    public String executeGetRequest(String url, Map<String, String> queryParams) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        queryParams.forEach(urlBuilder::addQueryParameter);

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .build();

        Response response = client.newCall(request).execute();
        log.info("Request with url: " + url  + " was sent");
        log.debug("Response code: " + response.code());

        return response.body().string();
    }
}
