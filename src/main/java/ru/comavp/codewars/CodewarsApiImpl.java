package ru.comavp.codewars;

import ru.comavp.client.RestClient;
import ru.comavp.client.RestClientImp;
import ru.comavp.entity.Solution;
import ru.comavp.parser.Parser;
import ru.comavp.parser.ParserImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.comavp.ApiUtils.GET_CODEWARS_COMPLETES_SOLUTIONS_PATH;

public class CodewarsApiImpl implements CodewarsApi {

    private RestClient client;
    private Parser parser;

    private String COOKIES = "";
    private Integer totalPagesNumber = 2; // todo

    public CodewarsApiImpl() {
        this.client = new RestClientImp();
        this.parser = new ParserImpl();
    }

    @Override
    public List<Solution> getAllSolutionsInfo() throws IOException {
        String solutionsPage = client.executeGetRequest(GET_CODEWARS_COMPLETES_SOLUTIONS_PATH, new HashMap<>(),
                new HashMap<>() {{ put("Cookie", COOKIES); }});
        List<Solution> solutionList = new ArrayList<>(parser.parseCodewarsSolutionsPage(solutionsPage));

        for (int i = 1; i <= totalPagesNumber; i++) {
            solutionList.addAll(parser.parseCodewarsSolutionsPage(client.executeGetRequest(
                    GET_CODEWARS_COMPLETES_SOLUTIONS_PATH,
                    Map.of("page", String.valueOf(i)),
                    Map.of(
                            "Cookie", COOKIES,
                            "X-Requested-With", "XMLHttpRequest")
                    )
            ));
        }
        return solutionList;
    }
}
