package ru.comavp.leetcode;

import ru.comavp.entity.Solution;

import java.io.IOException;
import java.util.List;

public interface LeetcodeApi {

    List<Solution>  getAllSolutionsInfo() throws IOException;
}
