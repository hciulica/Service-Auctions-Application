package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.User;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class SignUpClientControllerTest extends ApplicationTest {
    public static final String TEST_FIRSTNAME = "testFirstName";
    public static final String TEST_LASTNAME = "testLastName";
    public static final String TEST_PHONE = "0745678907";
    public static final String TEST_EMAIL = "testEmail";
    public static final String TEST_PASSWORD = "testPassword";
    public static final ActionEvent event = new ActionEvent();


    private SignUpClientController controller;


    @Before
    public void setUp() throws Exception {

        controller = new SignUpClientController();
        controller.firstName = new TextField();
        controller.lastName = new TextField();
        controller.phoneNr = new TextField();
        controller.email = new TextField();
        controller.password = new PasswordField();
        controller.signUpMessage = new Label();

        controller.firstName.setText(TEST_FIRSTNAME);
        controller.lastName.setText(TEST_LASTNAME);
        controller.phoneNr.setText(TEST_PHONE);
        controller.email.setText(TEST_EMAIL);
        controller.password.setText(TEST_PASSWORD);


    }


    @AfterClass
    public static void afterClass() throws Exception {

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



    public JSONArray getJsonClient() throws Exception {
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        FileReader readFile = new FileReader("src/main/resources/usersClient.json");
        BufferedReader read = new BufferedReader(readFile);
        p = jp.parse(read);
        if (p instanceof JSONArray) {
            arrayClient = (JSONArray) p;
        }
        return arrayClient;

    }

    @Test
    public void handleSignUpButtonAction() throws Exception {
        controller.firstName.setText(TEST_FIRSTNAME);
        controller.handleSignUpButtonAction(event);
        assertEquals(1, getJsonClient().size());
    }

    @Test
    public void testEmailAreadyExists() throws Exception {
        controller.firstName.setText(TEST_FIRSTNAME);
        User.addUserClient(TEST_FIRSTNAME,TEST_LASTNAME,TEST_PHONE,TEST_EMAIL,TEST_PASSWORD);
        controller.handleSignUpButtonAction(event);
        assertEquals("This email address is not available.",(String) controller.signUpMessage.getText());
    }

    @Test
    public void testEmptyFields() throws Exception {
        controller.firstName.clear();
        controller.handleSignUpButtonAction(event);
        assertEquals("All fields are required!", (String) controller.signUpMessage.getText());
    }

}