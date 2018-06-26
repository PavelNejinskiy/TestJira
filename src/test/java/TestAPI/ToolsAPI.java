package TestAPI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ToolsAPI {

    HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet("http://www.vogella.com");
    HttpResponse response;
    BufferedReader rd;


    public String httpGet() throws IOException {

        response = client.execute(request);
        rd = new BufferedReader
                (new InputStreamReader(
                        response.getEntity().getContent()));

        String line = "";
        String textView = "";
        while ((line = rd.readLine()) != null) {
            textView.concat(line);
        }

        return textView;
    }




}
