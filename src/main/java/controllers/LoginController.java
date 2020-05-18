package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
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
    public void handleLoginButtonAction(ActionEvent event) throws IOException, JSONException {

        /*JSONArray jrr = new JSONArray();
        JSONObject obj = new JSONObject();

        obj.put("Username", usernameField.getText());
        obj.put("Password",passwordField.getText());


        if(obj.equals(jrr.get(1))){
            JOptionPane.showMessageDialog(null,"Password matched");

        }else
            JOptionPane.showMessageDialog(null,"Incorrect User/Password!");
*/
        //switch to home screen
        Parent home_page_parent= FXMLLoader.load(getClass().getClassLoader().getResource("HomeScreenClient.fxml"));
        Scene home_page_scene= new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();





    }

    @FXML
    public void handleCloseButtonAction(MouseEvent event)
    {
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