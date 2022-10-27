package ru.comavp.savers;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileSaver implements Saver {

    @Override
    public void saveSourceCode(String fileName, String sourceCode) {
        Path path = Paths.get(fileName);
        try {
            BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
            writer.write(sourceCode);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAllSourceCodeFiles(List<String> sourceCodeFiles) {

    }
}
