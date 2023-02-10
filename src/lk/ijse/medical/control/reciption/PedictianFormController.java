package lk.ijse.medical.control.reciption;

import animatefx.animation.ZoomOutDown;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.DoctorServise;
import lk.ijse.medical.tm.DoctorTm;
import lk.ijse.medical.dto.DoctorDTO;

import java.sql.SQLException;

public class PedictianFormController {
    public AnchorPane pane1;
    public TableView tbl1;
    public TableColumn colName;
    public TableColumn colDate;
    public TableColumn colTime;

    private final DoctorServise doctorServise= (DoctorServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.DOCTOR);

    public void SearchOnAction(ActionEvent actionEvent) {
        ObservableList<DoctorTm> tmList = FXCollections.observableArrayList();
        for (DoctorDTO a1 : doctorServise.getData3()) {
            DoctorTm tm = new DoctorTm(a1.getId(),a1.getName(),a1.getType(),a1.getAlive(),a1.getTime(),a1.getContact());
            tmList.add(tm);

        }
        tbl1.setItems(tmList);
       // System.out.println(tmList);

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("alive"));
    }

    public void backOnAction(ActionEvent actionEvent) {
        new ZoomOutDown(pane1).play();
    }
}
