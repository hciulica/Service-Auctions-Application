package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class AuctionProviderControllerTest extends ApplicationTest {
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_DESCRIPTION = "testDescription";
    public static final VBox TEST_VBOX=new VBox();
    public static final String TEST_PRICE="234";
    public static final String TEST_ACTIVITY="Agricultura";
    public static final ActionEvent event= new ActionEvent();

    private AuctionProviderController controller;
    @Before
    public void setUp() throws Exception {
        controller = new AuctionProviderController();
        controller.title = new Label();
        controller.parent = new VBox();
        controller.activityDomain = new Label();
        controller.description = new Label();
        controller.message = new Label();
        controller.priceProv = new TextField();

        controller.priceProv.setText(TEST_PRICE);
    }

    @Test
    public void setParent()
    {
        controller.setParent(TEST_VBOX);
        assertEquals(TEST_VBOX,controller.parent);
    }
    @Test
    public void displayAuctions() {
        controller.displayAuctions(TEST_TITLE,TEST_ACTIVITY,TEST_DESCRIPTION);
        assertEquals(TEST_TITLE, controller.title.getText());
    }

    @Test
    public void handleSubmitButtonAction()
    {
        controller.handleSubmitButtonAction(event);
        assertEquals("Offer submitted!", controller.message.getText());
    }
}