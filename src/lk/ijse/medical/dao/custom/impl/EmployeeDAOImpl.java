package lk.ijse.medical.dao.custom.impl;

import lk.ijse.medical.dao.custom.EmployeeDAO;
import lk.ijse.medical.db.DBconnection;
import lk.ijse.medical.dto.EmployeeDTO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.entity.Employee;
import lk.ijse.medical.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public  boolean save(EmployeeDTO employee){
        try {
            return CrudUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?,?,?)",
                    employee.getId(),
                    employee.getName(),
                    employee.getDob(),
                    employee.getExp(),
                    employee.getContact(),
                    employee.getNic(),
                    employee.getType()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }


    public  String genarateMedicineId() {

        try {
          ResultSet  result = CrudUtil.execute("SELECT Employee_Id   FROM Employee ORDER BY  Employee_Id DESC LIMIT 1");
            if (result.next()) {
                return generateNextOrderId(result.getString(1));
            }
            return generateNextOrderId(result.getString(null));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }

    public static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("E00");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "E00" + id;
        }
        return "O001";

    }
    public Employee searchEmployee(String id){
        Connection connection = DBconnection.getInstance().getConnection();

        try {
            PreparedStatement stm = connection.prepareStatement("Select * from Employee where Employee_Id=?");
            stm.setObject(1, id);

            ResultSet rst = stm.executeQuery();

            if (rst.next()) {
                Employee employee = new Employee(rst.getString(1), rst.getString(2),
                        rst.getString(3), rst.getString(4),rst.getString(5),
                        rst.getString(6),rst.getString(7));
                return employee;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public  boolean deleteEmployee(String id){
        try {
            return  CrudUtil.execute("Delete From Employee where Employee_Id ='"+id+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  ArrayList<Integer> countEmployee(){
        try {
          ResultSet  result = CrudUtil.execute("SELECT COUNT(DISTINCT Employee_Id) FROM Employee");
            ArrayList<Integer> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(Integer.parseInt(result.getString(1)));
            }
            return idList;
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }


    @Override
    public boolean save(Employee entity) {
        return false;
    }

    @Override
    public ArrayList<Employee> getAll() {
        return null;
    }
}
