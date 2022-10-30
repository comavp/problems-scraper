package ru.comavp.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Solution {

    String solutionId;
    Problem problem;
    String submitDate;
    String fileExtension;
    String solutionSourceCode;
}
