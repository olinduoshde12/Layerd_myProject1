package lk.ijse.medical.control.manager;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.AppoinmentServise;
import lk.ijse.medical.servise.custom.DoctorServise;
import lk.ijse.medical.tm.AppoinmentTm;
import lk.ijse.medical.dto.AppoinmentDTO;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class AppoinmentFormController {
    public TextField TxtId;
    public JFXDatePicker lblDate;
    public JFXTimePicker lblTime;
    public JFXComboBox lblType;
    public TextField txtP_name;
    public TextField txtAddress;
    public TextField txtTel;
    public JFXComboBox lblD_name;
    public TextField txtPrice;
    public TableView tbl1;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colP_name;
    public TableColumn colAddress;
    public TableColumn colTel;
    public TableColumn colD_name;
    public TableColumn colPrice;
    public TableColumn colOption;
    public JFXButton apId;
    public TableColumn colUpdate;
    public Label lblAppoinment;
    public Pane pane6;
    public Label lblId;
    public Label lblDate1;
    public Label lblTime1;
    public Label lblTimer;
    public Label lblP_name;
    public Label lblAddress;
    public Label lblInput;
    public Label lblPrice;
    public Label lblPateint;
    private SimpleDateFormat timeFomat;
    private Pattern patienNamePattern;
    private Pattern addressPattern;
    private  Pattern tp_noPattern;
    private Pattern pricePattern;

    private final AppoinmentServise appoinmentServise= (AppoinmentServise) ServiseFactory.
            getServiseFactory().getServise(ServiseTypes.APPOINMENT);
    private final DoctorServise doctorServise= (DoctorServise) ServiseFactory.
            getServiseFactory().getServise(ServiseTypes.DOCTOR);


    public void initialize(){
        loadcounts();
        hide();
        regex();
        setDate();
        setData();
        ClaerField();
        loadTypes();
        loadNextOrderId();
        loadOrderDate();
        setAnimation();
        timeFomat = new SimpleDateFormat("h:mm a");
        loadTime();

        lblType.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue) ->{
            if(newValue!=null){
                try {
                    serccAppoinment();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void regex(){
      //  patienNamePattern = Pattern.compile("^[A-Za-z]+");
        addressPattern = Pattern.compile("^[A-Za-z]+");
        tp_noPattern=Pattern.compile("^(075|077|076|078)([0-9]{7})$");
        //pricePattern=Pattern.compile("^[((0-9)|(.))]");
    }

    private void serccAppoinment() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = doctorServise.loadD_names();
        for (String d_name : idList) {
            observableList.add(d_name);
        }

        lblD_name.setItems(observableList);

        }
    private void loadOrderDate() {
        lblDate1.setText(String.valueOf(LocalDate.now()));

    }
    private void loadTime(){
        Timeline time1 = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
                    lblTimer.setText(LocalDateTime.now().format(formatter));

                }), new KeyFrame(Duration.seconds(1)));
       time1.setCycleCount(Animation.INDEFINITE);
        time1.play();
    }


    private void ClaerField() {
       // TxtId.clear();
        txtAddress.clear();;
        txtP_name.clear();
        txtTel.clear();

        //new Swing(pane6).play();
        new Pulse(pane6).play();
    }
    private void setAnimation(){
        new ZoomIn(lblId).play();
        new ZoomIn(lblType).play();
        new ZoomIn(lblDate1).play();
        //new ZoomIn(lblP_name).play();
        new ZoomIn(txtAddress).play();
        new ZoomIn(txtTel).play();
        new ZoomIn(lblD_name).play();
        new ZoomIn(txtPrice).play();
        new ZoomIn(tbl1).play();

    }
    private void setData() {
       // lblType.getItems().addAll("Heart disses","Pediatrician","Dematologist","Psychatrist","Anesthaesiologist");
       // lblD_name.getItems().addAll("Dr.kamal(Haert disses)","Dr.Rajendra(Psychatrist)",
              //  "Dr.Amal Fernando(Pediatrician)");
    }
    private void hide(){
          lblPateint.setVisible(false);
        lblAddress.setVisible(false);
        lblInput.setVisible(false);
        lblPrice.setVisible(false);
    }

    private void loadTypes() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = doctorServise.loadAppoinmentTypes();

        for (String type : idList) {
            observableList.add(type);
        }
        lblType.setItems(observableList);
    }
    private void loadcounts()  {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<Integer> idList = appoinmentServise.countorder();

        for (Integer type : idList) {
            observableList.add(String.valueOf(type));
        }
        // System.out.println(observableList);
        lblType.setItems(observableList);
    }

    private void loadNextOrderId() {
        String orderId = appoinmentServise.generateNextAppoinmentId();
        lblId.setText(orderId);
    }



    public void AppoinmentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //boolean ispatienNameMatched = patienNamePattern.matcher(txtP_name.getText()).matches();
        boolean isaddressmatched = addressPattern.matcher(txtAddress.getText()).matches();
        boolean istelnomatched = tp_noPattern.matcher(txtTel.getText()).matches();
        // boolean ispriceMatched = pricePattern.matcher(txtPrice.getText()).matches();

          if(isaddressmatched){
              if(istelnomatched){
                  setAll();
              }else{
                  new Shake(txtTel).play();
                  lblInput.setVisible(true);
                  txtTel.requestFocus();
              }
          }else{
              new Shake(txtAddress).play();
              lblAddress.setVisible(true);
              txtAddress.requestFocus();
          }


    }



    private void setAll()  {
        String id = lblId.getText();
        String type = String.valueOf(lblType.getValue());
        String date = lblDate1.getText();
        String time = lblTimer.getText();
        String p_name = txtP_name.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        String d_name = String.valueOf(lblD_name.getValue());
        double Price= Double.parseDouble(txtPrice.getText());

        AppoinmentDTO appoinmentDTO = new AppoinmentDTO(id, type, date, time, p_name, address, tel, d_name,Price);
        boolean isAdded = appoinmentServise.addCustomer(appoinmentDTO);
        if (isAdded) {
            initialize();
            String Tittle="Added SuccessFully...";
            TrayNotification tray = new TrayNotification();
            AnimationType Type = AnimationType.POPUP;

            tray.setAnimationType(Type);
            tray.setTitle(Tittle);
            tray.setMessage(Tittle);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }


        ObservableList<AppoinmentTm> tmList = FXCollections.observableArrayList();
        for (AppoinmentDTO a1 : appoinmentServise.getAllCustomer()) {
            Button btn=new Button("Delete");
            Button btn1=new Button("update");
            AppoinmentTm tm = new AppoinmentTm(a1.getId(),a1.getType(),a1.getDate(),a1.getTime(),a1.getPatientname(),
                    a1.getAddress(),a1.getTel(),a1.getDoctorname(),a1.getPrice(),btn,btn1);
            tmList.add(tm);

            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure do you won't Delete this AppoinmentDTO"
                        , ButtonType.YES, ButtonType.NO);
                Optional<ButtonType>buttonType=alert.showAndWait();
                if(buttonType.get()==ButtonType.YES){
                }
            });
        }
        tbl1.setItems(tmList);
        new ZoomIn(tbl1).play();
    }
    private void setDate(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colP_name.setCellValueFactory(new PropertyValueFactory<>("patientname"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colD_name.setCellValueFactory(new PropertyValueFactory<>("doctorname"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


    }
}
