package hillelauto.jira;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import hillelauto.Tools;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;


public class IssuePage {
    private final By inputProject = By.cssSelector("input#project-field");
    private final By inputSummary = By.cssSelector("input#summary");
    private final WebDriver browser;
    private String newIssuePath;
    private String attachmentLink;
    @FindBy(css = "a#create_link")
    private WebElement buttonCreateIssue;
    @FindBy(css = "a.issue-created-key")
    private List<WebElement> linkNewIssues;
    @FindBy(css = "input.issue-drop-zone__file")
    private WebElement inputUploadAttachment;
    @FindBy(css = "a.attachment-title")
    private WebElement linkAttachmentName;

    public IssuePage(WebDriver browser) {
        this.browser = browser;
    }

    public void createIssue() {
        buttonCreateIssue.click();

        Tools.clearAndFill(inputProject, "General QA Robert (GQR)\n");
        Tools.clearAndFill(inputSummary, JiraVars.newIssueSummary);

//        new FluentWait<>(browser).withTimeout(Duration.ofSeconds(5)).pollingEvery(ofMillis(500))
//                .ignoring(InvalidElementStateException.class)
//                .until(browser -> Tools.clearAndFill(inputSummary, JiraVars.newIssueSummary)).submit();

        new FluentWait<> (new WebDriverWait(browser, 5).ignoring(InvalidElementStateException.class));


       // ((JavascriptExecutor) browser).executeScript("window.scrollBy(0,250)");

        Assert.assertTrue(linkNewIssues.size() != 0);

        newIssuePath = linkNewIssues.get(0).getAttribute("href");
    }

    public void openIssue() {
        browser.get(newIssuePath);
        Assert.assertTrue(browser.getTitle().contains(JiraVars.newIssueSummary));
    }

    public void uploadAttachment() {
        inputUploadAttachment.sendKeys(JiraVars.attachmentFileLocation + JiraVars.attachmentFileName);

//        new FluentWait<>(browser).withTimeout(Duration.ofSeconds(10)).pollingEvery(ofSeconds(2))
//                .ignoring(NoSuchElementException.class).until(browser -> linkAttachmentName);

        new FluentWait<> (new WebDriverWait(browser, 10).ignoring(InvalidElementStateException.class));

        Assert.assertEquals(JiraVars.attachmentFileName, linkAttachmentName.getText());

        attachmentLink = linkAttachmentName.getText();

        // return JiraVars.attachmentFileName.equals(linkAttachment.getText());
    }

    public void downloadAttachment() throws FileNotFoundException, NoSuchAlgorithmException {
        browser.get(attachmentLink);
        File file1 = new File(JiraVars.attachmentFileLocation + JiraVars.attachmentFileName);
       File file2 = new File("http://jira.hillel.it:8080/secure/thumbnail/13657/_thumb_13657.png");

      Assert.assertEquals(Tools.md5Apache(file1), Tools.md5Apache(file2));

        // https://stackoverflow.com/questions/304268/getting-a-files-md5-checksum-in-java
    }
}