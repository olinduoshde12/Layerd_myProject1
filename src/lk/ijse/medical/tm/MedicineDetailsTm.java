package lk.ijse.medical.tm;

import javafx.scene.control.Button;

public class MedicineDetailsTm {
    private String m_id;
    private String o_id;
    private String description;
    private double unit_price;
    private int qty;
    private double total;
    private Button btn;

    public MedicineDetailsTm() {
    }

    public MedicineDetailsTm(String m_id, String o_id, String description, double unit_price, int qty, double total, Button btn) {
        this.m_id = m_id;
        this.o_id = o_id;
        this.description = description;
        this.unit_price = unit_price;
        this.qty = qty;
        this.total = total;
        this.btn = btn;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    @Override
    public String toString() {
        return "MedicineDetailsTm{" +
                "m_id='" + m_id + '\'' +
                ", o_id='" + o_id + '\'' +
                ", description='" + description + '\'' +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                ", total=" + total +
                ", btn=" + btn +
                '}';
    }
}
