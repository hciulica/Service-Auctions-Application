package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONObject;
import services.Service;

import java.net.URL;
import java.util.ResourceBundle;

public class AuctionProviderPrivController implements Initializable {

    @FXML
    public Label title;
    @FXML
    public Label activityDomain;
    @FXML
    public Label description;
    @FXML
    public JFXButton submitBtn;

    public VBox parent;

    @FXML
    private Pane dealRoot;
    @FXML
    public TextField priceProv;

    JSONObject currentAuction;
    @FXML
    public Label message;

    public void displayAuctions(String title, String description)
    {
        this.title.setText(title);
        this.description.setText(description);

    }
    void setParent(VBox parent) {
        this.parent = parent;
    }

    public void handleSubmitButtonAction(ActionEvent event){
        message.setText("Offer submitted!");
        Service.submitPricePrivate(priceProv.getText(),title.getText(),description.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
