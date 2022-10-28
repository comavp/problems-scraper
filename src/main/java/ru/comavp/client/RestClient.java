package ru.comavp.client;

import ru.comavp.entity.Author;

import java.io.IOException;

public interface RestClient {

    String executePostRequest(String url, Author author) throws IOException;
    String executeGetRequest();
}
