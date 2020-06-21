package controllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class AuctionClientControllerTest extends ApplicationTest {
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_DESCRIPTION = "testDescription";
    public static final VBox TEST_VBOX=new VBox();
    public static final String TEST_ACTIVITY="Agricultura";

    private AuctionClientController controller;

    @Before
    public void setUp() throws Exception {
        controller= new AuctionClientController();
        controller.title=new Label();
        controller.parent=new VBox();
        controller.activityDomain=new Label();
        controller.description=new Label();
        controller.priceBtn= new ComboBox<>();


    }

    @Test
    public void setFields() {
        controller.setFields(TEST_TITLE,TEST_ACTIVITY,TEST_DESCRIPTION);
        assertEquals(TEST_TITLE, controller.title.getText());
    }
    @Test
    public void displayAuctions() {
        controller.displayAuctions(TEST_TITLE,TEST_ACTIVITY,TEST_DESCRIPTION);
        assertEquals(TEST_TITLE, controller.title.getText());
    }
    @Test
    public void setParent()
    {
        controller.setParent(TEST_VBOX);
        assertEquals(TEST_VBOX,controller.parent);
    }

    @Test
    public void getJsonObject()
    {   JSONObject object =new JSONObject();
        object.put("Nume",345);
        assertEquals("Nume - 345 RON",controller.getJsonObject(object));
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



}