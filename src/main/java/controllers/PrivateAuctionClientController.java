package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONObject;
import services.Client;

import java.net.URL;
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
    private VBox parent;
    @FXML
    private Pane dealRoot;
    @FXML
    public VBox invited;
    ObservableList<String> invitedField;

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
        Client.closePriv(currentAuction);
    }

    void setParent(VBox parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}


