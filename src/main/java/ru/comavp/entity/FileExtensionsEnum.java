package ru.comavp.entity;

import java.util.Arrays;

public enum FileExtensionsEnum {

    C("Visual C 2013", ".c"),
    CPP13("Visual C++ 2013", ".cpp"),
    CPP17("Visual C++ 2017", ".cpp"),
    CPP19("Visual C++ 2019", ".cpp"),

    CPP("cpp", ".cpp"),
    JAVA("java", ".java"),
    PYTHON("python", ".py"),
    PHP("php", ".php");

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
        return Arrays.stream(values()).filter(item -> languageName.equals(item.getLanguage())).findFirst().get();
    }
}
