package Robert.gwSanity;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class RequestApi {
    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static HttpGet sendGetRequest(String url, Map<String, String> headers) {
        HttpGet get = new HttpGet(url);
        System.out.println("Request :: " + get.getRequestLine());
        headers.forEach(get::addHeader);
        getResponse(httpclient, get);

        return get;
    }

    public static HttpPost sendPostRequest(String url, Map<String, String> headers, String bodyAsString, List<NameValuePair> nameValuePairs) {
        HttpPost request = new HttpPost(url);
        System.out.println("Request :: " + request.getRequestLine()); // реально нужно?
        if (headers != null)  // два раза проверяется в разных местах + {}
            headers.forEach(request::setHeader);
        try {
            if (nameValuePairs != null)
                request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            if (bodyAsString != null)
                request.setEntity(new StringEntity(bodyAsString));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        getResponse(httpclient, request);

        return request;
    }

    public static HttpPut sendPutRequest(String url, Map<String, String> headers, String body) {
        HttpPut request = new HttpPut(url);
        System.out.println("Request :: " + request.getRequestLine());
        if (headers != null) {   // скобки!!!
            headers.forEach(request::setHeader);
        }

        try {
            if (body != null) request.setEntity(new StringEntity(body));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        getResponse(httpclient, request);

        return request;
    }

    public static HttpResponse getResponse(CloseableHttpClient client, HttpRequest request) {
        try {
            HttpResponse response = client.execute((HttpUriRequest) request);

            System.out.println("Response Code : " + response.getStatusLine());
//            Header[] headers = response.getAllHeaders();
//
//            for (Header h : headers) {
//                System.out.println(h.toString());
//            }
//
//            BufferedReader rd = new BufferedReader(
//                    new InputStreamReader(response.getEntity().getContent()));
//
//            StringBuilder result = new StringBuilder();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//              System.out.println("Body :: " + result);

            return response;
        } catch (HttpHostConnectException e) {
            System.out.println("Response :: Connection Failed");
            return null;
        } catch (IOException e) { // Для чего его туту ловить ?
            System.out.println("Something bad with executing request");
            return null;
        }
    }
}
