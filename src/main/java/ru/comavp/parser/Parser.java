package ru.comavp.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.comavp.entity.Solution;

import java.util.List;

public interface Parser {

    List<Solution> parseTimusSolutionsPage(String htmlPage);
    List<Solution> parseCodewarsSolutionsPage(String htmlPage);

    List<Solution> parseLeetcodeSolutionsPage(String json) throws JsonProcessingException;
}
