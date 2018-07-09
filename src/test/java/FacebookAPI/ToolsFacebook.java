package FacebookAPI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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


    public Subscriber deleteName(String line)
    {
        String name = "";
        String lastName = "";
        String id = "";


            String[] words = line.split("\\s"); // Разбиение строки на слова с помощью разграничителя (пробел)
            name = words[0];
            for (int i = 1; i < words.length; i++) {
                lastName += words[i];
            }

        return new Subscriber(name, lastName, id);
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


    public static void writeIntoExcel(String file, List <Subscriber> list) throws FileNotFoundException, IOException{
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("result");

        Row row = sheet.createRow(0);

        for (int i = 0; i < list.size(); i++) {

            Cell name = row.createCell(i);
            Cell lastName = row.createCell(i+1);
            Cell id = row.createCell(i+2);
            name.setCellValue(list.get(i).name);
            lastName.setCellValue(list.get(i).lastName);
            id.setCellValue(list.get(i).ID);

        }
//        // Меняем размер столбца
//        sheet.autoSizeColumn(1);

        book.write(new FileOutputStream(file));
        book.close();
    }


}
