package ru.comavp;

import ru.comavp.entity.Author;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Application {

    public static void main(String[] args) throws IOException {
        Author author = new Author(loadApplicationProperties());
        //String response = new RestClientImp().executePostRequest();
        //System.out.println(response);
    }

    static Properties loadApplicationProperties() throws IOException {
        String propertiesPath = Application.class.getClassLoader().getResource("application.yml").getPath();
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesPath));
        return properties;
    }
}