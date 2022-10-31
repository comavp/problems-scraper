package ru.comavp.entity;

import lombok.Data;

import java.util.Properties;

@Data
public class Author {

    String authorId;
    String authorName;
    String judgeId;
    String password;

    public Author(Properties properties) {
        this.authorId = properties.getProperty("authorId");
        this.authorName = properties.getProperty("authorName");
        this.judgeId = properties.getProperty("judgeId");
        this.password = properties.getProperty("password");
    }
}
