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

    public void changeProblemName(String newProblemName) {
        problem.setProblemName(newProblemName);
    }
}
