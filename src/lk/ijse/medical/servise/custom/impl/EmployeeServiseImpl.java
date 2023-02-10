package lk.ijse.medical.servise.custom.impl;

import javassist.NotFoundException;
import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.EmployeeDAO;
import lk.ijse.medical.dto.EmployeeDTO;
import lk.ijse.medical.entity.Employee;
import lk.ijse.medical.servise.custom.EmployeeServise;
import lk.ijse.medical.servise.custom.exeption.InUseException;
import lk.ijse.medical.servise.util.Conveter;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class EmployeeServiseImpl implements EmployeeServise {
    private final EmployeeDAO employeeDAO= (EmployeeDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.EMPLOYEE);
    @Override
    public boolean addCustomer(EmployeeDTO employee) {
        return employeeDAO.save(Conveter.toEmployee(employee));
    }

    @Override
    public String genarateMedicineId() {
        return employeeDAO.genarateMedicineId();
    }

    @Override
    public Employee searchEmployee(String id) {
        return employeeDAO.searchEmployee(id);
    }

    @Override
    public boolean deleteEmployee(String id) throws NotFoundException {
        if (!employeeDAO.deleteEmployee(id))throw new NotFoundException("Book not found");

        try {
            employeeDAO.deleteEmployee(id);
        }catch (ConstraintViolationException e){
            throw new InUseException("Book already in used");
        }
        return false;
    }

    @Override
    public ArrayList<Integer> countEmployee() {
        return employeeDAO.countEmployee();
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployess() {
        return (ArrayList<EmployeeDTO>) employeeDAO.getAll().stream().map(employee -> Conveter.toemployeeDTO(employee)).collect(Collectors.toList());
    }
}
