package ru.comavp.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.comavp.entity.Problem;
import ru.comavp.entity.Solution;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import static ru.comavp.ApiUtils.BASE_URL;
import static ru.comavp.entity.FileExtensionsEnum.getElementByLanguage;

public class ParserImpl implements Parser {

    @Override
    public List<Solution> parseSolutionsInfoPage(String htmlPage) {
        Document doc = Jsoup.parse(htmlPage);
        Elements evenRows = doc.getElementsByClass("even");
        Elements oddRows = doc.getElementsByClass("odd");
        List<Solution> result = Stream.concat(evenRows.stream(), oddRows.stream())
                .map(this::parseSolutionInfoRow)
                .toList();
        return result;
    }

    private Solution parseSolutionInfoRow(Element row) {
        String solutionId = row.getElementsByClass("id").text();
        Element problem = row.getElementsByClass("problem").first();
        String submitDate = row.getElementsByClass("date").text();
        String fileExtension = getElementByLanguage(row.getElementsByClass("language").text()).getExtension();
        return Solution.builder()
                .solutionId(solutionId)
                .problem(parseProblemTag(problem))
                .submitDate(submitDate)
                .fileExtension(fileExtension)
                .build();
    }

    private Problem parseProblemTag(Element problemTag) {
        String problemUrl = problemTag.getElementsByTag("a").attr("href");
        String[] problemStr = problemTag.getElementsByTag("a").text().split("\\. ");
        String problemId = problemStr[0];
        String problemName = problemStr[1];
        return Problem.builder()
                .problemId(problemId)
                .problemName(problemName)
                .problemUrl(BASE_URL + "/" + problemUrl)
                .build();
    }
}
