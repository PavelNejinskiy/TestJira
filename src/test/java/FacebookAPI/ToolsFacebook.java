package FacebookAPI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

public class ToolsFacebook {



    public CloseableHttpResponse getRespons(String url) throws IOException, InterruptedException {


        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("accept", "application/json");
        CloseableHttpResponse response = client.execute(httpPost);

        //client.close();
        //httpPost.releaseConnection();

        return response;
    }

    public List<String> getLinks(String url) throws IOException {

        List<String> list = new LinkedList<>();

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            list.add(link.toString());
        }

        return list;
    }



    public HashMap<String,String> readXls(String file) {

        HashMap<String, String> map = new LinkedHashMap<>();
        int count = 1;

        String strName = "";
        String strAdress = "";
        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            in = new FileInputStream(file);
            wb = new HSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                if (count % 2 != 0) {
                    strName = cell.getStringCellValue();
                } else {
                    strAdress = cell.getStringCellValue();
                    map.put(strAdress, strName);
                }
                count++;
            }
        }

        return map;
    }


}
