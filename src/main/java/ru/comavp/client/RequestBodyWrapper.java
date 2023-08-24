package ru.comavp.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestBodyWrapper {

    private String body;
    private String mediaType;
}
