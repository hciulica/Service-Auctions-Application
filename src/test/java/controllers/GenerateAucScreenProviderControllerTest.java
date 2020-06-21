package controllers;

import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.Client;
import services.User;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class GenerateAucScreenProviderControllerTest extends ApplicationTest {

    GenerateAucScreenProviderController controller;
    @Before
    public void setUp() throws Exception {
        controller = new GenerateAucScreenProviderController();
        controller.pinAuc = new VBox();
        json();
    }

    public static void json()
    {
    //client
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
            arrayClient.clear();
            try {
                File file = new File("src/main/resources/usersClient.json");
                FileWriter fisier = new FileWriter(file.getAbsoluteFile());
                fisier.write(arrayClient.toJSONString());
                fisier.flush();
                fisier.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //provider
            JSONArray arrayProvider = new JSONArray();
            JSONParser jp2 = new JSONParser();
            Object p2;
            try {
                FileReader readFile = new FileReader("src/main/resources/usersProvider.json");
                BufferedReader read = new BufferedReader(readFile);
                p2 = jp2.parse(read);
                if (p2 instanceof JSONArray) {
                    arrayProvider = (JSONArray) p2;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            arrayProvider.clear();
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

    @AfterClass
    public static void afterClass() throws Exception {
        json();
    }

    @Test
    public void display() throws Exception
    {
        User.addUserClient("Nume","Prenume","1","1","1");
        User.addUserProvider("2","Agricultura","2","2","2");
        LoginController.email="1";
        Client.generatePublicAuc("titlu","Agricultura","descriere");
        controller.display();
        boolean b=false;
        if(controller.pinAuc.getChildren().isEmpty())
        {
            b=true;
        }
        assertEquals(false, b);

    }

    @Test
    public void testDisplayNoAuction() throws Exception
    {
        User.addUserClient("Nume","Prenume","1","1","1");
        User.addUserProvider("2","Agricultura","2","2","2");
        LoginController.email="1";
        Client.generatePublicAuc("titlu","Constructii","descriere");
        controller.display();
        boolean b=false;
        if(controller.pinAuc.getChildren().isEmpty())
        {
            b=true;
        }
        assertEquals(true, b);
    }







}