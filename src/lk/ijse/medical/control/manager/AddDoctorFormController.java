package lk.ijse.medical.control.manager;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.medical.dto.DoctorDTO;

import lk.ijse.medical.entity.Doctor;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.DoctorServise;
import lk.ijse.medical.servise.custom.OrderServise;
import lk.ijse.medical.tm.DoctorTm;

import java.sql.SQLException;

public class AddDoctorFormController {

    public Label lblDoctor;
    public JFXTextField txtName;
    public JFXTextField txtType;
    public JFXTextField txtDays;
    public JFXTextField txtTime;
    public JFXTextField txtContact;
    public TableView tbl1;
    public TableColumn col_id;
    public TableColumn colName;
    public TableColumn colType;
    public TableColumn colays;
    public TableColumn colTime;
    public TableColumn colContact;

    private final DoctorServise doctorServise= (DoctorServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.DOCTOR);
    private final OrderServise orderServise= (OrderServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.ORDER);
    public void initialize(){
        loadNextOrderId();
        setDate();
        setAnimation();
        tbl1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setdata((DoctorTm) newValue);
        });
    }
    private  void setAnimation(){
        new ZoomIn(tbl1).play();
        new ZoomIn(lblDoctor).play();
        new ZoomIn(txtName).play();
        new ZoomIn(txtType).play();
        new ZoomIn(txtDays).play();
        new ZoomIn(txtTime).play();
        new ZoomIn(txtContact).play();
    }

    private void loadNextOrderId()  {
        String orderId =orderServise.generateNextOrderId();
        lblDoctor.setText(orderId);
    }
    private void setDate(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colays.setCellValueFactory(new PropertyValueFactory<>("alive"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }

    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = lblDoctor.getText();
        String name = txtName.getText();
        String type = txtType.getText();
        String day = txtDays.getText();
        String time=txtContact.getText();
        String Contact=txtTime.getText();


        DoctorDTO doctorDTO = new DoctorDTO(id, name, type, day, time,Contact);
        boolean isAdded = doctorServise.addDoctor(doctorDTO);
        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, "success..!!").show();
        }
        ObservableList<DoctorTm> tmList = FXCollections.observableArrayList();
        for (DoctorDTO a1 :doctorServise.getAllDoctors()) {
            DoctorTm tm = new DoctorTm(a1.getId(),a1.getName(),a1.getType(),a1.getAlive(),a1.getTime(),a1.getContact());
            tmList.add(tm);
        }
        tbl1.setItems(tmList);
        cleatText();
        new ZoomIn(tbl1).play();
        tbl1.refresh();
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = lblDoctor.getText();
        String name = txtName.getText();
        String type = txtType.getText();
        String day = txtDays.getText();
        String time=txtContact.getText();
        String Contact=txtTime.getText();

        DoctorDTO doctorDto = new DoctorDTO(id, name, type, day,time,Contact);
        boolean isUpdate = doctorServise.update(doctorDto);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Update  completed").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Update not Completed...").show();
        }

    }

    private void cleatText(){
        txtName.clear();
        txtTime.clear();
        txtContact.clear();
        txtDays.clear();
        txtType.clear();

    }
    private void setdata(DoctorTm tm) {
        lblDoctor.setText(tm.getId());
        txtName.setText(tm.getName());
        txtType.setText(tm.getType());
        txtDays.setText(tm.getAlive());
        txtTime.setText(tm.getTime());
        txtContact.setText(tm.getContact());
    }

    public void DeleteOnAction(ActionEvent actionEvent) {

        boolean isDeleted=doctorServise.deleteCustomer(lblDoctor.getText());
        if(isDeleted){
            new Alert(Alert.AlertType.INFORMATION, "Delete SuccesFully completed").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Delete Fail..").show();
        }
    }

    public void NameOnAction(ActionEvent actionEvent) {
        DoctorDTO doctorDTO = doctorServise.searchCustomer(lblDoctor.getText());
        if (doctorDTO == null) {
            new Alert(Alert.AlertType.INFORMATION, "DoctorDTO Not Found..").show();
        } else {
            txtName.setText(doctorDTO.getName());
            txtType.setText(doctorDTO.getType());
            txtDays.setText(doctorDTO.getAlive());
            txtTime.setText(doctorDTO.getTime());
            txtContact.setText(doctorDTO.getContact());
        }
    }
}
