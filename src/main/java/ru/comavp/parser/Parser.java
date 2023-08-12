package ru.comavp.parser;

import ru.comavp.entity.Solution;

import java.util.List;

public interface Parser {

    List<Solution> parseTimusSolutionsPage(String htmlPage);
    List<Solution> parseCodewarsSolutionsPage(String htmlPage);
}
