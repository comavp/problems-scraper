package ru.comavp;

import lombok.extern.slf4j.Slf4j;
import ru.comavp.scraper.ScraperImpl;

import java.io.IOException;

@Slf4j
public class Application {

    public static void main(String[] args) throws IOException {
        log.debug("problems-scraper successfully started");
        new ScraperImpl().saveAllTimusSolutions();
    }
}