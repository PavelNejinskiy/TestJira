package MyTestJira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestsJira extends WebDriverBase implements Variables{

  static  BodyOfTests bot = new BodyOfTests();

    private static WebDriver driver;

    @BeforeGroups (groups = "positive")
    public static void openPage() {
        bot.login(login, password);
    }

    @Test (priority = -1)
    public static void testFailedLogin() throws InterruptedException {
        bot.failedLogin();
    }

    @Test (priority = 1, groups = "positive")
    public static void testLogin() throws InterruptedException {
        bot.testLogin();
    }

    @Test (priority = 2, groups = "positive")
    public static void testCreateIssue() throws InterruptedException {
        bot.CreateIssue();
    }

    @Test (priority = 3, groups = "positive")
    public static void testOpenIssue() throws InterruptedException {
        bot.openIssue();
    }

    @Test(priority = 4, groups = "positive")
    public static void testUploadAttachment()
    {
        bot.uploadAttachment();
    }

    @Test(priority = 5, groups = "positive")
    public static void testDownloadAttachment() throws IOException, NoSuchAlgorithmException {
        bot.downloadAttachment();
    }

    @Test(priority = 6, groups = "Admin")
    public static void testcreateUser() throws IOException, NoSuchAlgorithmException, InterruptedException {
        bot.createUser();
    }

}
