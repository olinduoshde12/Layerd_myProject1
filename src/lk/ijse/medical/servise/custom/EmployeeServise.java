package lk.ijse.medical.servise.custom;

import javassist.NotFoundException;
import lk.ijse.medical.dto.EmployeeDTO;
import lk.ijse.medical.entity.Employee;
import lk.ijse.medical.servise.SuperServise;

import java.util.ArrayList;

public interface EmployeeServise extends SuperServise {
     boolean addCustomer(EmployeeDTO employee);


     String genarateMedicineId();

     Employee searchEmployee(String id);

     boolean deleteEmployee(String id) throws NotFoundException;

     ArrayList<Integer> countEmployee();

     ArrayList<EmployeeDTO> getAllEmployess();


}
