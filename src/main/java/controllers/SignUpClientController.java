package controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpClientController implements Initializable {
    @FXML
    public FontAwesomeIconView closeBtn;
    @FXML
    private JFXButton signUpProviderBtn;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    public Label signinBtn;

    public void handleSignUpButtonAction(ActionEvent event) throws IOException
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
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }
    @FXML
    public void handleSignInButtonAction(MouseEvent event) throws IOException
    {
        FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
        Parent root=loader.load();

        Scene scene = signinBtn.getScene();


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