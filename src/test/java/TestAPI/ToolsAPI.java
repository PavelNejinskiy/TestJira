package TestAPI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.json.JSONObject;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.BufferedReader;
import java.io.IOException;


public class ToolsAPI {

    HttpClient httpClient;
    HttpPost httpPost;
    HttpGet httpGet;
    ResponseHandler<String> responseHandler;
    BufferedReader rd;


    protected String get(String url) {
        httpClient = new DefaultHttpClient();
        httpGet = new HttpGet(url);
        responseHandler = new BasicResponseHandler();
        String responseBody = null;
        try {
            responseBody = httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            //  log.error("IOException", e);
        } finally {

            httpClient.getConnectionManager().shutdown();
        }
        return responseBody;
    }

    protected String post(String url) {
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost(url);
        responseHandler = new BasicResponseHandler();
        String responseBody = null;
        try {
            responseBody = httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            // log.error("IOException", e);
        } finally {

            httpClient.getConnectionManager().shutdown();
        }
        return responseBody;
    }

    protected void myGet(String url) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        try {

            HttpPost request = new HttpPost(url);
            StringEntity params =new StringEntity("details={\"id\":\"3\",\"name\":\"Petrov Petr\",\"phone\":\"+380670000001\", \"role\":\"Support\", \"location\":\"Kiev\"} ");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            //handle response here...

        }catch (Exception ex) {

            //handle exception here

        } finally {
            //Deprecated
            //httpClient.getConnectionManager().shutdown();
        }

        // { id: '3', name: 'Petrov Petr', phone: '+380670000001', role: 'Support', location: 'Kiev' }
    }


}
