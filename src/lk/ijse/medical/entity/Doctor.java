package lk.ijse.medical.entity;

public class Doctor implements SuperEntity {
    private String id;
    private String name;
    private String type;
    private String alive ;
    private String time;
    private String contact;

    public Doctor() {
    }

    public Doctor(String id, String name, String type, String alive, String contact,String time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.alive = alive;
        this.contact = contact;
        this.time=time;
    }

    public Doctor(Doctor id, String name, String type, String alive, String contact, String time) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setTime(String time){
        this.time=time;
    }
    public String getTime(){
        return time;
    }
}
