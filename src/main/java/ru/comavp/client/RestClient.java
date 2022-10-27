package ru.comavp.client;

import java.io.IOException;

public interface RestClient {

    String executePostRequest() throws IOException;
    String executeGetRequest();
}
