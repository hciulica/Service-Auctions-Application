package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import services.User;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenServiceController implements Initializable {
    @FXML
    public FontAwesomeIconView closeBtn;
    @FXML
    public Label name;

    @FXML
    public void handleCloseButtonAction(MouseEvent event) {
        if(event.getSource()==closeBtn)
        {
            System.exit(0);
        }



    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setText(User.name);
    }
}
