package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.Client;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class PrivateAuctionClientController implements Initializable {

    @FXML
    public Label title;
    @FXML
    public Label activityDomain;
    @FXML
    public Label description;
    @FXML
    public JFXButton closeAucBtn;
    public VBox parent;
    @FXML
    private Pane dealRoot;
    @FXML
    public VBox invited;
    ObservableList<String> invitedField;
    @FXML
    public ComboBox<String> priceBtn;
    List<String> listPrice= new ArrayList<String>();

    JSONObject currentAuction;
    public void setFields(String title, List <String> invitedBusiness, String description)
    {
        this.title.setText(title);

        for(int i=0;i<invitedBusiness.size();i++)
        {
            Label business = new Label();
            business.setText(invitedBusiness.get(i));
            invited.getChildren().add(business);
        }
        this.description.setText(description);
        Client.generatePrivAuc(title, invitedBusiness, description);
        //takes the reference to the current obj
        currentAuction= Client.auc;
    }

    public void displayAuctions(String title, List <String> invitedBusiness, String description)
    {
        this.title.setText(title);

        for(int i=0;i<invitedBusiness.size();i++)
        {
            Label business = new Label();
            business.setText(invitedBusiness.get(i));
            invited.getChildren().add(business);
        }
        this.description.setText(description);

    }

    //remove the closed auction from the screen(remove from vbox and set status to closed)
    public void handleCloseDealBtnClick() {
        parent.getChildren().remove(dealRoot);
        //calls the close method from client
        Client.closePriv(currentAuction,(String)priceBtn.getValue());

    }

    void setParent(VBox parent) {
        this.parent = parent;
    }
    //returns the JSONObject as a string (key+Values)
    public static String getJsonObject(JSONObject jsonObj) {
        String s=new String ();
        for (Object key : jsonObj.keySet()) {
            String keyStr = (String) key;
            Object keyvalue = jsonObj.get(keyStr);

            if (!(keyvalue instanceof JSONObject)) {
                s=(keyStr + " - " + keyvalue + " RON");
            }
            if (keyvalue instanceof JSONObject) {
                getJsonObject((JSONObject) keyvalue);
            }
        }
        return s;
    }

    //display all the submitted prices in the combobox
    void displayPrice(JSONObject price)
    {

        JSONObject obj3 = new JSONObject();
        JSONArray prices= (JSONArray) price.get("Submited prices:");
        Iterator<JSONObject> iterator = prices.iterator();
        while (iterator.hasNext())
        {

            obj3=iterator.next();
            listPrice.add(getJsonObject(obj3));
            priceBtn.setItems(FXCollections.observableArrayList(listPrice));


        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}


