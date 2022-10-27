package ru.comavp.savers;

import org.junit.Test;

public class FileSaverTest {

    String testSourceCode = "int main()\n" +
            "{\n" +
            "\treturn 0;\n" +
            "}";
    String testComment = "// www.google.ru\n\n";
    String testProblemName = "1. Test problem";
    String testExtension = ".c";

    @Test
    public void saveSourceCodeTest() {
        Saver saver = new FileSaver();
        saver.saveSourceCode(testProblemName + testExtension, testComment + testSourceCode);
    }
}
