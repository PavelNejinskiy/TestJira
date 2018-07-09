package FacebookAPI;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class testFacebookID {


    static String fileRead = "G:\\QA\\test.xls";
    static String fileWrite = "G:\\QA\\result.xls";
    BodyFacebook body = new BodyFacebook();

    @Test
    public void testreadxlsFile() {

    }

    @Test
    public void testwritexlsFile() {

    }

    @Test
    public void testreadAndWrite() throws ParseException, InterruptedException, IOException {
        body.makeList(fileRead, fileWrite);
    }
}
