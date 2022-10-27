package ru.comavp.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Solution {

    String solutionSourceCode;
    String problemUrl;
    String problemName;
    LocalDateTime submitDate;
}
