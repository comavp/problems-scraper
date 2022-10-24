package ru.comavp;

import ru.comavp.client.RestClient;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        String response = new RestClient().executePostRequest();
        System.out.println(response);
    }
}