package lk.ijse.medical.control.manager;

import animatefx.animation.ZoomIn;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManagerDashBoardFormController {
    public Label lbl1;
    public Label lbl2;
    public AnchorPane pane1;
    public AnchorPane pane3;

    public void initialize() {
        SimpleDateFormat timeFomat = new SimpleDateFormat("h:mm a");
        loadTime();
        loadOrderDate();
    }
    private void loadOrderDate() {
        lbl2.setText(String.valueOf(LocalDate.now()));

    }
    private void loadTime(){
        Timeline time1 = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    lbl1.setText(LocalDateTime.now().format(formatter));

                }), new KeyFrame(Duration.seconds(1)));
        time1.setCycleCount(Animation.INDEFINITE);
        time1.play();
    }

    public void MedicineOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/AddMedicineForm.fxml"));
        pane3.getChildren().clear();
        pane3.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }
    private void setUi(String ui) throws IOException {
        Stage stage = (Stage) pane1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/"+ui+".fxml"))));

    }

    public void empOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/AddEmployeeForm.fxml"));
        pane3.getChildren().clear();
        pane3.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }

    public void doctorOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/AddDoctorForm.fxml"));
        pane3.getChildren().clear();
        pane3.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
    setUi("OpenForm");

    }
}
