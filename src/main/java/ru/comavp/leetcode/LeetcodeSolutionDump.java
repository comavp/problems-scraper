package ru.comavp.leetcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LeetcodeSolutionDump {

    @JsonProperty("submissions_dump")
    private List<LeetcodeSolutionDto> submissionsDump;
    @JsonProperty("has_next")
    private boolean hasNext;

    @Data
    @NoArgsConstructor
    public static class LeetcodeSolutionDto {
        private long id;
        private String lang;
        private long timestamp;
        @JsonProperty("status_display")
        private String statusDisplay;
        private String title;
        private String code;
        @JsonProperty("title_slug")
        private String titleSlug;
    }
}
