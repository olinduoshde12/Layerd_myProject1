package lk.ijse.medical.dto;

public class EmployeeDTO {
    private String id;
    private String name;
    private String dob;
    private String exp;
    private String contact;
    private String nic;
    private String type;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, String name, String dob, String exp, String contact, String nic, String type) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.exp = exp;
        this.contact = contact;
        this.nic = nic;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
