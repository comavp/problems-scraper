package ru.comavp.timus;

import ru.comavp.entity.Solution;

import java.io.IOException;
import java.util.List;

public interface TimusApi {

    String getSolutionSourceCodeById(String solutionId) throws IOException;
    List<Solution> getAllSolutionsInfo() throws IOException;
}
