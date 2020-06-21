package controllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrivateAuctionClientControllerTest extends ApplicationTest {
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_DESCRIPTION = "testDescription";
    public static final VBox TEST_VBOX=new VBox();

    private PrivateAuctionClientController controller;

    @Before
    public void setUp() throws Exception {
        controller= new PrivateAuctionClientController();
        controller.title=new Label();
        controller.parent=new VBox();
        controller.invited=new VBox();
        controller.description=new Label();
        controller.priceBtn= new ComboBox<>();
    }
    @Test
    public void setParent()
    {
        controller.setParent(TEST_VBOX);
        assertEquals(TEST_VBOX,controller.parent);
    }

    @Test
    public void setFields() {
        List<String> invitedBusiness= new ArrayList<>();
        invitedBusiness.add("Nume");
        controller.setFields(TEST_TITLE,invitedBusiness,TEST_DESCRIPTION);
        assertEquals(TEST_TITLE, controller.title.getText());
    }

    @Test
    public void displayAuctions() {
        List<String> invitedBusiness= new ArrayList<>();
        invitedBusiness.add("Nume");
        controller.displayAuctions(TEST_TITLE,invitedBusiness,TEST_DESCRIPTION);
        assertEquals(TEST_TITLE, controller.title.getText());
    }

    @Test
    public void displayPrice()
    {
        JSONObject object1 = new JSONObject();
        JSONArray prices= new JSONArray();
        JSONObject object = new JSONObject();
        object. put("Nume",345);
        prices.add(object);
        object1.put("Submited prices:",prices);
        controller.displayPrice(object1);
        boolean b=false;
        if(controller.priceBtn.getItems().isEmpty())
        {
            b=true;

        }
        assertEquals(false,b);

    }

    @Test
    public void getJsonObject()
    {   JSONObject object =new JSONObject();
        object.put("Nume",345);
        assertEquals("Nume - 345 RON",controller.getJsonObject(object));
    }
}