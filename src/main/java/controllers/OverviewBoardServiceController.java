package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class OverviewBoardServiceController  implements Initializable {

    @FXML
    public Label profit;
    @FXML
    public Label progress;
    @FXML
    public Label winnings;
    @FXML
    public VBox pinAuc;


    @FXML
    public FontAwesomeIconView closeBtn2;
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
            array= Service.displayOverview();
            Iterator<JSONObject> iterator = array.iterator();
            while (iterator.hasNext())
            {
                JSONObject obj2 = iterator.next();
                //if the status of the obj is closed it will not be displayed

                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("OverviewService.fxml"));
                    Pane root = null;
                    root = loader.load();
                    OverviewServiceController auction = loader.getController();
                    auction.setParent(pinAuc);
                    String status="active";
                    if(obj2.containsKey("Status:"))
                    {
                        status="closed" ;
                        auction.setFields((String) obj2.get("Title:"), (String) obj2.get("Submited prices:")+ " RON", status);
                    }

                    if(obj2.containsKey("Client:"))
                    {
                        status="win" ;
                        auction.setFields((String) obj2.get("Title:"), (String) obj2.get("Price:")+ " RON", status);
                    }
                    else auction.setFields((String) obj2.get("Title:"), (String) obj2.get("Submited prices:")+ " RON", status);

                    pinAuc.getChildren().add(root);
                }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Service.overView(profit,progress, winnings);
        display();

    }
}
