package lk.ijse.medical.control.reciption;

import animatefx.animation.ZoomOutDown;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medical.entity.Doctor;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.DoctorServise;
import lk.ijse.medical.tm.DoctorTm;
import lk.ijse.medical.dto.DoctorDTO;

import java.sql.SQLException;

public class HaertDiseesFormController {
    public TableView tbl1;
    public TableColumn cblD_name;
    public TableColumn cblD_Time;
    public TableColumn cbl_date;
    public AnchorPane pane1;

    private final DoctorServise doctorServise= (DoctorServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.DOCTOR);
    public void CheckOnAction(ActionEvent actionEvent) {
        ObservableList<DoctorTm> tmList = FXCollections.observableArrayList();
        for (DoctorDTO a1 : doctorServise.getData()) {
            DoctorTm tm = new DoctorTm(a1.getId(),a1.getName(),a1.getType(),a1.getAlive(),a1.getTime(),a1.getContact());
            tmList.add(tm);

        }
        tbl1.setItems(tmList);
        System.out.println(tmList);

        cblD_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        cblD_Time.setCellValueFactory(new PropertyValueFactory<>("time"));
        cbl_date.setCellValueFactory(new PropertyValueFactory<>("alive"));

    }

    public void backOnAction(ActionEvent actionEvent) {
        //pane1.setVisible(false);
        new ZoomOutDown(pane1).play();
    }
}
