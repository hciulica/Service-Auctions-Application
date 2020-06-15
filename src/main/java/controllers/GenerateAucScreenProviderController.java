package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.Client;
import services.Service;
import services.User;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class GenerateAucScreenProviderController implements Initializable {
    @FXML
    public VBox pinAuc;
    @FXML
    public FontAwesomeIconView closeBtn2;
    @FXML
    //close window
    public void handleCloseButtonAction(MouseEvent event) {
        if(event.getSource()==closeBtn2)
        {
            System.exit(0);
        }
    }
    public void display()  {
        try {
            JSONArray array=new JSONArray();
            array= Service.displayProvAuc();
            Iterator<JSONObject> iterator = array.iterator();
            while (iterator.hasNext())
            {
                JSONObject obj2 = iterator.next();
                //if the status of the obj is closed it will not be displayed

                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AuctionProvider.fxml"));
                    Pane root = null;
                    root = loader.load();
                    AuctionProviderController auction = loader.getController();
                    auction.setParent(pinAuc);
                    //sets the reference of the current obj in json so we can change the status if closed
                    auction.currentAuction=obj2;
                    auction.displayAuctions((String) obj2.get("Title:"), (String) obj2.get("Activity Field:"), (String) obj2.get("Description:"));

                    pinAuc.getChildren().add(root);

            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle rb){
        display();
    }
}
