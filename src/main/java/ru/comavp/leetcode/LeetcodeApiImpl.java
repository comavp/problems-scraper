package ru.comavp.leetcode;

import ru.comavp.client.RestClient;
import ru.comavp.client.RestClientImp;
import ru.comavp.entity.Solution;
import ru.comavp.parser.Parser;
import ru.comavp.parser.ParserImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.comavp.ApiUtils.GET_LEETCODE_SUBMISSIONS_PATH;

public class LeetcodeApiImpl implements LeetcodeApi {

    private RestClient client;
    private Parser parser;

    private String COOKIES = "";
    private Integer maxOffset = 80; // todo
    private Integer limit = 20;

    public LeetcodeApiImpl() {
        this.client = new RestClientImp();
        this.parser = new ParserImpl();
    }

    @Override
    public List<Solution> getAllSolutionsInfo() throws IOException {
        List<Solution> solutionList = new ArrayList<>();
        for (Integer currOffset = 0; currOffset <= maxOffset; currOffset += limit) {
            String solutions = client.executeGetRequestWithHeaders(
                    GET_LEETCODE_SUBMISSIONS_PATH,
                    Map.of(
                            "offset", currOffset.toString(),
                            "limit", limit.toString(),
                            "lastkey", ""
                    ),
                    Map.of("Cookie", COOKIES)
            );
            solutionList.addAll(parser.parseLeetcodeSolutionsPage(solutions));
        }

        return solutionList;
    }
}
