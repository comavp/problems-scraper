package ru.comavp.client;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

@Slf4j
public class RestClientImp implements RestClient {

    private String BASE_URL = "https://acm.timus.ru/getsubmit.aspx/";
    private String SOLUTION_ID = "7252133";
    private String FILE_EXTENSION = ".c";
    private String judgeId = "";
    private String password = "";

    public String executePostRequest() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        String payload = "Action=getsubmit&JudgeID=" + judgeId + "&Password=" + password;
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), payload);

        String url = BASE_URL + SOLUTION_ID + FILE_EXTENSION;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        log.info("Request with url: " + url  + " was sent");
        log.debug("Response code: " + response.code());

        return response.body().string();
    }

    public String executeGetRequest() {
        return "";
    }
}
