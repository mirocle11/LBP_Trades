package Controllers;

import Main.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import Models.clientsData;

import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {

    private ObservableList<String> months = FXCollections.observableArrayList( "January", "February", "March",
            "April", "May", "June", "July", "August", "September", "October", "November", "December");

    public JFXButton CLOSE, MAX, MIN, ADD_CLIENT;
    public JFXTreeTableView SHORT_TERM_TBL, MEDIUM_TERM_TBL, LONG_TERM_TBL, _8K_MODULE_TBL;
    public JFXComboBox<String> MONTH_SHORT, MONTH_MEDIUM, MONTH_LONG, MONTH_8K;

    //add client form
    private static Stage addClientStage;
    private double xOffset = 0;
    private double yOffset = 0;

    //clients data tbl
    public JFXTreeTableView<clientsData> CLIENTS_TBL;
    public TreeTableColumn<clientsData, String> COL_ID;
    public TreeTableColumn<clientsData, String> COL_FULL_NAME;
    public static ObservableList<clientsData> clientsData;

    private Main main;
    private Stage stage;

    public void setMain(Stage stage, Main main) {
        this.main = main;
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MONTH_SHORT.setItems(months);
        MONTH_MEDIUM.setItems(months);
        MONTH_LONG.setItems(months);
        MONTH_8K.setItems(months);

        ADD_CLIENT.setOnAction(event -> showAddClient());

        //clients data & table
        clientsData = FXCollections.observableArrayList();

        COL_ID.setCellValueFactory(
                new TreeItemPropertyValueFactory<>("id")
        );
        COL_FULL_NAME.setCellValueFactory(
                new TreeItemPropertyValueFactory<>("full_name")
        );

        TreeItem<clientsData> root = new RecursiveTreeItem<>(clientsData, RecursiveTreeObject::getChildren);
        CLIENTS_TBL.setRoot(root);
        CLIENTS_TBL.setShowRoot(false);
    }

    //undecorated nav functions
    public void setClose() {
        main.closeDashboardStage();
    }

    public void setMin() {
        main.setDashboardMin();
    }

    public void setMax() {
        main.setDashboardMax();
    }

    public void showAddClient() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Views/addClient.fxml"));
            AnchorPane pane = loader.load();

            //draggable pop up
            pane.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            pane.setOnMouseDragged(event -> {
                addClientStage.setX(event.getScreenX() - xOffset);
                addClientStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(pane);
            scene.setFill(Color.TRANSPARENT);
            scene.getStylesheets().addAll(Main.class.getResource("/Views/CSS/style.css").toExternalForm());
            addClientStage = new Stage();
            addClientStage.setScene(scene);
            addClientStage.initStyle(StageStyle.UNDECORATED);
            addClientStage.initModality(Modality.APPLICATION_MODAL);
            addClientStage.initStyle(StageStyle.TRANSPARENT);
            addClientStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeAddClient() {
        addClientStage.close();
    }

}
