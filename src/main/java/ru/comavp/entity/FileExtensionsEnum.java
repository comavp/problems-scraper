package ru.comavp.entity;

public enum FileExtensionsEnum {

    C("Visual C 2013", ".c"),
    CPP13("Visual C++ 2013", ".cpp"),
    CPP17("Visual C++ 2017", ".cpp"),
    CPP19("Visual C++ 2019", ".cpp");

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
}
