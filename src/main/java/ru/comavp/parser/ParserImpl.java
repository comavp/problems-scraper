package ru.comavp.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.comavp.entity.Problem;
import ru.comavp.entity.Solution;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.comavp.ApiUtils.*;
import static ru.comavp.entity.FileExtensionsEnum.getElementByLanguage;

public class ParserImpl implements Parser {

    private int codewarsSolutionId = 1;

    @Override
    public List<Solution> parseTimusSolutionsPage(String htmlPage) {
        Document doc = Jsoup.parse(htmlPage);
        Elements evenRows = doc.getElementsByClass("even");
        Elements oddRows = doc.getElementsByClass("odd");
        return Stream.concat(evenRows.stream(), oddRows.stream())
                .map(this::parseTimusSolutionInfoRow)
                .toList();
    }

    @Override
    public List<Solution> parseCodewarsSolutionsPage(String htmlPage) {
        Document doc = Jsoup.parse(htmlPage);
        Elements rows = doc.getElementsByClass("list-item-solutions");
        return rows.stream()
                .flatMap(row -> parseCodewarsSolutionInfoRow(row).stream())
                .toList();
    }

    @Override
    public List<Solution> parseLeetcodeSolutionsPage(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return toSolutionList(mapper.readValue(json, LeetcodeSolutionDump.class));
    }

    public List<Solution> toSolutionList(LeetcodeSolutionDump dump) {
        return dump.getSubmissionsDump().stream()
                .filter(item -> "Accepted".equals(item.getStatusDisplay()))
                .map(this::parseSolutionFromLeetcodeSolutionDto)
                .collect(Collectors.toList());
    }

    private Solution parseSolutionFromLeetcodeSolutionDto(LeetcodeSolutionDump.LeetcodeSolutionDto solutionDto) {
        return Solution.builder()
                .solutionId(String.valueOf(solutionDto.getId()))
                .problem(getProblemFromLeetcodeSolutionDto(solutionDto))
                .submitDate(String.valueOf(solutionDto.getTimestamp()))
                .fileExtension(getElementByLanguage(solutionDto.getLang()).getExtension())
                .solutionSourceCode(solutionDto.getCode())
                .build();
    }

    private Problem getProblemFromLeetcodeSolutionDto(LeetcodeSolutionDump.LeetcodeSolutionDto solutionDto) {
        return Problem.builder()
                .problemId(solutionDto.getTitle())
                .problemUrl(GET_LEETCODE_PROBLEMS_PATH + "/" + solutionDto.getTitleSlug())
                .problemName(solutionDto.getTitle())
                .build();
    }

    private List<Solution> parseCodewarsSolutionInfoRow(Element row) {
        Element problemInfoTag = row.getElementsByClass("item-title").get(0);
        return row.getElementsByTag("code").stream()
                .map(codeTag -> {
                    String language = codeTag.attr("data-language");
                    return Solution.builder()
                            .solutionId(String.valueOf(codewarsSolutionId++))
                            .problem(parseCodeWarsProblemTag(problemInfoTag))
                            .submitDate("")
                            .fileExtension(getElementByLanguage(language).getExtension())
                            .solutionSourceCode(codeTag.text())
                            .build();
                })
                .collect(Collectors.toList());
    }

    private Solution parseTimusSolutionInfoRow(Element row) {
        String solutionId = row.getElementsByClass("id").text();
        Element problem = row.getElementsByClass("problem").first();
        String submitDate = row.getElementsByClass("date").text();
        String fileExtension = getElementByLanguage(row.getElementsByClass("language").text()).getExtension();
        return Solution.builder()
                .solutionId(solutionId)
                .problem(parseTimusProblemTag(problem))
                .submitDate(submitDate)
                .fileExtension(fileExtension)
                .build();
    }

    private Problem parseCodeWarsProblemTag(Element problemTag) {
        String problemUrl = problemTag.getElementsByTag("a").attr("href");
        String problemName = problemTag.getElementsByTag("a").text();
        return Problem.builder()
                .problemId(problemName)
                .problemUrl(CODEWARS_BASE_URL + problemUrl)
                .problemName(problemName)
                .build();
    }

    private Problem parseTimusProblemTag(Element problemTag) {
        String problemUrl = problemTag.getElementsByTag("a").attr("href");
        String[] problemStr = problemTag.getElementsByTag("a").text().split("\\. ");
        String problemId = problemStr[0];
        String problemName = problemStr[1];
        return Problem.builder()
                .problemId(problemId)
                .problemName(problemName)
                .problemUrl(TIMUS_BASE_URL + "/" + problemUrl)
                .build();
    }
}
