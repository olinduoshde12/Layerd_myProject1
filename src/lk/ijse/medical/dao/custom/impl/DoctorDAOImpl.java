package lk.ijse.medical.dao.custom.impl;

import lk.ijse.medical.dao.custom.DoctorDAO;
import lk.ijse.medical.db.DBconnection;
import lk.ijse.medical.dto.DoctorDTO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.entity.Doctor;
import lk.ijse.medical.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorDAOImpl implements DoctorDAO {
    public  ArrayList<String> loadCustomerTypes(){

        try {
           ResultSet result = CrudUtil.execute("SELECT Doctor_type FROM Doctor");
            ArrayList<String> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(result.getString(1));
            }
            return idList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }



    public  ArrayList<Doctor> getAll(){
        try {
          ResultSet  rst = CrudUtil.execute("SELECT * FROM Doctor");
            ArrayList<Doctor>customerList=new ArrayList<>();

            while(rst.next()){
                Doctor doctor = new Doctor(rst.getString("Doctor_Id"),rst.getString("Doctor_Name"),
                        rst.getString("Doctor_type"),rst.getString("Doctor_AliveDate"),
                        rst.getString("Doctor_AliveTime"),rst.getString("Contact_Number"));
                customerList.add(doctor);
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
    public  ArrayList<String> loadAppoinmentTypes(){
        try {
          ResultSet  result = CrudUtil.execute( "SELECT DISTINCT Doctor_type FROM Doctor");
            ArrayList<String> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(result.getString(1));
            }
            return idList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
    public  ArrayList<String> loadD_names(){

        try {
           ResultSet result = CrudUtil.execute("SELECT DISTINCT Doctor_Name  FROM Doctor ");
            ArrayList<String> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(result.getString(1));
            }
            return idList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
    public ArrayList<Doctor> getData(){
        String type="Heart Disses";
        try {
           ResultSet rst = CrudUtil.execute("SELECT * FROM Doctor WHERE Doctor_type='Heart Disses'");
            ArrayList<Doctor>customerList=new ArrayList<>();
            //System.out.println("abc");

            while(rst.next()){
                Doctor doctor = new Doctor(rst.getString(1),rst.getString(2),
                        rst.getString(3),rst.getString(4),
                        rst.getString(5),rst.getString(6));
                customerList.add(doctor);
                //System.out.println("abc");
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
    public  ArrayList<Doctor> getData2(){
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Doctor WHERE Doctor_type='Psychatrist'");
            ArrayList<Doctor>customerList=new ArrayList<>();
            //System.out.println("abc");

            while(rst.next()){
                Doctor doctor = new Doctor(rst.getString(1),rst.getString(2),
                        rst.getString(3),rst.getString(4),
                        rst.getString(5),rst.getString(6));
                customerList.add(doctor);
                //System.out.println("abc");
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }


    }
    public  ArrayList<Doctor> getData3(){

        try {
           ResultSet rst = CrudUtil.execute("SELECT * FROM Doctor WHERE Doctor_type='Pediatrician'");
            ArrayList<Doctor>customerList=new ArrayList<>();
            //System.out.println("abc");

            while(rst.next()){
                Doctor doctor = new Doctor(rst.getString(1),rst.getString(2),
                        rst.getString(3),rst.getString(4),
                        rst.getString(5),rst.getString(6));
                customerList.add(doctor);
                //System.out.println("abc");
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }
    public  ArrayList<Doctor> getData4() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Doctor WHERE Doctor_type='Demotologist'");
            ArrayList<Doctor>customerList=new ArrayList<>();
            //System.out.println("abc");

            while(rst.next()){
                Doctor doctor = new Doctor(rst.getString(1),rst.getString(2),
                        rst.getString(3),rst.getString(4),
                        rst.getString(5),rst.getString(6));
                customerList.add(doctor);
                //System.out.println("abc");
            }
            return customerList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
    public  String genarateMedicineId()  {
        try {
            ResultSet result = CrudUtil.execute("SELECT Doctor_Id   FROM Doctor ORDER BY Doctor_Id  DESC LIMIT 1");
            if (result.next()) {
                return generateNextOrderId(result.getString(1));
            }
            return generateNextOrderId(result.getString(null));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }



    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "D00" + id;
        }
        return "O001";

    }
    public  boolean save(Doctor doctor)  {
        try {
            return CrudUtil.execute("INSERT INTO Doctor VALUES(?,?,?,?,?,?)",
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getType(),
                    doctor.getAlive(),
                    doctor.getTime(),
                    doctor.getContact()

            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
    public  boolean deleteCustomer(String id) {
        try {
            return CrudUtil.execute("Delete From DoctorDTO where Doctor_id='"+id+"'");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }



    public  boolean update(Doctor doctor){
        try {
            return CrudUtil.execute("Update Doctor set Doctor_Name =?, Doctor_type=?, Doctor_AliveDate=?, " +
                            "Doctor_AliveTime=? Contact_Number=? WHERE Doctor_Id =?",

                    doctor.getName(),
                    doctor.getType(),
                    doctor.getAlive(),
                    doctor.getTime(),
                    doctor.getContact(),
                    doctor.getId()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }
    public Doctor searchCustomer(String id) {
        Connection connection = DBconnection.getInstance().getConnection();
        try {
            PreparedStatement  stm = connection.prepareStatement("Select * from Doctor where Doctor_Id=?");
            stm.setObject(1, id);

            ResultSet rst = stm.executeQuery();

            if (rst.next()) {
                Doctor doctor = new Doctor(rst.getString(1), rst.getString(2),
                        rst.getString(3), rst.getString(4),rst.getString(5),rst.getString(6));
                return doctor;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }
    public  ArrayList<Integer> countDocors(){
        try {
            ResultSet result = CrudUtil.execute( "SELECT COUNT(DISTINCT Doctor_Id) FROM Doctor ");
            ArrayList<Integer> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(Integer.parseInt(result.getString(1)));
            }
            return idList;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }

}
