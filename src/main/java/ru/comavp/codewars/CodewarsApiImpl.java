package ru.comavp.codewars;

import ru.comavp.client.RestClient;
import ru.comavp.client.RestClientImp;
import ru.comavp.entity.Author;
import ru.comavp.entity.Solution;
import ru.comavp.parser.Parser;
import ru.comavp.parser.ParserImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ru.comavp.ApiUtils.GET_CODEWARS_COMPLETES_SOLUTIONS_PATH;

public class CodewarsApiImpl implements CodewarsApi {

    private RestClient client;
    private Parser parser;

    private String COOKIES = "";
    private Integer totalPagesNumber = 2;

    public CodewarsApiImpl() {
        this.client = new RestClientImp();
        this.parser = new ParserImpl();
    }

    @Override
    public List<Solution> getAllSolutionsInfo() throws IOException {
        String solutionsPage = client.executeGetRequestWithCookies(GET_CODEWARS_COMPLETES_SOLUTIONS_PATH, new HashMap<>(), COOKIES);
        List<Solution> solutionList = parser.parseCodewarsSolutionsPage(solutionsPage);
//        List<Solution> solutionList2 = parser.parseCodewarsSolutionsPage(client.executeGetRequestWithCookies(
//                GET_CODEWARS_COMPLETES_SOLUTIONS_PATH,
//                new HashMap<>() {{ put("page", "1"); }},
//                COOKIES
//        ));
        return solutionList;
    }
}
