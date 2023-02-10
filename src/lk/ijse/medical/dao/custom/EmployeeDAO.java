package lk.ijse.medical.dao.custom;

import lk.ijse.medical.dao.CrudDAO;
import lk.ijse.medical.dao.SuperDAO;
import lk.ijse.medical.dto.EmployeeDTO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.entity.Employee;

import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    // boolean addCustomer(EmployeeDTO employee);


     String genarateMedicineId();

     Employee searchEmployee(String id);

     boolean deleteEmployee(String id);

     ArrayList<Integer> countEmployee();




}
