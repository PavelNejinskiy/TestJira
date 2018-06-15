package MyTestJira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestAttachment {

    private static WebDriver driver;

    static String login = "Pavel";
    static String password = "droplles";

    @BeforeTest
    public static void openPage() {
        login(login, password);
    }

    @Test
    public static void testUpload() throws InterruptedException {
        myElement(By.cssSelector("#find_link")).click();
        myElement(By.cssSelector("#issue_lnk_15933_lnk")).click();
        WebElement element = driver.findElement(By.cssSelector(".issue-drop-zone__button"));
        element.click();
       // driver.switchTo().activeElement().sendKeys("G:\\темп\\kid.jpg");

     //   driver.get();

       // driver.findElement(By.id("clientUpload")).sendKeys("G:\\темп\\kid.jpg");
       Thread.sleep(2000);
        element.click();
        Thread.sleep(5000);

    }

    @AfterTest
    public static void closePage() throws InterruptedException {
        close();
    }

    public static void open(String url)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromdriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized","--incognito");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    public static void login(String login, String password) {
        open("http://jira.hillel.it:8080");

        myElement(By.cssSelector("#login-form-username")).sendKeys(login);
        myElement(By.cssSelector("#login-form-password")).sendKeys(password);
        myElement(By.cssSelector("#login")).click();

    }

    public static void close() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }


    public static WebElement myElement(By by)
    {
        WebElement element = driver.findElement(by);
        return element;
    }
}
