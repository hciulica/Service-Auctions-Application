package controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import exception.BusinessNotExistException;
import exception.EmptyFieldException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.Client;
import services.Service;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class GeneratePrivateAucClientController implements Initializable {
    @FXML
    public FontAwesomeIconView closeBtn2;
    @FXML
    public VBox pinAuc;
    @FXML
    public VBox invited;
    @FXML
    public JFXButton submitBtn;
    @FXML
    public TextField title;
    @FXML
    public TextArea description;
    @FXML
    public TextField search;
    @FXML
    public JFXButton inviteBtn;
    @FXML
    public Label error;
    @FXML
    public Label message;
    public List<String> invitedBusiness=new ArrayList<>();

    @FXML
    public void handleInviteButtonAction(ActionEvent event)
    {
        try {
            Label business = new Label();
            String s;
            if(!Service.getServiceNames().contains(search.getText()))
            {
                throw new BusinessNotExistException();
            }
            business.setText(search.getText());
            invitedBusiness.add(search.getText());
            invited.getChildren().add(business);
        }
        catch(BusinessNotExistException e)
        {
            error.setText(e.getMessage());
        }

    }

    @FXML
    //close window
    public void handleCloseButtonAction(MouseEvent event) {
        if(event.getSource()==closeBtn2)
        {
            System.exit(0);
        }
    }

    public void handleSubmitButtonAction(ActionEvent event)
    {

        try {
            if(title.getText().isEmpty()|invitedBusiness.isEmpty()| description.getText().isEmpty()) {
                throw new EmptyFieldException();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PrivateAuctionClient.fxml"));
            Pane root = null;
            root = loader.load();
            // Get the controller instance:
            PrivateAuctionClientController auction = loader.getController();
            // Set a reference for the parent vbox:
            auction.setParent(pinAuc);
            auction.setFields(title.getText(),invitedBusiness, description.getText());

            //clear the vbox with the invited businesses
            invited.getChildren().clear();
            invitedBusiness=null;title.clear();
            description.clear();
            search.clear();
            pinAuc.getChildren().add(root);



        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        catch(EmptyFieldException e)
        {
            message.setText(e.getMessage());
        }

    }

    public void display()  {
        try {
            JSONArray array=new JSONArray();
            array= Client.displayPrivAuctions();
            Iterator<JSONObject> iterator = array.iterator();
            while (iterator.hasNext())
            {
                JSONObject obj2 = iterator.next();
                //if the status of the obj is closed it will not be displayed
                if(!obj2.containsValue("closed")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PrivateAuctionClient.fxml"));
                    Pane root = null;
                    root = loader.load();
                    PrivateAuctionClientController auction = loader.getController();
                    auction.setParent(pinAuc);
                    //sets the reference of the current obj in json so we can change the status if closed
                    auction.currentAuction=obj2;
                    auction.displayPrice(obj2);
                    List<String> list = (List<String>) obj2.get("Invited Businesses:");
                    auction.displayAuctions((String) obj2.get("Title:"),list, (String) obj2.get("Description:"));
                    pinAuc.getChildren().add(root);
                }
            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Autocomplete for the search TextField with existing business Names
        TextFields.bindAutoCompletion(search, Service.getServiceNames());
        display();


    }
}
