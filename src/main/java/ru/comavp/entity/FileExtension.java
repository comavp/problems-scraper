package ru.comavp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileExtension {

    private String language;
    private String extension;
}
