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

public class HomeScreenServiceController implements Initializable {
    @FXML
    public FontAwesomeIconView closeBtn;
    @FXML
    public Label name;
    @FXML
    public JFXButton genAucBtn;
    @FXML
    public BorderPane mainPane;
    @FXML
    public Pane center;
    @FXML
    public JFXButton homeScreenBtn;
    @FXML
    public JFXButton genAucPrivBtn;
    @FXML
    public JFXButton overviewBtn;
    @FXML
    public void handleCloseButtonAction(MouseEvent event) {
        if (event.getSource() == closeBtn) {
            System.exit(0);
        }
    }

    @FXML
    public void handleGenAucButtonAction(ActionEvent event)
    {
        FxmlLoader object= new FxmlLoader();
        Pane view = object.getPage("GenerateAucScreenProvider");
        mainPane.setCenter(view);

    }
    @FXML
    public void handlePrivBtnAction(ActionEvent event){
        FxmlLoader object= new FxmlLoader();
        Pane view = object.getPage("GeneratePrivateAucProv");
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
    public void handleOverviewBtnAction(ActionEvent event)
    {
        FxmlLoader object= new FxmlLoader();
        Pane view = object.getPage("OverviewBoardService");
        mainPane.setCenter(view);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setText(User.name);
    }
}
