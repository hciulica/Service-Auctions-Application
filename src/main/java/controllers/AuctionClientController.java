package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import registration.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class AuctionClientController implements Initializable {
    @FXML
    public Label title;
    @FXML
    public Label activityDomain;
    @FXML
    public Label description;

    public void setFields(String title,String activityDomain, String description)
    {
        this.title.setText(title);
        this.activityDomain.setText(activityDomain);
        this.description.setText(description);
        Client.generatePublicAuc(title, activityDomain, description);
    }

    public void displayAuctions(String title,String activityDomain, String description)
    {
        this.title.setText(title);
        this.activityDomain.setText(activityDomain);
        this.description.setText(description);
    }





    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
