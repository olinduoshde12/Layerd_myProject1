package lk.ijse.medical.dto;

import java.util.ArrayList;

public class PlaceAppoinmentDTO { // placeorder
    private String o_id;
    private String date;
    private String Appoinment_id;
    private double Total;
    private ArrayList<MedicineDetails> orderDetailList;

    public PlaceAppoinmentDTO() {
    }

    public PlaceAppoinmentDTO(String o_id, String date, String appoinment_id, double total, ArrayList<MedicineDetails> orderDetailList) {
        this.o_id = o_id;
        this.date = date;
        Appoinment_id = appoinment_id;
        this.orderDetailList = orderDetailList;
        this.Total=total;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppoinment_id() {
        return Appoinment_id;
    }

    public void setAppoinment_id(String appoinment_id) {
        Appoinment_id = appoinment_id;
    }

    public ArrayList<MedicineDetails> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(ArrayList<MedicineDetails> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public void setTotal(double total){
        this.Total=total;
    }
    public double getTotal(){
        return  Total;
    }

    @Override
    public String toString() {
        return "PlaceAppoinmentDTO{" +
                "o_id='" + o_id + '\'' +
                ", date='" + date + '\'' +
                ", Appoinment_id='" + Appoinment_id + '\'' +
                ", Total=" + Total +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
