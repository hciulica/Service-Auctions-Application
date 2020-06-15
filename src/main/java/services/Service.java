package services;

import controllers.registration.LoginController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Service {

    //get all service Names
    public static List<String> getServiceNames()
    {
        JSONArray arrayProv= new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/usersProvider.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayProv = (JSONArray) p;
            }




        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String s;
        Iterator<JSONObject> iterator = arrayProv.iterator();
        List<String>names=new ArrayList<>();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();

                    s = (String)obj2.get("Business Name:");
                    names.add(s);


        }
        return names;
    }
    public static JSONArray displayProvAuc(){
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/usersClient.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }




        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray auc = new JSONArray();
        Iterator<JSONObject> iterator = arrayClient.iterator();
        JSONArray display = new JSONArray();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            auc = (JSONArray) obj2.get("Public auction:");
            Iterator<JSONObject> iterator2 = auc.iterator();
           /* for(int i = 0; i<auc.size();i++){
                JSONObject obj3 = iterator.next();

                if (obj3.get("Activity Field:").equals(User.activityF)) {

                    //takes the right vector that must be displayed
                    //display = (JSONArray) obj2.get("Public auction:");
                    display.add(obj3);
                    System.out.println(display);
            }*/
            while(iterator2.hasNext()) {
                JSONObject obj3 = iterator2.next();

                if (obj3.get("Activity Field:").equals(User.activityF)) {

                    //takes the right vector that must be displayed
                    //display = (JSONArray) obj2.get("Public auction:");
                    display.add(obj3);


                }
            }

        }
        System.out.println(display);
        return display;

    }
}
