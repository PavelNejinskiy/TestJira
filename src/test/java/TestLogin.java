import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestLogin {


    private static WebDriver driver;

    @BeforeTest
    public static void openPage() {
        login();
    }


    @Test
    public static void testLogin() throws InterruptedException {
        Assert.assertEquals(driver.findElement(By.cssSelector(".aui-page-header-main > h1:nth-child(1)")).getText(), "System Dashboard");

    }

    @Test
    public static void testCreateIssue() throws InterruptedException {
        myElement(By.cssSelector("#browse_link")).click();
        myElement(By.cssSelector("#admin_main_proj_link_lnk")).click();
        myElement(By.cssSelector("#create_link")).click();
        myElement(By.cssSelector("#summary")).sendKeys("Test Issue");
        myElement(By.cssSelector("#create-issue-submit")).click();
        Assert.assertEquals(true, myElement(By.cssSelector(".aui-message")).getText().contains("Test Issue"));

    }

    @Test
    public static void testOpenIssue() throws InterruptedException {
        myElement(By.cssSelector("#find_link")).click();
        myElement(By.cssSelector("#issue_lnk_15926_lnk")).click();
        Assert.assertEquals(myElement(By.cssSelector("#summary-val")).getText(), "Test Issue");
    }

    @AfterTest
    public static void closePage() throws InterruptedException {
        close();
    }



    public static void open(String url)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }


    public static void login() {
        open("http://jira.hillel.it:8080");

        myElement(By.cssSelector("#login-form-username")).sendKeys("Pavel");
        myElement(By.cssSelector("#login-form-password")).sendKeys("droplles");
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
