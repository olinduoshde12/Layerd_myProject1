package lk.ijse.medical.control.reciption;

import animatefx.animation.Shake;
import animatefx.animation.SlideInLeft;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ReloginFormController {
    public AnchorPane pane1;
    public AnchorPane pane2;
    public AnchorPane context;
    public   TextField txtUser;
    public PasswordField txtPassword;
    public JFXTextField txtuserName;
    public Label lblwarning;
    private Pattern userNamePattern;
    private Pattern pwPattern;
    public static String username;


    public void initialize(){
        setvisible();
        userNamePattern = Pattern.compile("^[a-z0-9]{4,}$");
        pwPattern = Pattern.compile("^[a-zA-Z0-9_]{5,}$");

        lblwarning.setVisible(false);

    }

    private void setvisible() {
        pane1.setVisible(false);
    }

    public void signOnAction(ActionEvent actionEvent) {

        pane1.setVisible(true);
        //new ZoomIn(pane1).play();
        new SlideInLeft(pane1).play();
    }

    public void logOnAction(ActionEvent actionEvent) throws IOException {
       boolean isusernamematched= userNamePattern.matcher(txtuserName.getText()).matches();
       boolean ispasswordmatched=pwPattern.matcher(txtPassword.getText()).matches();
        if(isusernamematched){
            if(ispasswordmatched){
                    detail();
            }else{
                new Shake(txtPassword).play();
                lblwarning.setVisible(true);
                txtPassword.requestFocus();
            }
        }else{
            new Shake(txtuserName).play();
            txtuserName.setFocusColor(Paint.valueOf("Red"));
            txtuserName.requestFocus();
        }

    }
    private void detail() throws IOException {
        username=txtuserName.getText();
        int password= Integer.parseInt(txtPassword.getText());

        if((username=="admin")||(password==12345)){
            setUi("RedashboardForm");
            String Tittle="SignIn SuccessFully...";
            String me=txtuserName.getText();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(Tittle);
            tray.setMessage(Tittle);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }else{
            String Tittle="SignIn Fail...";
            String me=txtuserName.getText();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(Tittle);
            tray.setMessage(Tittle);
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(3000));

        }
    }

    public void cancelOnAction(ActionEvent actionEvent) {
        pane1.setVisible(false);

    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("OpenForm");
    }
    private void setUi(String ui) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/"+ui+".fxml"))));

    }
}
