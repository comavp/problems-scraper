package ru.comavp.codewars;

import ru.comavp.entity.Solution;

import java.io.IOException;
import java.util.List;

public interface CodewarsApi {

    List<Solution> getAllSolutionsInfo() throws IOException;
}
