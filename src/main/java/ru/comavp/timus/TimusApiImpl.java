package ru.comavp.timus;

import ru.comavp.client.RestClient;
import ru.comavp.client.RestClientImp;
import ru.comavp.entity.Author;

import java.io.IOException;
import java.util.List;

import static ru.comavp.ApiUtils.BASE_URL;
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
        String url = BASE_URL + solutionId + C.getExtension();
        return client.executePostRequest(url, author);
    }

    @Override
    public List<String> getAllSolutions() {
        return null;
    }
}
