package TestAPI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


public class ToolsAPI {

//
//    public void whenPostJsonUsingHttpClient_thenCorrect() throws ClientProtocolException, IOException {
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://www.example.com");
//
//        String json = "details={\"id\":\"3\",\"name\":\"Petrov Petr\",\"phone\":\"+380670000001\", \"role\":\"Support\", \"location\":\"Kiev\"}";
//        StringEntity entity = new StringEntity(json);
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-type", "application/json");
//
//        CloseableHttpResponse response = client.execute(httpPost);
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
//        client.close();
//    }

    public CloseableHttpResponse getRespons(String url) throws ClientProtocolException, IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("accept", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);

        return response;
    }

    public CloseableHttpResponse getHttpGet(String url) throws ClientProtocolException, IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        httpget.addHeader("accept", "application/json");
        CloseableHttpResponse response = client.execute(httpget);

        return response;
    }





}
