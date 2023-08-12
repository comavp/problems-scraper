package ru.comavp.scraper;

import ru.comavp.Application;
import ru.comavp.codewars.CodewarsApi;
import ru.comavp.codewars.CodewarsApiImpl;
import ru.comavp.entity.Author;
import ru.comavp.entity.Solution;
import ru.comavp.savers.FileSaver;
import ru.comavp.savers.Saver;
import ru.comavp.timus.TimusApi;
import ru.comavp.timus.TimusApiImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ScraperImpl implements Scraper {

    private Author author;
    private TimusApi timusApi;
    private CodewarsApi codewarsApi;
    private Saver saver;

    private String TIMUS_PATH = "Timus Online Judge//";
    private String CODEWARS_PATH = "Codewars//";

    public static String INVALID_CHARACTERS = "[\\\\/:*?\"<>|]";

    public ScraperImpl() throws IOException {
        this.author = new Author(loadApplicationProperties());
        this.timusApi = new TimusApiImpl(author);
        this.codewarsApi = new CodewarsApiImpl();
        this.saver = new FileSaver();
    }

    @Override
    public void saveTimusSolutionById() throws IOException {
        /*String solutionId = "7122067";
        String solutionSourceCode = timusApi.getSolutionSourceCodeById(solutionId);
        saver.saveSourceCode(TIMUS_PATH + solutionId + C.getExtension(), getComment() + solutionSourceCode);*/
    }

    @Override
    public void saveAllTimusSolutions() throws IOException {
        List<Solution> solutionsList = timusApi.getAllSolutionsInfo();
        changeProblemNamesForDuplicates(solutionsList);
        solutionsList.forEach(item -> {
            String fileName = TIMUS_PATH + item.getFileName();
            String sourceCode = getComment(item);
            try {
                sourceCode += timusApi.getSolutionSourceCodeById(item.getSolutionId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            saver.saveSourceCode(fileName, sourceCode);
        });
    }

    @Override
    public void saveAllCodewarsSolutions() throws IOException {
        List<Solution> solutionList = codewarsApi.getAllSolutionsInfo();
        changeProblemNamesForDuplicates(solutionList);
        solutionList.forEach(solution -> {
            String fileName = CODEWARS_PATH + solution.getFileName().replaceAll(INVALID_CHARACTERS, "");
            String sourceCode = getComment(solution) + solution.getSolutionSourceCode();
            saver.saveSourceCode(fileName, sourceCode);
        });
    }

    private String getComment(Solution solution) {
        return "/*\n" + solution.getProblem().getProblemName() + "\n" + solution.getProblem().getProblemUrl() + "\n*/\n\n";
    }

    private Properties loadApplicationProperties() throws IOException {
        String propertiesPath = Application.class.getClassLoader().getResource("application.yml").getPath();
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesPath));
        return properties;
    }

    private void changeProblemNamesForDuplicates(List<Solution> solutionList) {
        Map<String, Integer> countMap = new HashMap<>();
        solutionList.forEach(solution -> {
            String problemId = solution.getProblem().getProblemId();
            if (!countMap.containsKey(problemId)) {
                countMap.put(problemId, 1);
            } else {
                Integer cnt = countMap.get(problemId);
                cnt++;
                solution.changeProblemId(problemId + "_" + cnt);
                countMap.replace(problemId, cnt);
            }
        });
    }
}
