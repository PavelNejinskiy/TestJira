package FacebookAPI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;

public class ToolsFacebook {


    public CloseableHttpResponse getRespons(String url) throws ClientProtocolException, IOException {



        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("accept", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);

        //client.close();

        return response;
    }
}
