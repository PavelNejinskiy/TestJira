package MyTestJira;

import TestRailApi.APIClient;
import TestRailApi.APIException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.Duration.ofSeconds;

public class BodyOfTests implements Variables{

    private static WebDriver driver;

    private String newIssuePath;
    private String attachmentLink = "http://jira.hillel.it:8080/secure/thumbnail/13657/_thumb_13657.png";
    @FindBy(css = "a#create_link")
    private WebElement buttonCreateIssue;
    @FindBy(css = "a.issue-created-key")
    private List<WebElement> linkNewIssues;
    @FindBy(css = "input.issue-drop-zone__file")
    private WebElement inputUploadAttachment;
    @FindBy(css = "a.attachment-title")
    private WebElement linkAttachmentName;


    APIClient apiClient;


    public static void setDriver(WebDriver driver) {
        BodyOfTests.driver = driver;
    }

    public void failedLogin()
    {
        login("test", "test");
        Assert.assertEquals(Tools.myElement(By.cssSelector("#usernameerror > p:nth-child(2)")).getText(), "Sorry, your username and password are incorrect - please try again.");
    }

    public void testLogin()
    {
        Assert.assertEquals(driver.findElement(By.cssSelector(".aui-page-header-main > h1:nth-child(1)")).getText(), "System Dashboard");
    }

    public void CreateIssue()
    {
        Tools.myElement(By.cssSelector("#create_link")).click();
        Tools.myElement(By.cssSelector("#summary")).sendKeys("Test Issue");
        Tools.myElement(By.cssSelector("#create-issue-submit")).click();
        Assert.assertEquals(true, Tools.myElement(By.cssSelector(".aui-message")).getText().contains("Test Issue"));
    }

    public void openIssue()
    {
        Tools.myElement(By.cssSelector(".issuekey > a:nth-child(1)")).click();
        Assert.assertEquals(Tools.myElement(By.cssSelector("#summary-val")).getText(), "Test Issue");
    }


    public void createUser() throws InterruptedException {

        login(Variables.Adminlogin, Variables.Adminpassword);

        Tools.myElement(By.cssSelector("#admin_menu > span:nth-child(1)")).click();
        Tools.myElement(By.cssSelector("#admin_users_menu")).click();
        Tools.clearAndFill(By.cssSelector("#login-form-authenticatePassword"), Variables.Adminpassword).submit();
        Tools.myElement(By.cssSelector("#create_user")).click();
        Tools.clearAndFill(By.cssSelector("#user-create-email"), "ForPaveldd@email.com");
        Tools.clearAndFill(By.cssSelector("#user-create-fullname"), "NameForPaveldd");
        Tools.clearAndFill(By.cssSelector("#user-create-username"), "PavelnewNamedd");
        Tools.clearAndFill(By.cssSelector("#password"), "newpassword").submit();

        Assert.assertEquals(Tools.myElement(By.cssSelector(".user-created-flag-single")).getText(), "has been successfully created");
    }


    public void uploadAttachment() {

        openIssue();
        inputUploadAttachment.sendKeys(Variables.attachmentFileLocation + Variables.attachmentFileName);

        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(ofSeconds(2))
                .ignoring(NoSuchElementException.class).until(browser -> linkAttachmentName);

        Assert.assertEquals(Variables.attachmentFileName, linkAttachmentName.getText());

    }

    public void downloadAttachment() throws IOException, NoSuchAlgorithmException {
        openIssue();
        driver.get(attachmentLink);

        URL url = new URL( "http://jira.hillel.it:8080/secure/thumbnail/13657/_thumb_13657.png" );
        BufferedImage image = ImageIO.read( url );
        String typ = "jpg";
        File f1 = new File( "newimage.".concat(typ) );
        ImageIO.write( image, typ, f1 );

        File file1 = new File(Variables.attachmentFileLocation + Variables.attachmentFileName);
       // File file2 = new File(Variables.attachmentFileLocation + Variables.attachmentFileName);

        Assert.assertEquals(Tools.md5Apache(file1), Tools.md5Apache(f1));
    }


    public void sendPost() throws IOException, APIException {
        apiClient = new APIClient("https://hillelmanold.testrail.io");

        apiClient.setUser("rvalek@intersog.com");
        apiClient.setPassword("hillel");

        Map data = new HashMap();
        data.put("status_id", new Integer(1));
        data.put("comment", "This test worked fine!");
        JSONObject r = (JSONObject) apiClient.sendPost("add_result_for_case/1/1", data);
    }


    public static void login(String login, String password) {
        Tools.myElement(By.cssSelector("#login-form-username")).sendKeys(login);
        Tools.myElement(By.cssSelector("#login-form-password")).sendKeys(password);
        Tools.myElement(By.cssSelector("#login-form-submit")).click();

    }
}
