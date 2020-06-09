package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONObject;
import services.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class AuctionClientController implements Initializable {
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

    JSONObject currentAuction;

    public void setFields(String title,String activityDomain, String description)
    {
        this.title.setText(title);
        this.activityDomain.setText(activityDomain);
        this.description.setText(description);
        Client.generatePublicAuc(title, activityDomain, description);
        //takes the reference to the current obj
        currentAuction= Client.auc;
    }

    public void displayAuctions(String title,String activityDomain, String description)
    {
        this.title.setText(title);
        this.activityDomain.setText(activityDomain);
        this.description.setText(description);

    }
    @FXML
    //remove the closed auction from the screen(remove from vbox and set status to closed)
    public void handleCloseDealBtnClick() {
        parent.getChildren().remove(dealRoot);
        //calls the close method from client
        Client.close(currentAuction);
    }

    void setParent(VBox parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
