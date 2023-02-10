package lk.ijse.medical.dto;

public class PaymentDTO {
    private  String Pl_id ;
    private  String A_id;
    private String date;
    private String P_name;
    private String D_name;
    private double M_Total;
    private double A_Total;
    private double F_Total;

    public PaymentDTO(String pl_id, String a_id, String date, String p_name, String d_name, double m_Total, double a_Total, double f_Total) {
        Pl_id = pl_id;
        A_id = a_id;
        this.date = date;
        P_name = p_name;
        D_name = d_name;
        M_Total = m_Total;
        A_Total = a_Total;
        F_Total = f_Total;
    }

    public PaymentDTO(String p_id, String o_id, String a_id, String date, String p_name, String d_name, double m_tot, double a_tot, String tot) {
    }

    public String getPl_id() {
        return Pl_id;
    }

    public void setPl_id(String pl_id) {
        Pl_id = pl_id;
    }

    public String getA_id() {
        return A_id;
    }

    public void setA_id(String a_id) {
        A_id = a_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getP_name() {
        return P_name;
    }

    public void setP_name(String p_name) {
        P_name = p_name;
    }

    public String getD_name() {
        return D_name;
    }

    public void setD_name(String d_name) {
        D_name = d_name;
    }

    public double getM_Total() {
        return M_Total;
    }

    public void setM_Total(double m_Total) {
        M_Total = m_Total;
    }

    public double getA_Total() {
        return A_Total;
    }

    public void setA_Total(double a_Total) {
        A_Total = a_Total;
    }

    public double getF_Total() {
        return F_Total;
    }

    public void setF_Total(double f_Total) {
        F_Total = f_Total;
    }
}
