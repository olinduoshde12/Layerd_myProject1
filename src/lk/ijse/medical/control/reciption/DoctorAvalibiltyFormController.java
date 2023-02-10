package lk.ijse.medical.control.reciption;

import animatefx.animation.ZoomIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DoctorAvalibiltyFormController {
    public AnchorPane contxt1;

    public void HeartOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/HaertDiseesForm.fxml"));
        contxt1.getChildren().clear();
        contxt1.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }

    public void PsyOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/psychistForm.fxml"));
        contxt1.getChildren().clear();
        contxt1.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }

    public void pediOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/PedictianForm.fxml"));
        contxt1.getChildren().clear();
        contxt1.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }

    public void DemoOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/DemotologistForm.fxml"));
        contxt1.getChildren().clear();
        contxt1.getChildren().add(parent);
        //new FadeIn(parent).play();
        new ZoomIn(parent).play();
    }
}
