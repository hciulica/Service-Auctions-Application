package controllers.registration;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import exception.EmailNotAvailable;
import exception.EmptySignUpFieldException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpClientController implements Initializable {
    @FXML
    public Label signUpMessage;
    @FXML
    public FontAwesomeIconView closeBtn;
    @FXML
    private JFXButton signUpProviderBtn;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    public Label signinBtn;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public TextField phoneNr;
    @FXML
    public TextField email;
    @FXML
    public PasswordField password;
    @FXML
    public JFXButton signUpBtn;




    public void handleSignUpProvButtonAction(ActionEvent event) throws IOException
    {
        //Load Signup Provider Screen
        FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("SignUpService.fxml"));
        Parent root=loader.load();

        Scene scene = signUpProviderBtn.getScene();
        root.translateXProperty().set(489);

        StackPane parentContainer = (StackPane) signUpProviderBtn.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(800), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    public void handleSignUpButtonAction(ActionEvent event) throws IOException
    {
        try {

            User.addUserClient(firstName.getText(), lastName.getText(),  (String) phoneNr.getText(),email.getText(), password.getText());
            //Load the Home Page for client
            Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("HomeScreenClient.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();
            LoginController.email=email.getText();
        }
        catch (EmptySignUpFieldException e)
        {   //error if not all fields are completed
            signUpMessage.setText(e.getMessage());
        }
        catch(EmailNotAvailable e)
        {
            signUpMessage.setText(e.getMessage());
        }

    }
    @FXML
    public void handleSignInButtonAction(MouseEvent event) throws IOException
    {
        FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
        Parent root=loader.load();
        StackPane parentContainer = (StackPane) signinBtn.getScene().getRoot();

        parentContainer.getChildren().add(root);


    }


    public void handleCloseButtonAction(MouseEvent event) {
        if (event.getSource() == closeBtn) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {




    }
}