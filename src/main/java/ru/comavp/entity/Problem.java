package ru.comavp.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Problem {

    String problemId;
    String problemUrl;
    String problemName;
}
