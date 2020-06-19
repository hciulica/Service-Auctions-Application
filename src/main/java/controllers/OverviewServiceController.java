package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class OverviewServiceController implements Initializable {
    @FXML
    public Label title;
    @FXML
    public Label price;
    @FXML
    public Label status;
    @FXML
    public Pane dealRoot;
    private VBox parent;



    public void setFields(String title,String price, String status)
    {
        this.title.setText(title);
        this.price.setText(price);
        this.status.setText(status);
    }
    void setParent(VBox parent) {
        this.parent = parent;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
