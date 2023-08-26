package ru.comavp.codeforces;

import ru.comavp.entity.Solution;

import java.io.IOException;
import java.util.List;

public interface CodeforcesApi {

    List<Solution> getAllSolutionsInfo() throws IOException;
    CodeforcesSubmissionDto getSubmissionSourceCode(String submissionId) throws IOException;
}
