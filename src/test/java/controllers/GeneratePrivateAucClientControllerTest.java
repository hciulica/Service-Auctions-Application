package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GeneratePrivateAucClientControllerTest extends ApplicationTest {
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_DESCRIPTION = "testDescription";
    public static final String TEST_SEARCH="1";
    public static final ActionEvent event = new ActionEvent();

    private GeneratePrivateAucClientController controller;
    @Before
    public void setUp() throws Exception {
        controller= new GeneratePrivateAucClientController();
        controller.title= new TextField();
        controller.description= new TextArea();
        controller.message=new Label();
        controller.error=new Label();
        controller.pinAuc= new VBox();
        controller.pinAuc= new VBox();
        controller.invited=new VBox();
        controller.search=new TextField();
        controller.invitedBusiness= new ArrayList<>();
        controller.title.setText(TEST_TITLE);
        controller.description.setText(TEST_DESCRIPTION);
        controller.search.setText(TEST_SEARCH);
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
    public void handleInviteButtonAction() throws Exception
    {
        User.addUserProvider("1","1","1","1","1");
        controller.handleInviteButtonAction(event);
        boolean b=false;
        if(controller.invited.getChildren().isEmpty())
        {
            b=true;
        }
        assertEquals(false, b);


    }
    @Test
    public void testBusinnesNotExist() throws Exception
    {
        controller.search.setText("2");
        controller.handleInviteButtonAction(event);
        assertEquals("This Business does not exist!", (String) controller.error.getText());

    }

    @Test
    public void testEmptyFields() throws Exception {
        controller.title.clear();
        controller.handleSubmitButtonAction(event);
        assertEquals("All fields are required!", (String) controller.message.getText());
    }

   @Test
    public void handleSubmitButtonAction() throws Exception {
        controller.invitedBusiness.add("1");
        controller.handleSubmitButtonAction(event);
        boolean b=false;
        if(controller.pinAuc.getChildren().isEmpty())
        {
            b=true;
        }
        assertEquals(false, b);

    }

    @Test
    public void display() throws Exception
    {
        User.addUserClient("1","1","1","1","1");
        LoginController.email="1";
        controller.invitedBusiness.add("1");
        Client.generatePrivAuc("Lictitatie",controller.invitedBusiness,"Descriere");
        controller.display();
        boolean b=false;
        if(controller.pinAuc.getChildren().isEmpty())
        {
            b=true;
        }
        assertEquals(false, b);
    }
}