package ru.comavp.scraper;

import java.io.IOException;

public interface Scraper {

    void saveTimusSolutionById() throws IOException;
    void saveAllTimusSolutions() throws IOException;

    void saveAllCodewarsSolutions() throws IOException;
    void saveAllLeetcodeSolutions() throws IOException;
    void saveAllCodeforcesSolutions() throws IOException;
}
