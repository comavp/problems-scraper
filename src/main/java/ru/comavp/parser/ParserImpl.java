package ru.comavp.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.comavp.entity.Problem;
import ru.comavp.entity.Solution;

import java.util.List;
import java.util.stream.Stream;

import static ru.comavp.ApiUtils.CODEWARS_BASE_URL;
import static ru.comavp.ApiUtils.TIMUS_BASE_URL;
import static ru.comavp.entity.FileExtensionsEnum.getElementByLanguage;

public class ParserImpl implements Parser {

    private Integer codewarsSolutionId = 1;

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
                .map(this::parseCodewarsSolutionInfoRow)
                .toList();
    }

    private Solution parseCodewarsSolutionInfoRow(Element row) {
        Element codeTag = row.getElementsByTag("code").get(0);
        String language = codeTag.attr("data-language");
        Element problemInfoTag = row.getElementsByClass("item-title").get(0);
        return Solution.builder()
                .solutionId((codewarsSolutionId++).toString())
                .problem(parseCodeWarsProblemTag(problemInfoTag))
                .submitDate("")
                .fileExtension(getElementByLanguage(language).getExtension())
                .solutionSourceCode(codeTag.text())
                .build();
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
