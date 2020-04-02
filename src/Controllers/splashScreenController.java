package Controllers;

import Main.Main;
import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class splashScreenController implements Initializable {

    public Label loading_lbl, loading_lbl2, logoView;
    public JFXProgressBar progress_bar;
    public AnchorPane rootPane;

    Main main;
    Stage stage;

    public void main(Main main) {
        this.main = main;
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition logoViewTransition = new FadeTransition(Duration.seconds(2), logoView);
        logoViewTransition.setToValue(1);
        logoViewTransition.setFromValue(0);
        logoViewTransition.play();

        TranslateTransition loadingLabelTransition = new TranslateTransition(Duration.seconds(1), loading_lbl);
        loadingLabelTransition.setByY(700);
        loadingLabelTransition.play();

        TranslateTransition loadingLabel2Transition = new TranslateTransition(Duration.seconds(1), loading_lbl2);
        loadingLabel2Transition.setByY(700);
        loadingLabel2Transition.play();

        loadingLabelTransition.setOnFinished(event -> {
            loading_lbl.setVisible(true);

            TranslateTransition loadingLabelTransition1 = new TranslateTransition(Duration.seconds(1), loading_lbl);
            loadingLabelTransition1.setByY(-700);
            loadingLabelTransition1.play();

            loadingLabelTransition1.setOnFinished(event1 -> {
                loading_lbl2.setVisible(true);

                TranslateTransition loadingLabel2Transition1 = new TranslateTransition(Duration.seconds(0.5), loading_lbl2);
                loadingLabel2Transition1.setByY(-700);
                loadingLabel2Transition1.play();

                loadingLabel2Transition1.setOnFinished(event2 -> {
                    progress_bar.setVisible(true);

                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), progress_bar);
                    fadeTransition.setFromValue(0);
                    fadeTransition.setToValue(3);
                    fadeTransition.play();

                    fadeTransition.setOnFinished(event4 -> {

                        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(2), rootPane);
                        fadeTransition1.setFromValue(1);
                        fadeTransition1.setToValue(0.1);
                        fadeTransition1.play();

                        fadeTransition1.setOnFinished(event5 -> {
                            main.closeStage();
                            main.mainWindow();
                        });
                    });
                });
            });
        });
    }

}
