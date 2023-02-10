package lk.ijse.medical.control.reciption;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.AppoinmentServise;

import java.sql.SQLException;

public class CheckAppoinmentDetailsFormControoller {

    public JFXTextField txtSaerch;
    public TableView tblserch;
    public TableColumn colP_name;
    public TableColumn colD_name;
    public TableColumn colType;
    public TableColumn colDate;
    public JFXTextField txtp_name;
    public JFXTextField txtdate;
    public JFXTextField txtTime;
    public JFXTextField txtType;
    public AnchorPane anpane8;
    public JFXTextField txtDoc;

    private final AppoinmentServise appoinmentServise= (AppoinmentServise) ServiseFactory.
            getServiseFactory().getServise(ServiseTypes.APPOINMENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        setvisi();
    }

    private void setvisi() {
        txtp_name.setVisible(false);
        txtdate.setVisible(false);
        txtTime.setVisible(false);
        txtType.setVisible(false);
        txtDoc.setVisible(false);


    }


    public void seOnAction(ActionEvent actionEvent, String id)  {
        Appoinment appoinmentDTO =appoinmentServise.searchCustomer(id);
        if(appoinmentDTO ==null){
            new Alert(Alert.AlertType.WARNING,"Appointment not Avalible..").show();
        }else{
            txtp_name.setText(appoinmentDTO.getPatientname());
            txtdate.setText(appoinmentDTO.getDate());
            txtTime.setText(appoinmentDTO.getTime());
            txtType.setText(appoinmentDTO.getType());
            txtDoc.setText(appoinmentDTO.getDoctorname());


            txtp_name.setVisible(true);
            txtdate.setVisible(true);
            txtTime.setVisible(true);
            txtType.setVisible(true);
            txtDoc.setVisible(true);


            new ZoomIn(txtp_name).play();
            new ZoomIn(txtdate).play();
            new ZoomIn(txtTime).play();
            new ZoomIn(txtType).play();
            new ZoomIn(txtDoc).play();

        }

    }

    public void bkOnAction(ActionEvent actionEvent) {
        anpane8.setVisible(false);
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }
}

