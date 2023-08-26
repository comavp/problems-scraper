package ru.comavp.codeforces;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CodeforcesSubmissionDto {

    private String source;
    private String contestName;
    private String problemName;
    private String challengeLink;
    private String href;
}
