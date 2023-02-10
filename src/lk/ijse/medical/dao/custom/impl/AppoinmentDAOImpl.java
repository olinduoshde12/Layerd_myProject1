package lk.ijse.medical.dao.custom.impl;

import javafx.scene.control.Button;
import lk.ijse.medical.dao.custom.AppoinmentDAO;
import lk.ijse.medical.db.DBconnection;
import lk.ijse.medical.dto.AppoinmentDTO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppoinmentDAOImpl implements AppoinmentDAO {
    private static Button btn;

    public  boolean save(Appoinment appoinment)  {
        try {
            return CrudUtil.execute("INSERT INTO Appoinment VALUES(?,?,?,?,?,?,?,?,?)",
                    appoinment.getId(),
                    appoinment.getType(),
                    appoinment.getDate(),
                    appoinment.getTime(),
                    appoinment.getPatientname(),
                    appoinment.getAddress(),
                    appoinment.getTel(),
                    appoinment.getDoctorname(),
                    appoinment.getTotal()

            );
        } catch (SQLException e){
            throw new RuntimeException("Failed to save the appoinmentDTO");
        }
    }




    public  ArrayList<Appoinment> getAll(){
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Appoinment");
            ArrayList<Appoinment>customerList=new ArrayList<>();

            while(rst.next()){
                Appoinment appoinment = new Appoinment(rst.getString("Appoinment_Id"),rst.getString("Appoinment_Type"),rst.getString("Appoinment_Date"),
                        rst.getString("Appoinment_Time"),rst.getString("Pateint_name"),rst.getString("Address"),rst.getString("Tel_no"),
                        rst.getString("Doctor_name"),rst.getDouble("Price"));
                customerList.add(appoinment);
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the getApppoinment");
        }

    }



    public  boolean deleteCustomer(String id) {
        try {
            return CrudUtil.execute("Delete From Appoinment where Appoinment_Id='"+id+"'");
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
    public  boolean updateCustomer(Appoinment appoinment) throws ClassNotFoundException, SQLException {
        Connection connection = DBconnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update Appoinment set Pateint_name=?, Address=?, Tel_no=? where Appoinment_Id=?");
        stm.setObject(1, appoinment.getPatientname());
        stm.setObject(2, appoinment.getAddress());
        stm.setObject(3, appoinment.getTel());
        stm.setObject(4, appoinment.getId());

        return  stm.executeUpdate()>0;
    }
    public  Appoinment searchCustomer(String id){
        Connection connection = DBconnection.getInstance().getConnection();

        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Appoinment WHERE Appoinment_Id=?");
            stm.setObject(1, id);


            ResultSet rst = stm.executeQuery();

            if (rst.next()) {
                Appoinment appoinment = new Appoinment(rst.getString("Appoinment_Id"), rst.getString("Appoinment_Type"),
                        rst.getString("Appoinment_Date"),
                        rst.getString("Appoinment_Time"),rst.getString("Pateint_name"),
                        rst.getString("Address"),rst.getString("Tel_no"),rst.getString("Doctor_name"),rst.getDouble("Price"));
                return appoinment;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }
    public  ArrayList<String> loadCustomerIds(){

        try {
           ResultSet result = CrudUtil.execute( "SELECT * FROM Appoinment ORDER BY Appoinment_Id DESC LIMIT 1");
            ArrayList<String> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(result.getString(1));
            }
            return idList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the getApppoinment");
        }
    }
    public  ArrayList<Integer> countorder()  {

        try {
            ResultSet result = CrudUtil.execute("SELECT COUNT(DISTINCT Appoinment_Id) FROM Appoinment");
            ArrayList<Integer> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(Integer.parseInt(result.getString(1)));
            }
            return idList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the getApppoinment");
        }

    }
    public  ArrayList<String> loadCustomerTypes(){
        try {
           ResultSet result = CrudUtil.execute("SELECT DISTINCT Appoinment_Type FROM Appoinment");
            ArrayList<String> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(result.getString(1));
            }
            return idList;
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the getApppoinment");
        }
    }



    public  String generateNextAppoinmentId() {
        try {
          ResultSet  result = CrudUtil.execute("SELECT Appoinment_Id FROM Appoinment ORDER BY Appoinment_Id DESC LIMIT 1");
            if (result.next()) {
                return generateNextOrderId(result.getString(1));
            }
            return generateNextOrderId(result.getString(null));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the getApppoinment");
        }
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("A0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "A0" + id;
        }
        return "O001";

    }
}
