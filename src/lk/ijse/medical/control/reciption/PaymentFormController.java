package lk.ijse.medical.control.reciption;

import animatefx.animation.ZoomIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.db.DBconnection;
import lk.ijse.medical.entity.Order;
import lk.ijse.medical.entity.Payment;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.*;
import lk.ijse.medical.tm.PaymentTm;
import lk.ijse.medical.dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentFormController {

    public ComboBox cobO_id;
    public TextField txtA_id;
    public TextField txtDate;
    public TextField txtMedicineTotal;
    public Label lblFullTotal;
    public TextField txtP_name;
    public TextField txtD_name;
    public TextField txtTotal;
    public TableColumn colD_name;
    public TableView tbl1;
    public Label lblPL_ID;
    public TableView tblPay;
    public TableColumn colP_id;
    public TableColumn colA_id1;
    public TableColumn col_P_name;
    public TableColumn colDate1;
    public TableColumn colM_tot;
    public TableColumn colAp_Tot;
    public TableColumn colF_tot;
    public TableColumn colP_id1;

    private final PayMentServise payMentServise= (PayMentServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.PAYMENT);
    private final AppoinmentServise appoinmentServise= (AppoinmentServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.APPOINMENT);
    private final OrderServise orderServise= (OrderServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.ORDER);;
    private final MedicineServise medicineServise= (MedicineServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.MEDICINE);;
    private final OrderDetailServise orderDetailServise= (OrderDetailServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.ORDERDETAIL);;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadTypes();
        loadNextOrderId1();
        cobO_id.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue) ->{
            if(newValue!=null){
                try {
                    searchMedicineDetails();
                    serchAppoinmentDetails();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadNextOrderId1() throws SQLException, ClassNotFoundException {
        String orderId = payMentServise.generateNextPaymentId();
        lblPL_ID.setText(orderId);
    }


    private void searchMedicineDetails() throws SQLException, ClassNotFoundException {
        for (Order a1: orderServise.getAllOrder()) {

            if(a1.getO_Id().equals(cobO_id.getValue())) {
                txtA_id.setText(a1.getAppoinment_id());
                txtDate.setText(String.valueOf(a1.getDate()));
                txtMedicineTotal.setText(String.valueOf(a1.getTotal()));
            }

        }
    }
    private void serchAppoinmentDetails() throws SQLException, ClassNotFoundException {
        for (AppoinmentDTO a1: appoinmentServise.getAllCustomer()) {

            if(a1.getId().equals(txtA_id.getText())) {
                txtP_name.setText(a1.getPatientname());
                txtD_name.setText(String.valueOf(a1.getDoctorname()));
                txtTotal.setText(String.valueOf(a1.getPrice()));
            }

        }
    }



    private void loadTypes() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = orderServise.loadOrderIds();

        for (String type : idList) {
            observableList.add(type);
        }
        cobO_id.setItems(observableList);
    }


    public void PayOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String p_id=lblPL_ID.getText();
        String A_id=txtA_id.getText();
        String date=txtDate.getText();
        String P_name=txtP_name.getText();
        String D_name=txtD_name.getText();
        double M_tot= Double.parseDouble(txtMedicineTotal.getText());
        double A_tot= Double.parseDouble(txtTotal.getText());
        double tot=M_tot+A_tot;

        PaymentDTO paymentDTO = new PaymentDTO(p_id,A_id,date,P_name,D_name,M_tot,A_tot,tot);
        boolean isAdded = payMentServise.addPayment(paymentDTO);
        if (isAdded) {
            new Alert(Alert.AlertType.INFORMATION, "success..!!").show();
        }
        ObservableList<PaymentTm> tmList = FXCollections.observableArrayList();
        for (Payment a1 : payMentServise.getAllPayment()) {
            PaymentTm tm = new PaymentTm(a1.getPl_id(),a1.getA_id(),a1.getDate(),a1.getP_name(),a1.getD_name(),a1.getM_Total(),
                    a1.getA_Total(),a1.getF_Total());
            tmList.add(tm);
        }
        tblPay.setItems(tmList);
        lblFullTotal.setText(String.valueOf(tot));
        new ZoomIn(tblPay).play();

        colP_id1.setCellValueFactory(new PropertyValueFactory<>("Pl_id"));
        colA_id1.setCellValueFactory(new PropertyValueFactory<>("A_id"));
        colDate1.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_P_name.setCellValueFactory(new PropertyValueFactory<>("P_name"));
        colD_name.setCellValueFactory(new PropertyValueFactory<>("D_name"));
        colM_tot.setCellValueFactory(new PropertyValueFactory<>("M_Total"));
        colAp_Tot.setCellValueFactory(new PropertyValueFactory<>("A_Total"));
        colF_tot.setCellValueFactory(new PropertyValueFactory<>("F_Total"));

    }



    public void reportOnAction(ActionEvent actionEvent) {
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/medical/report/Payment1.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null,
                    DBconnection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            throw  new RuntimeException(e);
        }
    }
}
