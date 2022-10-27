package ru.comavp.timusapi;

import java.util.List;

public interface TimusApi {

    String getSolutionSourceCodeById(String solutionId);
    List<String> getAllSolutions();
}
