package controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import exception.EmailNotAvailable;
import exception.EmptySignUpFieldException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import registration.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpServiceController implements Initializable {
    @FXML
    public Label signUpMessage;
    @FXML
    public FontAwesomeIconView closeBtn2;
    @FXML
    private JFXButton signUpClientBtn;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private ComboBox <String> fieldBtn;
    @FXML
    public Label signinBtn;
    @FXML
    public TextField bussinesName;
    @FXML
    public TextField phoneNr;
    @FXML
    public TextField email;
    @FXML
    public PasswordField password;
    @FXML
    public JFXButton signUpBtn;


    ObservableList <String> activityField= FXCollections.
            observableArrayList ("Agricultura","Constructii", "Transport si Depozitare",
                    "Hoteluri si Restaurante", "Tranzactii Imobiliare", "Invatamant",
                    "Sanatate","Activitati Culturale", "Activitati Tehnice", "Alte");
    public void handleSignUpButtonAction(ActionEvent event) throws IOException
    {
            try
            {
                User.addUserProvider(bussinesName.getText(), (String)fieldBtn.getValue(), (String) phoneNr.getText(), email.getText(), password.getText());
                //Load the Home Page for Provider
                Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("HomeScreenService.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }
            catch (EmptySignUpFieldException e)
            {
                //error if not all fields are completed
                signUpMessage.setText(e.getMessage());
            }
            catch(EmailNotAvailable e)
            {
                signUpMessage.setText(e.getMessage());
            }




    }


    public void handleSignUpClientButtonAction(ActionEvent event) throws IOException
    {
        //Load Signup Client Screen
        FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("SignUpClient.fxml"));
        Parent root=loader.load();
        StackPane parentContainer = (StackPane) signUpClientBtn.getScene().getRoot();

        Scene scene = signUpClientBtn.getScene();
        SignUpClientController scene2Controller = loader.getController();
        //make close button invisible
        scene2Controller.closeBtn.setVisible(false);
        //load signup screen where de login picture is
        root.translateXProperty().set(-1 * 489);



        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(800), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
        //make close button visibile again
        timeline.setOnFinished(e ->scene2Controller.closeBtn.setVisible(true));

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
        if (event.getSource() == closeBtn2) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fieldBtn.setItems(activityField);

        //Change prompt Text Color
        fieldBtn.setEditable(false);
        fieldBtn.setButtonCell(new ListCell<String>()
        {

            @Override
            protected void updateItem(String item, boolean empty)
            {
                super.updateItem(item, empty);
                if(empty || item==null){
                    // styled like -fx-prompt-text-fill:
                    setStyle("-fx-text-fill: derive(-fx-control-inner-background,-30%)");
                } else {
                    setStyle("-fx-text-fill: -fx-text-inner-color");
                    setText(item.toString());
                }
            }

        });

    }
}