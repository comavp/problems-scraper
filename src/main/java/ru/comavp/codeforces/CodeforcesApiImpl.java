package ru.comavp.codeforces;

import ru.comavp.client.RequestBodyWrapper;
import ru.comavp.client.RestClient;
import ru.comavp.client.RestClientImp;
import ru.comavp.entity.Solution;
import ru.comavp.parser.Parser;
import ru.comavp.parser.ParserImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.comavp.ApiUtils.GET_CODEFORCES_SUBMISSIONS_PATH;
import static ru.comavp.ApiUtils.GET_CODEFORCES_SUBMISSION_SOURCE_CODE_PATH;

public class CodeforcesApiImpl implements CodeforcesApi {

    private RestClient client;
    private Parser parser;

    private String USER_NAME = "comavp";
    private Integer numberOfPages = 9;
    private String COOKIE = "";
    private String csrfToken = "";

    public CodeforcesApiImpl() {
        this.client = new RestClientImp();
        this.parser = new ParserImpl();
    }

    @Override
    public List<Solution> getAllSolutionsInfo() throws IOException {
        List<Solution> result = new ArrayList<>();
        for (int i = 1; i <= 1; i++) {
            String htmlPage = client.executeGetRequest(GET_CODEFORCES_SUBMISSIONS_PATH + "/" + USER_NAME + "/page/" + i);
            result.addAll(parser.parseCodeforcesSubmissionsPage(htmlPage));
        }
        return result;
    }

    @Override
    public CodeforcesSubmissionDto getSubmissionSourceCode(String submissionId) throws IOException {
        String payload = "submissionId=" + submissionId + "&csrf_token=" + csrfToken;
        String mediaType = "application/x-www-form-urlencoded";
        String jsonBody = client.executePostRequest(GET_CODEFORCES_SUBMISSION_SOURCE_CODE_PATH, new RequestBodyWrapper(payload, mediaType));
        return parser.parseCodeforcesSubmissionDto(jsonBody);
    }
}
