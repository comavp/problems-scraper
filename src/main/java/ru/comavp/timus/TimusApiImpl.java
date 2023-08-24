package ru.comavp.timus;

import ru.comavp.client.RequestBodyWrapper;
import ru.comavp.client.RestClient;
import ru.comavp.client.RestClientImp;
import ru.comavp.entity.Author;
import ru.comavp.entity.Solution;
import ru.comavp.parser.Parser;
import ru.comavp.parser.ParserImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ru.comavp.ApiUtils.GET_ACCEPTED_SOLUTIONS_PATH;
import static ru.comavp.ApiUtils.GET_SOLUTION_PATH;
import static ru.comavp.entity.FileExtensionsEnum.C;

public class TimusApiImpl implements TimusApi {

    private RestClient client;
    private Author author;
    private Parser parser;

    public TimusApiImpl(Author author) {
        this.author = author;
        this.client = new RestClientImp();
        this.parser = new ParserImpl();
    }

    @Override
    public String getSolutionSourceCodeById(String solutionId) throws IOException {
        String url = GET_SOLUTION_PATH + solutionId + C.getExtension();
        String payload = "Action=getsubmit&JudgeID=" + author.getJudgeId() + "&Password=" + author.getPassword();
        return client.executePostRequest(url, new RequestBodyWrapper(payload, "application/x-www-form-urlencoded"));
    }

    @Override
    public List<Solution> getAllSolutionsInfo() throws IOException {
        String htmlResponse = client.executeGetRequest(GET_ACCEPTED_SOLUTIONS_PATH,
                Map.of(
                        "author", author.getAuthorId(),
                        "status", "accepted",
                        "count", "1000"
                ),
                Map.of(
                        "Accept-Language", "ru-RU"
                ));
        return parser.parseTimusSolutionsPage(htmlResponse);
    }
}
