package lk.ijse.medical.control.reciption;

import animatefx.animation.ZoomIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.AppoinmentDAO;
import lk.ijse.medical.dao.custom.MedicineDAO;
import lk.ijse.medical.dao.custom.OrderDAO;
import lk.ijse.medical.dao.custom.OrderDetailDAO;
import lk.ijse.medical.entity.Medicine;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.*;
import lk.ijse.medical.tm.MedicineDetailsTm;
import lk.ijse.medical.dto.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class PlaceAppoinmentFormController {
    public ComboBox cobA_id;
    public Label lblp_name;
    public Label lblD_name;
    public Label lblDate;
    public ComboBox cobM_id;
    public Label lblDescription;
    public Label lblType;
    public Label lblUnit_Price;
    public Label lblQty;
    public TextField txtQty;
    public TableView tbl1;
    public TableColumn colM_id;
    public TableColumn col_Description;
    public TableColumn colType;
    public TableColumn col_Price;
    public TableColumn colQty;
    public TableColumn colTotal;
    public Label lblTotal;
    public TableColumn colOption;
    public Label lblOreder;

    private final AppoinmentServise appoinmentServise= (AppoinmentServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.APPOINMENT);
    private final OrderServise orderServise= (OrderServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.ORDER);
    private final MedicineServise medicineServise= (MedicineServise)ServiseFactory.getServiseFactory().getServise(ServiseTypes.MEDICINE);
    private final OrderDetailServise orderDetailServise= (OrderDetailServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.ORDERDETAIL);
    private final PlaceAppoinmentServise placeAppoinmentServise= (PlaceAppoinmentServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.PLACEAPPOINMENT);


    public void initialize(){
        setdata();
        loadAppoinmentIds();
        loadMedicineIds();
        loadNextOrderId();
        setAnimation();
        cobA_id.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue) ->{
            if(newValue!=null){
                try {
                    searchCustomerDetails();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        cobM_id.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue,newValue) ->{
            if(newValue!=null){
                try {
                    searchMedicineDetails();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void setdata(){
        colM_id.setCellValueFactory(new PropertyValueFactory<>("m_id"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


    }

   private void searchCustomerDetails() throws SQLException, ClassNotFoundException {
        for (AppoinmentDTO a1:appoinmentServise.getAllCustomer()
        ) {
            if(a1.getId().equals(cobA_id.getValue())){
                lblp_name.setText(a1.getPatientname());
                lblD_name.setText(a1.getDoctorname());
                lblDate.setText(a1.getDate());


            }
        }
    }
    private void searchMedicineDetails() throws SQLException, ClassNotFoundException {
        for (MedicineDTO a1:medicineServise.getAllMedicine()
        ) {
            if(a1.getId().equals(cobM_id.getValue())){
                lblDescription.setText(a1.getDescription());
                lblType.setText(a1.getType());
                lblUnit_Price.setText(String.valueOf(a1.getPrice()));
                lblQty.setText(String.valueOf(a1.getQty()));


            }
        }
    }

    private void loadAppoinmentIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = appoinmentServise.loadCustomerIds();

        for (String id : idList) {
            observableList.add(id);
        }
        cobA_id.setItems(observableList);
    }
    private void loadMedicineIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        ArrayList<String> idList = medicineServise.loadMedicineIds();

        for (String id : idList) {
            observableList.add(id);
        }
        cobM_id.setItems(observableList);
    }
    private void setAnimation(){
        new ZoomIn(cobA_id).play();
        new ZoomIn(cobM_id).play();
        new ZoomIn(lblp_name).play();
        new ZoomIn(lblDate).play();
        new ZoomIn(lblDescription).play();
        new ZoomIn(lblUnit_Price).play();
        new ZoomIn(lblD_name).play();
        new ZoomIn(lblOreder).play();
        new ZoomIn(lblType).play();
        new ZoomIn(tbl1).play();


    }
    private ObservableList<MedicineDetailsTm> obList = FXCollections.observableArrayList();
    Button btnDelete = new Button("Delete");
    public void addOnAction(ActionEvent actionEvent) {
        if (txtQty.getText().equals("")) {
            new Alert(Alert.AlertType.WARNING, "Enter Qty for the First please!!..").show();
        }else {
            if (Integer.parseInt(lblQty.getText()) >= Integer.parseInt(txtQty.getText())) {
                String M_id = String.valueOf(cobM_id.getValue());
                String o_id = lblOreder.getText();
                String desc = lblDescription.getText();
                int qty = Integer.parseInt(txtQty.getText());
                double unitPrice = Double.parseDouble(lblUnit_Price.getText());
                double total = unitPrice * qty;


                int row = alreadyExists(M_id);
                if (row == -1) {
                    MedicineDetailsTm tm = new MedicineDetailsTm(M_id, o_id, desc, qty, (int) unitPrice, total, btnDelete);
                    obList.add(tm);
                    tbl1.setItems(obList);
                } else {
                    int tempqty = (int) (obList.get(row).getUnit_price() +unitPrice);
                    double tempTotal = unitPrice * qty;
                    obList.get(row).setQty(tempqty);
                    obList.get(row).setTotal(tempTotal);
                    tbl1.refresh();
                }
                calculateNetTotal();
                clearQtyTextField();
                cobM_id.requestFocus();
            }else{
                new Alert(Alert.AlertType.WARNING,"PLz enter the valid qty").show();
            }


            btnDelete.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.YES) {
                    for (MedicineDetailsTm tm : obList
                    ) {
                        if (tm.getM_id().equals(tm.getM_id())) {
                            obList.remove(tm);
                            calculateNetTotal();
                            tbl1.refresh();

                        }
                    }
                }
            });
        }
    }
    private int alreadyExists(String code) {
        for (int i = 0; i <obList.size(); i++) {
            if(obList.get(i).getM_id().equals(code)){
                return i;
            }
        }
        return -1;
    }

    private void calculateNetTotal() {
     if(tbl1.getItems().size()>0){
         double total=0;
         for (int i = 0; i <tbl1.getItems().size(); i++) {
             MedicineDetailsTm medicineDetailsTm= (MedicineDetailsTm) tbl1.getItems().get(i);
             total+=medicineDetailsTm.getTotal();
         }
         lblTotal.setText(String.valueOf(total));
     }

    }

    private void clearQtyTextField() {
        calculateNetTotal();
        txtQty.setText("");
    }
    private void loadOrderDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadNextOrderId()  {
        String orderId = orderServise.generateNextOrderId();
        lblOreder.setText(orderId);
    }


    public void placeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orderId = lblOreder.getText();
        String date=lblDate.getText();
        String A_id = String.valueOf(cobA_id.getValue());
        double total= Double.parseDouble(lblTotal.getText());

        ArrayList<MedicineDetails> cartDetails = new ArrayList<>();


        for (int i = 0; i < tbl1.getItems().size(); i++) {

            MedicineDetailsTm tm = obList.get(i);
            cartDetails.add(new MedicineDetails(orderId, tm.getM_id(), tm.getDescription(), tm.getQty(), (int) tm.getUnit_price()));
            System.out.println(tm);

        }
        System.out.println(orderId);
        PlaceAppoinmentDTO placeAppoinment = new PlaceAppoinmentDTO(orderId,date,A_id, total, cartDetails);
        System.out.println(placeAppoinment);

        boolean isPlaced = placeAppoinmentServise.placeOrder(placeAppoinment);
        if (isPlaced) {
             /* to clear table */
             obList.clear();
             loadNextOrderId();
             new Alert(Alert.AlertType.CONFIRMATION, "OrderDTO Placed!").show();
           // System.out.println(obList);
         } else {
             new Alert(Alert.AlertType.WARNING, "OrderDTO Not Placed!").show();
         }

    }


}
