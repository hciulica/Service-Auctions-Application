package services;

import exception.EmailNotAvailable;
import exception.EmptyFieldException;
import exception.IncorrectMailOrPassException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;
import java.util.Iterator;

public class User {
    public static String key = "Jar12345Jar12345";
    public static String initVector = "RandomInitVector";
    public static String name;
    public static String activityF;

    //adds clients in json file: usersClient
    public static void addUserClient(String firstName, String lastName, String phoneNr, String email, String password) throws EmptyFieldException,EmailNotAvailable {

        checkIfFieldsAreEmptyClient(firstName, lastName, email, phoneNr, password);
        name=firstName+" "+lastName;
        JSONObject obj = new JSONObject();
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
        Iterator<JSONObject> iterator = arrayClient.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Email:").equals(email))
            {
                throw new EmailNotAvailable();
            }

        }
        JSONArray array= new JSONArray();
        obj.put("First Name:", firstName);
        obj.put("Last Name:", lastName);
        obj.put("Email:",email);
        obj.put("Phone Nr:", phoneNr);
        obj.put("Password:", encodePassword(key,initVector,password));
        obj.put("Public auction:", array);
        obj.put("Private auction:", array);
        //obj.put("Password:",password);
        arrayClient.add(obj);
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

    //adds clients in json file:usersProvider
    public static void addUserProvider(String bussinesName, String activityField, String phoneNr, String email, String password) throws EmptyFieldException, EmailNotAvailable{

        checkIfFieldsAreEmptyProv(bussinesName, activityField, phoneNr, email, password);
        name=bussinesName;
        activityF = activityField;
        JSONObject obj = new JSONObject();
        JSONArray arrayProvider = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/usersProvider.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayProvider = (JSONArray) p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Iterator<JSONObject> iterator = arrayProvider.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Email:").equals(email))
            {
                throw new EmailNotAvailable();
            }

        }
        JSONArray array = new JSONArray();
        obj.put("Business Name:", bussinesName);
        obj.put("Activity Field:", activityField);
        obj.put("Email:", email);
        obj.put("Phone Nr:", phoneNr);
        obj.put("Password:",encodePassword(key,initVector,password));
        obj.put("Submited Auction:",array);
        obj.put("Wins:",array);
        double i=0;
        obj.put("Rate:",i);
        arrayProvider.add(obj);
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


    //checks if any fields are empty for Client
    private static void checkIfFieldsAreEmptyClient(String firstName, String lastName, String email, String phoneNr, String password) throws EmptyFieldException {

        if (firstName.isEmpty() | lastName.isEmpty() | email.isEmpty() | phoneNr.isEmpty() | password.isEmpty())
            throw new EmptyFieldException();
    }

    //checks if any fields are empty for Provider
    private static void checkIfFieldsAreEmptyProv(String bussinesName, String activityField, String phoneNr, String email, String password) throws EmptyFieldException {

        if (bussinesName.isEmpty() | activityField == null | email.isEmpty() | phoneNr.isEmpty() | password.isEmpty())
            throw new EmptyFieldException();
    }

  //encrypts passwords
    public static String encodePassword(String key, String initVector, String value)
    {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //checks if the user exists in a json file
    static boolean correctAccount = false;
    public static String loginCheckClient(String email, String password)throws IncorrectMailOrPassException {
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


        while (iterator.hasNext())
        {
            JSONObject obj = iterator.next();
            if (obj.get("Email:").equals(email)&& obj.get("Password:").equals(encodePassword(key,initVector,password)))
            {
                correctAccount = true;
                name=(String)obj.get("First Name:")+" "+(String)obj.get("Last Name:");
                return "Client";
            }

        }

        return"";

    }

    public static String loginCheckProv(String email, String password)throws IncorrectMailOrPassException {
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



        while (iterator.hasNext())
        {
            JSONObject obj = iterator.next();
            if (obj.get("Email:").equals(email)&& obj.get("Password:").equals(encodePassword(key,initVector,password)))
            {
                correctAccount = true;
                name=(String)obj.get("Business Name:");
                activityF = (String)obj.get("Activity Field:");
                return "Provider";
            }

        }

        return"";

    }

    //if there is no user in the files throws incorrect mail or pass
    public static void checkIncorrect()throws IncorrectMailOrPassException
    {
        if(!correctAccount)
        {
            throw new IncorrectMailOrPassException();
        }
    }


}


