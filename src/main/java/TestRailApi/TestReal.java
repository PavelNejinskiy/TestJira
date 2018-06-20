package TestRailApi;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

public class TestReal {

    public static void main(String[] args) {


        APIClient apiClient = new APIClient("https://hillelmanold.testrail.io");

        apiClient.setUser("rvalek@intersog.com");
        apiClient.setPassword("hillel");


        try {
            JSONObject c = (JSONObject) apiClient.sendGet("get_case/1");
            System.out.println(c.get("title"));
        }
        catch (APIException e)
        {
            System.out.println("connection faled");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
