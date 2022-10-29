package ru.comavp.timus;

import ru.comavp.client.RestClient;
import ru.comavp.client.RestClientImp;
import ru.comavp.entity.Author;
import ru.comavp.entity.Solution;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.comavp.ApiUtils.GET_ACCEPTED_SOLUTIONS_PATH;
import static ru.comavp.ApiUtils.GET_SOLUTION_PATH;
import static ru.comavp.entity.FileExtensionsEnum.C;

public class TimusApiImpl implements TimusApi {

    private RestClient client;
    private Author author;

    public TimusApiImpl(Author author) {
        this.author = author;
        this.client = new RestClientImp();
    }

    @Override
    public String getSolutionSourceCodeById(String solutionId) throws IOException {
        String url = GET_SOLUTION_PATH + solutionId + C.getExtension();
        return client.executePostRequest(url, author);
    }

    @Override
    public List<Solution> getAllSolutionsInfo() throws IOException {
        Map<String, String> queryParams = new HashMap<>() {{
            put("author", author.getAuthorId());
            put("status", "accepted");
            put("count", "1000");
        }};
        String htmlResponse = client.executeGetRequest(GET_ACCEPTED_SOLUTIONS_PATH, queryParams);
        System.out.println(htmlResponse);
        return null;
    }
}
