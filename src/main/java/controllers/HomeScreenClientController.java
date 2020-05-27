package controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import registration.FxmlLoader;

import javafx.event.ActionEvent;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
