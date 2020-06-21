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

public class Service {
private static JSONObject submitedAuc = new JSONObject();
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

            while(iterator2.hasNext()) {
                JSONObject obj3 = iterator2.next();

                if (obj3.get("Activity Field:").equals(User.activityF)) {

                    display.add(obj3);


                }
            }

        }

        return display;

    }
    public static void submitPrice(String price,String title,String description){
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
        JSONArray d = new JSONArray();
        Iterator<JSONObject> iterator = arrayClient.iterator();

        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            auc = (JSONArray) obj2.get("Public auction:");
            Iterator<JSONObject> iterator2 = auc.iterator();

            while(iterator2.hasNext()) {
                JSONObject obj3 = iterator2.next();

                if (obj3.get("Activity Field:").equals(User.activityF) && obj3.get("Title:").equals(title) && obj3.get("Description:").equals(description))
                {
                    d= (JSONArray) obj3.get("Submited prices:");
                    obj3.remove("Submited prices:");
                    submitedAuc = obj3;
                    submitedAuc.put("Submited prices:",price);
                    addSubmitedAucProv(submitedAuc);
                    JSONObject obj4=new JSONObject();
                    //d.remove(obj3);
                    obj4.put(User.name,price);
                    d.add(obj4);
                    obj3.put("Submited prices:",d);


                }
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
    public static void addSubmitedAucProv(JSONObject submited){
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
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            JSONArray array2= new JSONArray();
            if(obj2.get("Business Name:").equals(User.name)) {
                array2 = (JSONArray) obj2.get("Submited Auction:");
                obj2.remove("Submited Auction:");

                array2.add(submited);
                obj2.put("Submited Auction:", array2);
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
    public static JSONArray displayPrivateAuc() {
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
        while (iterator.hasNext()) {
            JSONObject obj2 = iterator.next();
            auc = (JSONArray) obj2.get("Private auction:");
            Iterator<JSONObject> iterator2 = auc.iterator();

            while (iterator2.hasNext()) {
                JSONObject obj3 = iterator2.next();
                 List<String> invited=(List)obj3.get("Invited Businesses:");
                if (invited.contains(User.name)) {
                    display.add(obj3);
                }
            }

        }

        return display;
    }
    public static void submitPricePrivate(String price,String title,String description){
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
        JSONArray d = new JSONArray();
        Iterator<JSONObject> iterator = arrayClient.iterator();

        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            auc = (JSONArray) obj2.get("Private auction:");
            Iterator<JSONObject> iterator2 = auc.iterator();

            while(iterator2.hasNext()) {
                JSONObject obj3 = iterator2.next();

                if (obj3.get("Title:").equals(title) && obj3.get("Description:").equals(description))
                {
                    d= (JSONArray) obj3.get("Submited prices:");
                    obj3.remove("Submited prices:");
                    submitedAuc = obj3;
                    submitedAuc.put("Submited prices:",price);
                    addSubmitedAucProv(submitedAuc);
                    JSONObject obj4=new JSONObject();
                    //d.remove(obj3);
                    obj4.put(User.name,price);
                    d.add(obj4);
                    obj3.put("Submited prices:",d);


                }
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

    public static void overView(Label prof,Label progress, Label winnings)
    {
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


        Integer profit=0;
        Integer prog=0;
        Integer wins=0;

        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            JSONArray array2= new JSONArray();
            JSONArray array3= new JSONArray();
            if(obj2.get("Business Name:").equals(User.name)) {
                //profit
                array2 = (JSONArray) obj2.get("Wins:");
                wins=array2.size();
                Iterator<JSONObject> iterator2 = array2.iterator();
                while(iterator2.hasNext())
                {
                    JSONObject obj3= iterator2.next();
                    profit=profit+ Integer.parseInt((String)obj3.get("Price:"));

                }
                //auction in progress
                array3= (JSONArray) obj2.get("Submited Auction:");
                Iterator<JSONObject> iterator3 = array3.iterator();
                while(iterator3.hasNext())
                {
                    JSONObject obj3= iterator3.next();
                    if(!obj3.containsKey("Status:")){
                        prog++;
                    }

                }


            }

        }

        prof.setText(Integer.toString(profit)+ " RON");
        progress.setText(Integer.toString(prog));
        winnings.setText(Integer.toString(wins));
    }

    public static JSONArray displayOverview()
    {
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
        Iterator<JSONObject> iterator = arrayClient.iterator();
        JSONArray display = new JSONArray();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();

            if (obj2.get("Email:").equals(LoginController.email))
            {
                JSONArray arr=(JSONArray)obj2.get("Wins:");
                //takes the right vector that must be displayed
                display= (JSONArray) obj2.get("Submited Auction:");
                Iterator<JSONObject> iterator2 = arr.iterator();
                Iterator<JSONObject> iterator3 = display.iterator();
                while(iterator3.hasNext())
                {
                    JSONObject object2 = iterator3.next();
                    while (iterator2.hasNext())
                    {
                       //if an auction is closed but also won by the provider it should be only the won one in the array
                        JSONObject object = iterator2.next();
                        if(object2.get("Title:").equals(object.get("Title:")))
                        {
                            display.remove(object2);
                        }
                        display.add(object);

                    }
                    break;
                }




            }

        }
        return display;

    }
}
