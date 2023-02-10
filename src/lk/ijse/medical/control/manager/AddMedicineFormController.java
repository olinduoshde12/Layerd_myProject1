package lk.ijse.medical.control.manager;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javassist.NotFoundException;
import lk.ijse.medical.dto.MedicineDTO;
import lk.ijse.medical.entity.Medicine;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.DoctorServise;
import lk.ijse.medical.servise.custom.EmployeeServise;
import lk.ijse.medical.servise.custom.MedicineServise;
import lk.ijse.medical.tm.MedicineTm;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AddMedicineFormController {


    public TableView tbl1;
    public TableColumn colId;
    public TableColumn colDescription;
    public TableColumn colType;
    public TableColumn colqty;
    public TableColumn colPrice;
    public Label lblId1;
    public TextField txtDescription;
    public JFXComboBox colType2;
    public TextField txtQty;
    public TextField txtPrice;
    public TableColumn colDelete;
    public ComboBox col_aid;
    public TableColumn colA_id;
    public TextField lblId2;
    public AnchorPane Pane2;
    public Label lblId;

    private final MedicineServise medicineServise= (MedicineServise) ServiseFactory.
            getServiseFactory().getServise(ServiseTypes.MEDICINE);

    private final DoctorServise doctorServise= (DoctorServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.DOCTOR);

    public void initialize(){
        loadNextOrderId();
        loadTypes();
        setAnimation();
        tbl1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setdata((MedicineTm) newValue);
        });


    }
    private void setAnimation(){
        new ZoomIn(tbl1).play();
        new ZoomIn(lblId2).play();
        new ZoomIn(txtDescription).play();
        new ZoomIn(colType2).play();
        new ZoomIn(txtQty).play();
        new ZoomIn(txtPrice).play();

    }
    private void loadNextOrderId() {
        String orderId = medicineServise.genarateMedicineId();
        lblId2.setText(orderId);
    }

    private void loadTypes()  {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = doctorServise.loadAppoinmentTypes();

        for (String type : idList) {
            observableList.add(type);
        }
        colType2.setItems(observableList);
    }


    public void addOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = lblId2.getText();
        String description = txtDescription.getText();
        String type = String.valueOf(colType2.getValue());
        int qty= Integer.parseInt(txtQty.getText());
        double price= Double.parseDouble(txtPrice.getText());


        MedicineDTO medicineDTO = new MedicineDTO(id,description,type,qty,price);
        boolean isAdded = medicineServise.addmedicine(medicineDTO);
        if (isAdded) {
            String Tittle="Added SuccessFully...";
            TrayNotification tray = new TrayNotification();
            AnimationType Type = AnimationType.POPUP;

            tray.setAnimationType(Type);
            tray.setTitle(Tittle);
            tray.setMessage(Tittle);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }

        ObservableList<MedicineTm> tmList = FXCollections.observableArrayList();
        for (MedicineDTO a1 : medicineServise.getAllMedicine()) {
            Button btn=new Button("Delete");
            Button btn1=new Button("Update");
            MedicineTm tm = new MedicineTm(a1.getId(),a1.getType(),a1.getDescription(),a1.getQty(),a1.getPrice(),btn);
            tmList.add(tm);

            btn.setOnAction((e) -> {
                ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(no) == ok) {
                    MedicineTm  medicineTm = (MedicineTm) tbl1.getSelectionModel().getSelectedItem();


                    tbl1.getItems().removeAll(tbl1.getSelectionModel().getSelectedItem());
                }
            });
        }
        tbl1.setItems(tmList);
        new ZoomIn(tbl1).play();


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));


    }

    public void updateOnAction(ActionEvent actionEvent) {

    }
    private void setdata(MedicineTm tm) {
        lblId2.setText(tm.getId());
        txtDescription.setText(tm.getDescription());
        colType2.setValue(tm.getType());
        txtQty.setText(String.valueOf(tm.getQty()));
        txtPrice.setText(String.valueOf(tm.getPrice()));

    }
    public void DeleteOnAction(ActionEvent actionEvent) {
        try {

            boolean isDeleted=medicineServise.deleteMedicine(lblId2.getText());
            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Delete SuccesFully completed").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Delete Fail..").show();
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}

