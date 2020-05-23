package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenServiceController implements Initializable {
    @FXML
    public FontAwesomeIconView closeBtn;

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
    }
}
