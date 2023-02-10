package lk.ijse.medical.dto;

public class MedicineDetails { //addtocart

    private String m_id;
    private  String o_id;
    private String description;
    private double unit_price;
    private int qty;

    public MedicineDetails() {
    }

    public MedicineDetails(String o_id, String m_id, String description, double unit_price, int qty) {
        this.o_id = o_id;
        this.m_id = m_id;
        this.description = description;
        this.unit_price = unit_price;
        this.qty = qty;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
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
}
