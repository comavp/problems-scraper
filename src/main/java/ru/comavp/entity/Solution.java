package ru.comavp.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Solution {

    String solutionId;
    Problem problem;
    String submitDate;
    String fileExtension;
    String solutionSourceCode;

    public void changeProblemId(String newProblemId) {
        problem.setProblemId(newProblemId);
    }

    public String getFileName() {
        return problem.getProblemId() + fileExtension;
    }
}
