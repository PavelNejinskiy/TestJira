package FacebookAPI;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class BodyFacebook {

    ToolsFacebook tools = new ToolsFacebook();

    static String file = "G:\\QA\\new.xls";

    HashMap<String, String> sortMapWhitID;
    HashMap<String, String> mapWithoutID;
    List<Abonent> list;


    public String getID(String url) throws IOException, ParseException, InterruptedException {

        String body = IOUtils.toString(tools.getRespons(url).getEntity().getContent(), "UTF-8");
        int count = 0;
        String fbid = "";
        //  String fbid = body.substring(body.indexOf("fbid="), body.indexOf("&"));


        char[] mass = body.toCharArray();
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


    public void makeList(HashMap<String, String> map) throws IOException, ParseException, InterruptedException {
        list = new LinkedList<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {

            String[] words = entry.getValue().split("\\s"); // Разбиение строки на слова с помощью разграничителя (пробел)
            String name = words[0];
            String lastName = "";
            String id = "";
            id = getID(entry.getKey());

            for (int i = 1; i < words.length; i++) {
                lastName += words[i];
            }

            list.add(new Abonent(name, lastName, id));

            System.out.println("Name = " + name + " LastName = " + lastName + " ID: " + id);
        }

        //   return list;
    }


    public void sortByID(HashMap<String, String> map) {
        sortMapWhitID = new HashMap<>();
        mapWithoutID = new HashMap<>();
        String temp = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {

            if (entry.getKey().contains("id")&&entry.getKey().contains("&")) {
                temp = entry.getKey().substring(entry.getKey().indexOf("id="), entry.getKey().indexOf("&"));
                sortMapWhitID.put(temp, entry.getValue());
            }
            else {
                mapWithoutID.put(entry.getKey(), entry.getValue());
            }

        }
    }


    public static void main(String[] args) throws IOException, ParseException, InvalidFormatException, InterruptedException {
        String url = "https://www.facebook.com/linda.colombrita.37?fref=grp_mmbr_list";
        BodyFacebook body = new BodyFacebook();
        //  body.makeList((HashMap) new ToolsFacebook().readXls(file));
        //  body.getID(url);
        //   System.out.println("id:" + getID("https://www.facebook.com/linda.colombrita.37?fref=grp_mmbr_list"));


    }
}



