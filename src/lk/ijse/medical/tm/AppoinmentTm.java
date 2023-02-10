package lk.ijse.medical.tm;

import javafx.scene.control.Button;

public class AppoinmentTm {
    private String id;
    private String type;
    private String date;
    private String time;
    private String patientname;
    private String address;
    private String tel;
    private String doctorname;
    private double total;
    private Button btn;
    private Button btn1;

    public AppoinmentTm() {
    }

    public AppoinmentTm(String id, String type, String date, String time, String patientname, String address, String tel, String doctorname, double total, Button btn, Button btn1) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.time = time;
        this.patientname = patientname;
        this.address = address;
        this.tel = tel;
        this.doctorname = doctorname;
        this.total = total;
        this.btn = btn;
        this.btn1 = btn1;
    }

    public AppoinmentTm(String id, String type, String date, String time, String patientname, String address, String tel, String doctorname, double price, Button btn) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public Button getBtn1() {
        return btn1;
    }

    public void setBtn1(Button btn1) {
        this.btn1 = btn1;
    }
}
