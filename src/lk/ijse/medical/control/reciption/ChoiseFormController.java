package lk.ijse.medical.control.reciption;

import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideOutLeft;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ChoiseFormController {

    public ComboBox colBox;
    public AnchorPane pane;
    public Pane colPane;


    public void initialize() throws SQLException, ClassNotFoundException {
       setData();
       new SlideInLeft(colPane).play();
    }
    private void setData(){
         colBox.getItems().addAll("Reciption","Manager");
    }

    public void goOnAction(ActionEvent actionEvent) throws IOException {
        if(colBox.getValue()=="Reciption"){
            setUi("ReloginForm");
        }else if(colBox.getValue()=="Manager"){
            setUi("ManagerLoginForm");
        }
    }

    public void backOnAction(ActionEvent actionEvent) {
    }
    private void setUi(String ui) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/"+ui+".fxml"))));

    }
}
