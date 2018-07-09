package FacebookAPI;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class BodyFacebook {

    ToolsFacebook tools = new ToolsFacebook();

    static String fileRead = "G:\\QA\\new.xls";
    static String fileWrite = "G:\\QA\\result.xls";

    HashMap<String, String> sortMapWhitID;
    HashMap<String, String> mapWithoutID;
    List<Subscriber> list;


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


    public void makeList(String fileForRead, String fileForWrite) throws IOException, ParseException, InterruptedException {
        list = new LinkedList<>();

        sortByID((HashMap) new ToolsFacebook().readXls(fileForRead));
        // add abonents whit ID
        for (Map.Entry<String, String> entry : sortMapWhitID.entrySet()) {
            Subscriber subscriber = tools.deleteName(entry.getValue());
            list.add(new Subscriber(subscriber.name, subscriber.lastName, subscriber.ID));
        }

        // get ID from Facebook and add to list
        for (Map.Entry<String, String> entry : mapWithoutID.entrySet()) {
            Subscriber subscriber = tools.deleteName(entry.getValue());
            list.add(new Subscriber(subscriber.name, subscriber.lastName, getID(entry.getKey())));

           // The visibility of adding
            System.out.println(subscriber.name + " " + subscriber.lastName + " " + getID(entry.getKey()));
        }


       tools.writeIntoExcel(fileForWrite, list);

    }


    public void sortByID(HashMap<String, String> map) {
        sortMapWhitID = new HashMap<>();
        mapWithoutID = new HashMap<>();
        String temp = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {

            if (entry.getKey().contains("id") && entry.getKey().contains("&")) {
                temp = entry.getKey().substring(entry.getKey().indexOf("id="), entry.getKey().indexOf("&"));
                sortMapWhitID.put(temp, entry.getValue());
            } else {
                mapWithoutID.put(entry.getKey(), entry.getValue());
            }

        }
    }


    public static void main(String[] args) throws IOException, ParseException, InvalidFormatException, InterruptedException {

        BodyFacebook body = new BodyFacebook();
       // body.makeList();
    }
}



