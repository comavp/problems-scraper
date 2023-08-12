package ru.comavp.client;

import ru.comavp.entity.Author;

import java.io.IOException;
import java.util.Map;

public interface RestClient {

    String executePostRequest(String url, Author author) throws IOException;
    String executeGetRequest(String url, Map<String, String> queryParams) throws IOException;

    String executeGetRequestWithCookies(String url, Map<String, String> queryParams, String cookies) throws IOException;
}
