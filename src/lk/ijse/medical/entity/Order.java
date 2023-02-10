package lk.ijse.medical.entity;

public class Order implements SuperEntity{
    private String o_Id;
    private String date;
    private String Appoinment_id;
    private double Total;

    public Order() {
    }

    public Order(String o_Id, String date, String appoinment_id, double total) {
        this.o_Id = o_Id;
        this.date = date;
        Appoinment_id = appoinment_id;
        Total = total;
    }

    public String getO_Id() {
        return o_Id;
    }

    public void setO_Id(String o_Id) {
        this.o_Id = o_Id;
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

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }
}
