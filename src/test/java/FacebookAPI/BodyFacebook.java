package FacebookAPI;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class BodyFacebook {

    //BodyFacebook fb = new BodyFacebook();

    static ToolsFacebook tools = new ToolsFacebook();
    String url = "https://www.facebook.com/linda.colombrita.37?fref=grp_mmbr_list";

    static HashMap<String, List<String>> map = new LinkedHashMap<>();


    public static String getID(String url) throws IOException, ParseException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader((tools.getRespons(url).getEntity().getContent())));

        String str = "";
        int count = 0;
        String fbid = "";

        while (br.readLine() != null) {
            str += br.readLine();
        }

        char[] mass = str.toCharArray();
        char[] id = "fbid=\"".toCharArray();

        for (int i = 0; i < mass.length; i++) {

            if (mass[i] == id[count]) {

                if (count == 5) {
                    i++;
                    while (Character.isDigit(mass[i])) {
                        fbid += mass[i];
                        i++;
                    }
                    break;
                }
                count++;
            } else {
                count = 0;
            }
        }
        return fbid;
    }

//String sheetName, File file
    public void readExcel() throws IOException, InvalidFormatException {
        InputStream inp = getClass().getResourceAsStream("G:\\QA\\new.xls");
        Workbook wb = WorkbookFactory.create(inp);
        DataFormatter objDefaultFormat = new DataFormatter();
        FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) wb);

        Sheet sheet= wb.getSheetAt(0);
        Iterator<Row> objIterator = sheet.rowIterator();

        while(objIterator.hasNext()){

            Row row = objIterator.next();
            Cell cellValue = row.getCell(0);
            objFormulaEvaluator.evaluate(cellValue); // This will evaluate the cell, And any type of cell will return string value
            String cellValueStr = objDefaultFormat.formatCellValue(cellValue,objFormulaEvaluator);

        }
    }

    public static void readFile(String path) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
        String line = "";
        while (reader.readLine() != null) {
            line += reader.readLine();
            deleteLine(line);

        }

    }


    public static void deleteLine(String line) throws IOException, ParseException {
        String name = "";
        String lastName = "";
        String httpLink = "";
        String temp = "";
        List<String> list = new LinkedList<>();
        int count = 0;

        char[] mass = line.toCharArray();

        for (int i = 0; i < mass.length; i++) {
            temp += mass[i];
            if (mass[i] == " ".toCharArray()[0] && count == 0) {
                name += temp;
                temp = "";
                count++;
            }
            if (mass[i] == " ".toCharArray()[0] && count == 1) {
                lastName += temp;
                temp = "";
                count++;
            }

            if (mass.length - 2 == i) {
                httpLink += temp;
                temp = "";
                count = 0;
            }
        }
        list.add(name);
        list.add(lastName);

        map.put(getID(httpLink), list);

    }


    public static void main(String[] args) throws IOException, ParseException, InvalidFormatException {

      new BodyFacebook().readExcel();

        // System.out.println("fbid = " + fb.getID(url));

        // fb.readExcel("new", new File("G:\\QA\\new.xml"));
       // readFile("G:\\QA\\new.xls");

//        for (HashMap.Entry<String, List<String>> entry : map.entrySet()) {
//            String key = entry.getKey();
//            String name = entry.getValue().get(0);
//            String lastName = entry.getValue().get(1);
//
//            System.out.println(key + " " + name + " " + lastName);
//        }
    }
}


//        JSONParser parser = new JSONParser();
//        JSONObject json = (JSONObject) parser.parse(str);

//        HttpEntity httpEntity = tools.getRespons(url).getEntity();
//        String apiOutput = EntityUtils.toString(httpEntity);
//
//        System.out.println(apiOutput);

//  System.out.println(json.get("id"));
