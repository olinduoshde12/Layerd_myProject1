package lk.ijse.medical.control.manager;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javassist.NotFoundException;
import lk.ijse.medical.entity.Employee;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.DoctorServise;
import lk.ijse.medical.servise.custom.EmployeeServise;
import lk.ijse.medical.tm.EmployeeTm;
import lk.ijse.medical.dto.EmployeeDTO;

import java.time.LocalDate;

public class AddEmployeeFormController {

    public TextField txtName;
    public TextField txtExp;
    public JFXDatePicker lblDate;
    public TextField txtContact;
    public TextField txtNix;
    public JFXComboBox lblType;
    public TableView tbl1;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colDob;
    public TableColumn colExp;
    public TableColumn colContact;
    public TableColumn colNic;
    public TableColumn colW_Type;
    public Label lblEmployyeId;
    public TextField lblEmployyeId1;

    private final EmployeeServise employeeServise= (EmployeeServise) ServiseFactory.
            getServiseFactory().getServise(ServiseTypes.EMPLOYEE);
    private final DoctorServise doctorServise= (DoctorServise) ServiseFactory.
            getServiseFactory().getServise(ServiseTypes.DOCTOR);

    public void initialize(){
        loadNextOrderId();
        setData();
        setAnimation();
        tbl1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setdata((EmployeeTm) newValue);
        });
    }
    private void setAnimation(){
        new ZoomIn(tbl1).play();
        new ZoomIn(lblEmployyeId1).play();
        new ZoomIn(txtName).play();
        new ZoomIn(txtContact).play();
        new ZoomIn(txtExp).play();
        new ZoomIn(txtNix).play();
        new ZoomIn(lblType).play();
        new ZoomIn(lblDate).play();


    }
    private void loadNextOrderId() {
        String orderId = employeeServise.genarateMedicineId();
        lblEmployyeId1.setText(orderId);
    }

    private void setData() {
        lblType.getItems().addAll("Helper","Nurse","Store keeper");
    }

    public void addOnAction(ActionEvent actionEvent)  {
        String id = lblEmployyeId1.getText();
        String name = txtName.getText();
        String exp = txtExp.getText();
        String date = String.valueOf(lblDate.getValue());
        String contact = txtContact.getText();
        String nic = txtNix.getText();
        String type = String.valueOf(lblType.getValue());

        EmployeeDTO employeeDTO = new EmployeeDTO(id, name, exp, date, contact, nic, type);
        boolean isAdded = employeeServise.addCustomer(employeeDTO);
        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, "success..!!").show();
        }
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();
        for (EmployeeDTO a1 : employeeServise.getAllEmployess()) {
            EmployeeTm tm = new EmployeeTm(a1.getId(),a1.getName(),a1.getDob(),a1.getExp(),a1.getContact(),a1.getNic(),a1.getType());
            tmList.add(tm);
        }
        tbl1.setItems(tmList);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colExp.setCellValueFactory(new PropertyValueFactory<>("exp"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colW_Type.setCellValueFactory(new PropertyValueFactory<>("type"));

    }
    private void setdata(EmployeeTm tm) {
        lblEmployyeId1.setText(tm.getId());
        txtName.setText(tm.getName());
        lblDate.setValue(LocalDate.parse(tm.getDob()));
        txtExp.setText(tm.getExp());
        txtContact.setText(tm.getContact());
        txtNix.setText(tm.getNic());
        lblType.setValue(tm.getType());
    }

    public void deleteOnAction(ActionEvent actionEvent)  {
        try {

            boolean isDeleted=employeeServise.deleteEmployee(lblEmployyeId1.getText());
            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Delete SuccesFully completed").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Delete Fail..").show();
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void viewOnAction(ActionEvent actionEvent) {
    }
}
