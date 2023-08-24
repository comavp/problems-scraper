package ru.comavp.client;

import java.io.IOException;
import java.util.Map;

public interface RestClient {

    String executeGetRequest(String url) throws IOException;
    String executeGetRequest(String url, Map<String, String> queryParams) throws IOException;
    String executeGetRequest(String url, Map<String, String> queryParams, Map<String, String> headers) throws IOException;
    String executePostRequest(String url, RequestBodyWrapper requestBodyWrapper) throws IOException;
    String executePostRequest(String url, RequestBodyWrapper requestBodyWrapper, Map<String, String> headers) throws IOException;
}
