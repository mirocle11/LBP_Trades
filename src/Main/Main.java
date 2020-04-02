package Main;

import Controllers.dashboardController;
import Controllers.splashScreenController;
import DataBase.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public Stage stage, dashboard_stage;
    public Scene dashboardScene;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        splashScreenWindow();
    }

    private void splashScreenWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/splashScreen.fxml"));
            AnchorPane pane = loader.load();
            splashScreenController splashScreenController = loader.getController();
            splashScreenController.main(this);

            Scene scene = new Scene(pane);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Please wait..");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainWindow() {
        DataBase db = DataBase.getInstance();

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Views/dashboard.fxml"));
            AnchorPane pane = loader.load();

            dashboardScene = new Scene(pane);
            dashboardController controller = loader.getController();
            controller.setMain(dashboard_stage,this);
            dashboardScene.getStylesheets().addAll(Main.class.getResource("/Views/CSS/style.css").toExternalForm());
            dashboardScene.setFill(Color.TRANSPARENT);

            dashboard_stage = new Stage();
            dashboard_stage.initStyle(StageStyle.UNDECORATED);
            dashboard_stage.initStyle(StageStyle.TRANSPARENT);
            dashboard_stage.setScene(dashboardScene);
            dashboard_stage.show();
            dashboard_stage.setTitle("LBP Trades");
            dashboard_stage.setMinWidth(1250);
            dashboard_stage.setMinHeight(750);

            //draggable pop up
            pane.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            pane.setOnMouseDragged(event -> {
                dashboard_stage.setX(event.getScreenX() - xOffset);
                dashboard_stage.setY(event.getScreenY() - yOffset);
            });

            db.displayClients(dashboardController.clientsData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeStage() {
        stage.close();
    }

    public void closeDashboardStage() {
        dashboard_stage.close();
    }

    public void setDashboardMax() {
        if (dashboard_stage.isMaximized()) {
            dashboard_stage.setMaximized(false);
        } else {
            dashboard_stage.setMaximized(true);
        }
    }

    public void setDashboardMin() {
        dashboard_stage.setIconified(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
