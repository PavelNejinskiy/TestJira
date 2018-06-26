package MyTestJira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    private static WebDriver driver;

    public static void setDriver(WebDriver browser) {
        Tools.driver = browser;
    }

    public static WebElement myElement(By by)
    {
        WebElement element = driver.findElement(by);
        return element;
    }

    public static WebElement clearAndFill(By selector, String data) {
        WebElement element = driver.findElement(selector);
        element.clear();
        element.sendKeys(data);

        return element;
    }



    public static String timestamp() {
        return new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date());
    }



    public static String md5Apache(File file) throws NoSuchAlgorithmException, FileNotFoundException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        InputStream is = new FileInputStream(file);
        String output = "";
        byte[] buffer = new byte[8192];
        int read = 0;
        try {
            while( (read = is.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }
            byte[] md5sum = digest.digest();
            BigInteger bigInt = new BigInteger(1, md5sum);
            output = bigInt.toString(16);
        }
        catch(IOException e) {
            throw new RuntimeException("Unable to process file for MD5", e);
        }
        finally {
            try {
                is.close();
            }
            catch(IOException e) {
                throw new RuntimeException("Unable to close input stream for MD5 calculation", e);
            }
        }

        return output;
    }
}
