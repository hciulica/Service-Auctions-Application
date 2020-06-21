package services;

import controllers.LoginController;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client {
    //get the reference for the current obj for the close auction
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
                JSONArray array = new JSONArray();
                array2= (JSONArray)obj2.get("Public auction:");
                obj2.remove("Public auction:");
                obj3.put("Title:", title);
                obj3.put("Activity Field:", activityField);
                obj3.put("Description:", description);
                obj3.put("Submited prices:",array);
                auc=obj3;
                array2.add(obj3);
                obj2.put("Public auction:", array2);
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

    public static void generatePrivAuc(String title, List<String> invitedBusiness, String description)
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
        JSONArray array = new JSONArray();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Email:").equals(LoginController.email))
            {

                //writes the auction inside de Auction array
                JSONArray array2= new JSONArray();
                array2= (JSONArray)obj2.get("Private auction:");
                obj2.remove("Private auction:");
                obj3.put("Title:", title);
                obj3.put("Invited Businesses:", invitedBusiness);
                obj3.put("Description:", description);
                obj3.put("Submited prices:",array);
                auc=obj3;
                array2.add(obj3);
                obj2.put("Private auction:", array2);
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
                display= (JSONArray) obj2.get("Public auction:");


            }

        }
        return display;

    }

    public static JSONArray displayPrivAuctions()
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
                display= (JSONArray) obj2.get("Private auction:");


            }

        }
        return display;

    }


    //sets the status to closed
    public static void close(JSONObject obj, String winner)
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
        JSONArray win=new JSONArray();
        JSONObject obj4= new JSONObject();
        String s= new String();


        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();

            if (obj2.get("Email:").equals(LoginController.email))
            {


                d= (JSONArray) obj2.get("Public auction:");
                JSONObject obj3 = obj;
                d.remove(obj);
                obj3.put("Status:","closed");
                s=(String) obj3.get("Title:");
                if(winner!=null) {
                    obj3.put("Winner:", win);
                }
                d.add(obj3);

            }
        }

        if(winner!=null) {
            int index = 0;
            for (String word : winner.split(" ")) {
                if (index == 0) {
                    obj4.put("Name:", word);

                }
                if (index == 2) {
                    obj4.put("Price:", word);

                }
                index++;

            }
            win.add(obj4);

            submit(obj4,s);
            closeed(obj);
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


    public static void closePriv(JSONObject obj, String winner)
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
        String s=new String();
        Iterator<JSONObject> iterator = arrayClient.iterator();
        JSONArray win=new JSONArray();
        JSONObject obj4= new JSONObject();

        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();

            if (obj2.get("Email:").equals(LoginController.email))
            {


                d= (JSONArray) obj2.get("Private auction:");
                JSONObject obj3 = obj;
                d.remove(obj);
                obj3.put("Status:","closed");
                s=(String) obj3.get("Title:");

                if(winner!=null) {
                    obj3.put("Winner:", win);
                }
                d.add(obj3);
            }
            if(winner!=null) {
                int index = 0;
                for (String word : winner.split(" ")) {
                    if (index == 0) {
                        obj4.put("Name:", word);

                    }
                    if (index == 2) {
                        obj4.put("Price:", word);

                    }
                    index++;

                }
                win.add(obj4);
                submit(obj4,s);
                closeed(obj);
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

    public static void submit(JSONObject obj4,String title)
    {
        JSONParser parser = new JSONParser();
        Object p2;
        JSONArray arrayProvider = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/resources/usersProvider.json");
            BufferedReader read = new BufferedReader(readFile);
            p2 = parser.parse(read);
            if (p2 instanceof JSONArray) {
                arrayProvider = (JSONArray) p2;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        JSONObject obj3 = new JSONObject();
        Iterator<JSONObject> iterator2 = arrayProvider.iterator();
        while (iterator2.hasNext())
        {
            JSONObject obj2 = iterator2.next();
            if (obj2.get("Business Name:").equals(obj4.get("Name:")))
            {
                JSONArray array2= new JSONArray();
                JSONArray array = new JSONArray();
                array2= (JSONArray)obj2.get("Wins:");
                obj2.remove("Wins:");
                obj3.put("Client:", User.name);
                obj3.put("Price:", obj4.get("Price:"));
                obj3.put("Title:",title);
                array2.add(obj3);
                obj2.put("Wins:", array2);
                arrayProvider.add(obj2);
                arrayProvider.remove(obj2);
                break;
            }
        }
        try {
            File file = new File("src/main/resources/usersProvider.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayProvider.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void closeed(JSONObject obj4)
    {
        JSONParser parser = new JSONParser();
        Object p2;
        JSONArray arrayProvider = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/resources/usersProvider.json");
            BufferedReader read = new BufferedReader(readFile);
            p2 = parser.parse(read);
            if (p2 instanceof JSONArray) {
                arrayProvider = (JSONArray) p2;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        JSONObject obj3 = new JSONObject();
        Iterator<JSONObject> iterator2 = arrayProvider.iterator();
        while (iterator2.hasNext())
        {
            JSONObject obj2 = iterator2.next();
            JSONArray array2= new JSONArray();
           // if(obj2.get("Activity Field:").equals(obj4.get("Activity Field:"))) {
                array2 = (JSONArray) obj2.get("Submited Auction:");
                Iterator<JSONObject> iterator3 = array2.iterator();
                while(iterator3.hasNext()) {
                    JSONObject obj6 = iterator3.next();
                    if(obj6.get("Title:").equals(obj4.get("Title:")) && obj6.get("Description:").equals(obj4.get("Description:")))
                    {
                        obj2.remove("Submited Auction:");
                        JSONObject obj7 = obj6;
                        array2.remove(obj6);
                        obj7.put("Status:","closed");
                        array2.add(obj7);
                        obj2.put("Submited Auction:", array2);
                    }
                }
            }

        try {
            File file = new File("src/main/resources/usersProvider.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayProvider.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<String> rating() {
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
        JSONObject obj5 = new JSONObject();
        List<String> lista = new ArrayList<>();
        Iterator<JSONObject> iterator = arrayClient.iterator();
        JSONArray display = new JSONArray();
        while (iterator.hasNext()) {
            JSONObject obj2 = iterator.next();

            if (obj2.get("Email:").equals(LoginController.email)) {

                JSONArray arr = new JSONArray();
                arr = (JSONArray) obj2.get("Public auction:");
                Iterator<JSONObject> iterator2 = arr.iterator();
                while (iterator2.hasNext()) {
                    JSONArray arr2 = new JSONArray();
                    obj3 = iterator2.next();
                    arr2 = (JSONArray) obj3.get("Winner:");
                    //arr2.get(0);
                    JSONObject obj4 = (JSONObject) arr2.get(0);
                    if (!lista.contains((String) obj4.get("Name:"))) {
                        lista.add((String) obj4.get("Name:"));
                    }


                }
                //takes the right vector that must be displayed

                JSONArray arr1 = new JSONArray();
                arr1 = (JSONArray) obj2.get("Private auction:");
                Iterator<JSONObject> iterator3 = arr1.iterator();
                while (iterator3.hasNext()) {
                    JSONArray arr3 = new JSONArray();
                    obj5 = iterator3.next();
                    arr3 = (JSONArray) obj5.get("Winner:");
                    //arr2.get(0);
                    JSONObject obj6 = (JSONObject) arr3.get(0);
                    if (!lista.contains((String) obj6.get("Name:"))) {
                        lista.add((String) obj6.get("Name:"));
                    }

                }

            }
        }
        return lista;
    }
    public static JSONArray compare(){
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/usersProvider.json");
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
        List<String> lista = new ArrayList<>();
        lista = rating();

        Iterator<JSONObject> iterator = arrayClient.iterator();
        JSONArray display = new JSONArray();

       while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            for(int i=0 ;i<lista.size();i++){
                if(obj2.get("Business Name:").equals(lista.get(i))){
                    JSONObject obj4 = new JSONObject();
                    obj4.put("Name:",obj2.get("Business Name:"));
                    obj4.put("Rate:", obj2.get("Rate:"));

                    display.add(obj4);
                }
            }


        }
    return display;
    }
    public static void submitRate(double value,String name){
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/resources/usersProvider.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Iterator<JSONObject> iterator = arrayClient.iterator();



        JSONObject obj3= new JSONObject();
        double d=value;
        while (iterator.hasNext())
        {

            JSONObject obj2 = iterator.next();
            if(obj2.get("Business Name:").equals(name)){
            if(!obj2.get("Rate:").equals(0.0)) {
                d = (d + (double) obj2.get("Rate:")) / 2;
            }


            obj2.remove("Rate:");
            obj2.put("Rate:",d);
            }
        }
        try {
            File file = new File("src/main/resources/usersProvider.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void overView(Label money, Label progress, Label total)
    {
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/resources/usersClient.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Iterator<JSONObject> iterator = arrayClient.iterator();


        Integer profit=0;
        Integer prog=0;
        Integer wins=0;

        while (iterator.hasNext()) {
            JSONObject obj2 = iterator.next();

                if(obj2.get("Email:").equals(LoginController.email)){
                    wins = displayover().size();
                    Iterator<JSONObject> iterator2 = displayover().iterator();
                    while(iterator2.hasNext()){
                        JSONObject obj1 = iterator2.next();
                        if(!obj1.containsKey("Status:")){
                            prog++;
                        }
                        if(obj1.containsKey("Winner:")) {
                            JSONArray arr1 = (JSONArray) obj1.get("Winner:");
                            Iterator<JSONObject> iterator3 = arr1.iterator();
                            while (iterator3.hasNext()) {
                                JSONObject obj3 = iterator3.next();


                                if (obj3.containsKey("Price:")) {


                                    profit = profit + Integer.parseInt((String)obj3.get("Price:"));

                                }
                            }
                        }
                    }
                }
            }



        money.setText(Integer.toString(profit)+ " RON");
        progress.setText(Integer.toString(prog));
        total.setText(Integer.toString(wins));
    }
    public static JSONArray displayover()
    {
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/resources/usersClient.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Iterator<JSONObject> iterator = arrayClient.iterator();
        JSONArray display = new JSONArray();

        while (iterator.hasNext()) {
            JSONObject obj2 = iterator.next();
            JSONArray arr1 = (JSONArray)obj2.get("Public auction:");
            JSONArray arr2 = (JSONArray)obj2.get("Private auction:");
            Iterator<JSONObject> iterator2 = arr2.iterator();
            if(obj2.get("Email:").equals(LoginController.email)){
                display = arr1;
            while(iterator2.hasNext()){
                JSONObject obj1 = iterator2.next();
                display.add(obj1);
            }

            }




        }
            return display;
    }
}

