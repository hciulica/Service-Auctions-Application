package controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import services.FxmlLoader;
import services.User;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenClientController implements Initializable {
    @FXML
    public FontAwesomeIconView closeBtn;
    @FXML
    public JFXButton homeScreenBtn;
    @FXML
    public JFXButton genAucBtn;
    @FXML
    public JFXButton genPrivAucBtn;
    @FXML
    public JFXButton ratingBtn;
    @FXML
    public JFXButton overviewBtn;
    @FXML
    public BorderPane mainPane;
    @FXML
    public Pane center;
    @FXML
    public Label client;


    @FXML
    public void handleCloseButtonAction(MouseEvent event) {
        if(event.getSource()==closeBtn)
        {
            System.exit(0);
        }
    }

    @FXML
    public void handleGenAucButtonAction(ActionEvent event)
    {
        FxmlLoader object= new FxmlLoader();
        Pane view = object.getPage("GenerateAucScreenClient");
        mainPane.setCenter(view);

    }

    @FXML
    public void handleHomeScreenBtnAction(ActionEvent event)
    {
        FxmlLoader object= new FxmlLoader();
        Pane view = center;
        mainPane.setCenter(view);

    }


    @FXML
    public void handleGenPrivAucButtonAction(ActionEvent event)
    {
        FxmlLoader object= new FxmlLoader();
        Pane view = object.getPage("GeneratePrivateAucClient");
        mainPane.setCenter(view);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        client.setText(User.name);
    }
}
