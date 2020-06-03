package registration;

import controllers.LoginController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class Client {

    public static JSONObject auc;
    //writes the new auction in the json file
    public static void generatePublicAuc(String title, String activityField, String description) {
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

        JSONObject obj3 = new JSONObject();
        Iterator<JSONObject> iterator = arrayClient.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Email:").equals(LoginController.email))
            {

                //writes the auction inside de Auction array
                JSONArray array2= new JSONArray();
                array2= (JSONArray)obj2.get("Auction:");
                obj2.remove("Auction:");
                obj3.put("Title:", title);
                obj3.put("Activity Field:", activityField);
                obj3.put("Description:", description);
                auc=obj3;
                array2.add(obj3);
                obj2.put("Auction:", array2);
                arrayClient.add(obj2);
                arrayClient.remove(obj2);
                break;

            }

        }



        try {
            File file = new File("src/main/resources/usersClient.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static JSONArray displayAuctions()
    {
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

        JSONObject obj3 = new JSONObject();
        Iterator<JSONObject> iterator = arrayClient.iterator();
        JSONArray display = new JSONArray();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();

            if (obj2.get("Email:").equals(LoginController.email))
            {

                //takes the right vector that must be displayed
                display= (JSONArray) obj2.get("Auction:");


            }

        }
        return display;

    }
//sets the status to closed
public static void close(JSONObject obj)
{
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
    JSONArray d = new JSONArray();

    Iterator<JSONObject> iterator = arrayClient.iterator();
    while (iterator.hasNext())
    {
        JSONObject obj2 = iterator.next();

        if (obj2.get("Email:").equals(LoginController.email))
        {


            d= (JSONArray) obj2.get("Auction:");
            JSONObject obj3 = obj;
            d.remove(obj);
            obj3.put("Status:","closed");
            d.add(obj3);
            System.out.println(obj3);



        }

    }


    try {
        File file = new File("src/main/resources/usersClient.json");
        FileWriter fisier = new FileWriter(file.getAbsoluteFile());
        fisier.write(arrayClient.toJSONString());
        fisier.flush();
        fisier.close();
    } catch (IOException e) {
        e.printStackTrace();
    }


}
}

