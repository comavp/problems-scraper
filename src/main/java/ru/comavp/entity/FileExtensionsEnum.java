package ru.comavp.entity;

import ru.comavp.exceptions.UnknownFileExtensionException;

import java.util.Arrays;

public enum FileExtensionsEnum {

    C13("Visual C 2013", ".c"),
    CPP13("Visual C++ 2013", ".cpp"),
    CPP17("Visual C++ 2017", ".cpp"),
    CPP19("Visual C++ 2019", ".cpp"),

    CPP("cpp", ".cpp"),
    C("c", ".c"),
    JAVA("java", ".java"),
    PYTHON("python", ".py"),
    PHP("php", ".php"),

    GNU_CPP_17("GNU C++17", ".cpp"),
    GNU_CPP_14("GNU C++14", ".cpp"),
    GNU_C("GNU C", ".c"),
    MS_CPP("MS C++", ".cpp"),
    JAVA_8("Java 8", ".java");

    private FileExtension fileExtension;

    FileExtensionsEnum(String language, String extension) {
        this.fileExtension = new FileExtension(language, extension);
    }

    public String getLanguage() {
        return this.fileExtension.getLanguage();
    }

    public String getExtension() {
        return this.fileExtension.getExtension();
    }

    public static FileExtensionsEnum getElementByLanguage(String languageName) {
        return Arrays.stream(values())
                .filter(item -> languageName.equals(item.getLanguage()))
                .findFirst()
                .orElseThrow(() -> new UnknownFileExtensionException("Can't find file extension for language: " + languageName));
    }
}
