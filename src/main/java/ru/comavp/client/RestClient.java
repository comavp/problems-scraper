package ru.comavp.client;

import java.io.IOException;
import java.util.Map;

public interface RestClient {

    String executePostRequest(String url, RequestBodyWrapper requestBodyWrapper) throws IOException;
    String executeGetRequest(String url, Map<String, String> queryParams, Map<String, String> headers) throws IOException;
}
