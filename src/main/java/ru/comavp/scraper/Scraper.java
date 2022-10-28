package ru.comavp.scraper;

import java.io.IOException;

public interface Scraper {

    void saveTimusSolutionById() throws IOException;
    void saveAllTimusSolutions();
}
