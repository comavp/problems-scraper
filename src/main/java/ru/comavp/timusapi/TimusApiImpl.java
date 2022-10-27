package ru.comavp.timusapi;

import ru.comavp.client.RestClient;
import ru.comavp.client.RestClientImp;

import java.util.List;

public class TimusApiImpl implements TimusApi {

    RestClient client = new RestClientImp();

    @Override
    public String getSolutionSourceCodeById(String solutionId) {
        return null;
    }

    @Override
    public List<String> getAllSolutions() {
        return null;
    }
}
