package lk.ijse.medical.dto;

public class MedicineDTO {
    private  String id;
    private String description;
    private String type;
    private int qty;
    private double price;

    public MedicineDTO() {
    }

    public MedicineDTO(String id, String description, String type, int qty, double price) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.qty = qty;
        this.price = price;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
