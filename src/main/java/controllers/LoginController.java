package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable{


    @FXML
    public Text loginMessage;
    @FXML
    public JFXPasswordField passwordField;
    @FXML
    public JFXTextField usernameField;
    @FXML
    public JFXButton loginBtn;
    @FXML
    public Label signupBtn;
    @FXML
    public ImageView closeBtn;



    @FXML
    public void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();



    }

    @FXML
    public void handleMouseEvent(MouseEvent event) {
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