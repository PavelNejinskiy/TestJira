package hillelauto.jira;

import hillelauto.Tools;

public interface JiraVars {
    public static final String baseURL = "http://jira.hillel.it:8080/";
    public static final String username = "Pavel";
    public static final String password = "droplles";

    public static final String newIssueSummary = "Pavel AutoTest " + Tools.timestamp();
    public static final String attachmentFileLocation = "G:\\темп";
    public static final String attachmentFileName = "images1.jpg";
}