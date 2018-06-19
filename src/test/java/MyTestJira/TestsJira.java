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

















//    @AfterGroups(groups = "positive")
//    public static void closePage() throws InterruptedException {
//        close();
//    }



//
//    public static void close() throws InterruptedException {
//        Thread.sleep(5000);
//        driver.close();
//    }
//
//
//    public static WebElement myElement(By by)
//    {
//        WebElement element = driver.findElement(by);
//        return element;
//    }
//
//
//    public static void open(String url)
//    {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromdriver\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized","--incognito");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get(url);
//        driver.manage().timeouts().implicitlyWait(5, SECONDS);
//    }
//
//
//    public static void login(String login, String password) {
//        open("http://jira.hillel.it:8080");
//
//        myElement(By.cssSelector("#login-form-username")).sendKeys(login);
//        myElement(By.cssSelector("#login-form-password")).sendKeys(password);
//        myElement(By.cssSelector("#login")).click();
//
//    }

}
