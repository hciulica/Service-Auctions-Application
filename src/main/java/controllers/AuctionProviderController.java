package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class AuctionProviderController implements Initializable {

    @FXML
    public Label title;
    @FXML
    public Label activityDomain;
    @FXML
    public Label description;
    @FXML
    public JFXButton submitBtn;

    private VBox parent;

    @FXML
    private Pane dealRoot;

    JSONObject currentAuction;
    public void displayAuctions(String title,String activityDomain, String description)
    {
        this.title.setText(title);
        this.activityDomain.setText(activityDomain);
        this.description.setText(description);

    }
    void setParent(VBox parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
