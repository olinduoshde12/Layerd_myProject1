package lk.ijse.medical.control.reciption;
import animatefx.animation.BounceInUp;
import animatefx.animation.Swing;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXProgressBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class OpenFormController {

    public Label Lbl2;
    public JFXProgressBar laoding;
    public Label lbl1;
    @FXML
        private AnchorPane pane;



        @FXML
        void StartOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/choiseForm.fxml"))));
        }
        public void initialize(){
            setAction();
        }

    private void setAction() {

            new ZoomIn(Lbl2).play();
           // new Swing(Lbl2).play();

    }


   /* private void setUi(String ui) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/ijse/medical/view/"+ui+".fxml"))));

    }*/

}


