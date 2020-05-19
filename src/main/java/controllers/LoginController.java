package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    public FontAwesomeIconView closeBtn2;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;



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
    public void handleSignUpButtonAction(MouseEvent event) throws IOException
    {
        //Load Signup Client Screen
        FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("SignUpClient.fxml"));
        Parent root=loader.load();

        Scene scene = signupBtn.getScene();
        SignUpClientController scene2Controller = loader.getController();
        //make close button invisible
        scene2Controller.closeBtn.setVisible(false);
        //load signup screen where de login picture is
        root.translateXProperty().set(-1 * 489);

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        //incrementeaza coordonata X
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);

        });
        timeline.play();
        //make close button visibile again
        timeline.setOnFinished(e ->scene2Controller.closeBtn.setVisible(true));



    }


    @FXML
    public void handleCloseButtonAction(MouseEvent event)
    {
        if(event.getSource()==closeBtn2)
        {
            System.exit(0);
        }



    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}