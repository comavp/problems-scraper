package ru.comavp.savers;

import java.util.List;

public interface Saver {

    void saveSourceCode(String fileName, String sourceCode);
    void saveAllSourceCodeFiles(List<String> sourceCodeFiles);
}
