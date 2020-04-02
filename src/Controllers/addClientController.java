package Controllers;

import DataBase.DataBase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class addClientController implements Initializable {

    public JFXButton CANCEL, SAVE;
    public JFXTextField FULL_NAME, ADDRESS;
    public JFXDatePicker DATE_REGISTERED;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CANCEL.setOnAction(event -> {
            dashboardController.closeAddClient();
        });

        SAVE.setOnAction(event -> {
            DataBase db = DataBase.getInstance();
            db.addClient(FULL_NAME.getText(), ADDRESS.getText(), DATE_REGISTERED.getValue().toString());
            dashboardController.closeAddClient();
            dashboardController.clientsData.clear();
            db.displayClients(dashboardController.clientsData);
        });
    }

}
