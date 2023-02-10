package lk.ijse.medical.control.manager;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;

public class ManagerLoginFormController {
    public AnchorPane pane1;
    public Pane pane23;
    public static String text;
    public JFXTextField txtUser;
    public AnchorPane pane2;
    public JFXPasswordField txtPassword1;

    public void initialize(){
        pane23.setVisible(false);
    }
    public void signOnAction(ActionEvent actionEvent) {
        pane23.setVisible(true);
        new SlideInLeft(pane23).play();
    }

    public void otOnAction(ActionEvent actionEvent) {

    }

    public void s1OnAction(ActionEvent actionEvent) throws IOException {
        text=txtUser.getText();
        int password= Integer.parseInt(txtPassword1.getText());

        if((text=="admin")||(password==12345)){
            setUi("ManagerDashBoardForm");
            String Tittle="SignIn SuccessFully...";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(Tittle);
            tray.setMessage(Tittle);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }else{
           String Tittle="SignIn Fail..";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(Tittle);
            tray.setMessage(Tittle);
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));
        }

    }

    public void backOnAction(ActionEvent actionEvent) {
    }
    private void setUi(String ui) throws IOException {
        Stage stage = (Stage) pane2.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/"+ui+".fxml"))));

    }
}
