package ru.comavp;

public class ApiUtils {

    public static String TIMUS_BASE_URL = "https://acm.timus.ru";
    public static String CODEWARS_BASE_URL = "https://www.codewars.com";
    public static String LEETCODE_BASE_URL = "https://www.leetcode.com";

    public static String GET_SUBMIT = "getsubmit.aspx";
    public static String STATUS = "status.aspx";

    public static String GET_SOLUTION_PATH = TIMUS_BASE_URL + "/" + GET_SUBMIT + "/";
    public static String GET_ACCEPTED_SOLUTIONS_PATH = TIMUS_BASE_URL + "/" + STATUS;

    public static String GET_CODEWARS_COMPLETES_SOLUTIONS_PATH = CODEWARS_BASE_URL + "/users/comavp/completed_solutions";

    public static String GET_LEETCODE_SUBMISSIONS_PATH = LEETCODE_BASE_URL + "/api/submissions";
    public static String GET_LEETCODE_PROBLEMS_PATH = LEETCODE_BASE_URL + "/problems";
}
