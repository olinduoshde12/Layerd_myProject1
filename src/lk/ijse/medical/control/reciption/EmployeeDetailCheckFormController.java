package lk.ijse.medical.control.reciption;

import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOutDown;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medical.dto.EmployeeDTO;
import lk.ijse.medical.entity.Employee;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.DoctorServise;
import lk.ijse.medical.servise.custom.EmployeeServise;

import java.sql.SQLException;

public class EmployeeDetailCheckFormController {

    public TextField txtId;
    public JFXTextField txtContact;
    public JFXTextField txtType;
    public JFXTextField txtExp;
    public JFXTextField txtName;
    public AnchorPane pane1;
    private final EmployeeServise employeeServise= (EmployeeServise) ServiseFactory.
            getServiseFactory().getServise(ServiseTypes.EMPLOYEE);

    public void initialize(){
        setAnimation();
        pane1.setVisible(true);

    }
    private void setAnimation(){
        new ZoomIn(txtId).play();
        new ZoomIn(txtContact).play();
        new ZoomIn(txtType).play();
        new ZoomIn(txtExp).play();
        new ZoomIn(txtName).play();
    }

    public void backOnAction(ActionEvent actionEvent) {
        new ZoomOutDown(txtContact).play();
        new ZoomOutDown(txtType).play();
        new ZoomOutDown(txtExp).play();
        new ZoomOutDown(txtName).play();

        txtContact.setVisible(true);
        txtType.setVisible(true);
        txtExp.setVisible(true);
        txtName.setVisible(true);
    }

    public void EmpOnAction(ActionEvent actionEvent) {
        try {
            Employee employeeDTO = employeeServise.searchEmployee(txtId.getText());
            if (employeeDTO == null) {
                new Alert(Alert.AlertType.ERROR, "EmployeeDTO Not Found..").show();
            } else {
                txtName.setText(employeeDTO.getName());
                txtType.setText(employeeDTO.getType());
                txtExp.setText(employeeDTO.getDob());
                txtContact.setText(employeeDTO.getContact());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
