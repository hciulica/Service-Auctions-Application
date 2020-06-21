package controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import exception.EmptyRateException;
import exception.NoProvSelectedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.Client;


import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class RatingController implements Initializable {
    @FXML
    public FontAwesomeIconView closeBtn2;
    @FXML
    public VBox pinAuc;
    @FXML
    public JFXButton submitBtn;
    @FXML
    public Label nameRating;
    @FXML
    public Label text;
    @FXML
    public Rating stars;

    public void display() {

        try {
            JSONArray jar1 = Client.compare();
            Iterator <JSONObject> it = jar1.iterator();
            if(!jar1.isEmpty()) {
                while (it.hasNext()) {
                    JSONObject obj2 = it.next();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("RateRow.fxml"));
                    Pane root = null;
                    root = loader.load();
                    RateRowController rating = loader.getController();
                    rating.setParent(pinAuc);
                    rating.setName(nameRating, text, stars);

                    rating.setFields((String) obj2.get("Name:"), (double) obj2.get("Rate:"));

                    pinAuc.getChildren().add(root);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //close window
    public void handleCloseButtonAction(MouseEvent event) {
        if(event.getSource()==closeBtn2)
        {
            System.exit(0);
        }
    }
    public void handleSubmitBtnAction(ActionEvent event) {

        try {
            if (nameRating.getText().equals("Select a Business")) throw new NoProvSelectedException();
            if (stars.getRating() == 0.0) {

                throw new EmptyRateException();
            }
            text.setStyle("-fx-text-fill:#000000");
            Client.submitRate(stars.getRating(), nameRating.getText());
            text.setText("Rating submitted!");

            stars.setRating(0.0);
        } catch (EmptyRateException e) {
            text.setStyle("-fx-text-fill:#FF0000");
            text.setText(e.getMessage());
        } catch (NoProvSelectedException e) {
            text.setStyle("-fx-text-fill:#FF0000");

            text.setText(e.getMessage());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        display();
    }
}
