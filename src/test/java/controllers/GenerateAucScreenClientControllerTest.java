package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

public class GenerateAucScreenClientControllerTest extends ApplicationTest {
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_DESCRIPTION = "testDescription";
    public static final ActionEvent event = null;

    GenerateAucScreenClientController controller;
    @Before
    public void setUp() throws Exception {
        controller= new GenerateAucScreenClientController();
        controller.title= new TextField();
        controller.description= new TextArea();
        controller.message=new Label();
        controller.fieldBtn=new ComboBox<String>();
        controller.fieldBtn.setItems(controller.activityField);
        controller.fieldBtn.setValue("Agricultura");
        controller.pinAuc= new VBox();

        controller.title.setText(TEST_TITLE);
        controller.description.setText(TEST_DESCRIPTION);

        json();

    }
    public static void json()
    {
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
    }

    @AfterClass
    public static void afterClass() throws Exception {
        json();
    }

    @Test
    public void handleSubmitButtonAction() throws Exception {
        controller.handleSubmitButtonAction(event);
        boolean b=false;
        if(controller.pinAuc.getChildren().isEmpty())
        {
            b=true;
        }
        assertEquals(false, b);

    }
    @Test
    public void testEmptyFields() throws Exception {
        controller.title.clear();
        controller.handleSubmitButtonAction(event);
        assertEquals("All fields are required!", (String) controller.message.getText());
    }

    @Test
    public void display() throws Exception
    {
        User.addUserClient("1","1","1","1","1");
        LoginController.email="1";
        Client.generatePublicAuc("Lictitatie","Concutructii","Descriere");
        controller.display();
        boolean b=false;
        if(controller.pinAuc.getChildren().isEmpty())
        {
            b=true;
        }
        assertEquals(false, b);
    }

}