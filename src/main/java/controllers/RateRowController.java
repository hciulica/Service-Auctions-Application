package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;


import java.net.URL;
import java.util.ResourceBundle;

public class RateRowController implements Initializable {
    @FXML
    public JFXButton btnRate;
    @FXML
    public Rating star;
    @FXML
    public Pane dealRoot;

    private VBox parent;
    public Rating star1;
    @FXML
    private Label nameRating;

    public Label text;

    public void setFields(String name,double rate)
    {
        btnRate.setText(name);
        star.setRating(rate);

    }
    public void setName(Label nameRating,Label text,Rating star1){
        this.nameRating = nameRating;
        this.text = text;
        this.star1 = star1;
    }
    public void handleBtnRateAction(ActionEvent event){

        nameRating.setText(btnRate.getText());
        text.setText("");
        star1.setRating(0.0);
    }
    void setParent(VBox parent) {
        this.parent = parent;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
