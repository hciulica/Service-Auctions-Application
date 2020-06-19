package services;

import controllers.registration.LoginController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
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
            System.out.println(obj4);
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


}

