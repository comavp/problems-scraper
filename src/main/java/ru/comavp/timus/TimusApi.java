package ru.comavp.timus;

import java.io.IOException;
import java.util.List;

public interface TimusApi {

    String getSolutionSourceCodeById(String solutionId) throws IOException;
    List<String> getAllSolutions();
}
