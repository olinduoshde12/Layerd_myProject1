package lk.ijse.medical.control.reciption;

import animatefx.animation.BounceIn;
import animatefx.animation.ZoomIn;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static lk.ijse.medical.control.reciption.ReloginFormController.username;


public class RedashboardFormController {
    public AnchorPane context1;
    public AnchorPane context3;
    public Label lbl1;
    public Label reId;
    public AnchorPane Apane7;
    public Label date1;
    private SimpleDateFormat timeFomat;

    public void initialize(){

        setTime();
        setName();
        new BounceIn(Apane7).play();
        timeFomat = new SimpleDateFormat("h:mm a");
        loadOrderDate();
    }

    private void setName() {
        reId.setText(username);
    }

    private void loadOrderDate() {
        date1.setText(String.valueOf(LocalDate.now()));

    }


    public void AppoinmentOnAction(ActionEvent actionEvent) throws IOException {
       Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/AppoinmentForm.fxml"));
       context3.getChildren().clear();
       context3.getChildren().add(parent);
       //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }
    private void setUi(String ui) throws IOException {
        Stage stage = (Stage) context1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/"+ui+".fxml"))));

    }
    private void setTime() {
        Timeline time1 = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    lbl1.setText(LocalDateTime.now().format(formatter));

                }), new KeyFrame(Duration.seconds(1)));
        time1.setCycleCount(Animation.INDEFINITE);
        time1.play();
    }

    public void PaymentOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/PaymentForm.fxml"));
        context3.getChildren().clear();
        context3.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }

    public void DashOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/SecondDashBoardForm.fxml"));
        context3.getChildren().clear();
        context3.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure do you won't Exit"
                , ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            setUi("OpenForm");
        } else {

        }
    }

    public void AvalibiltyOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/DoctorAvalibiltyForm.fxml"));
        context3.getChildren().clear();
        context3.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }

    public void placeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/PlaceAppoinmentForm.fxml"));
        context3.getChildren().clear();
        context3.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }

    public void empOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/EmployeeDetailCheckForm.fxml"));
        context3.getChildren().clear();
        context3.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }
}
