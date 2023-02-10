package lk.ijse.medical.dao.custom;

import lk.ijse.medical.dao.CrudDAO;
import lk.ijse.medical.dto.AppoinmentDTO;
import lk.ijse.medical.entity.Appoinment;

import java.util.ArrayList;
import java.util.List;

public interface AppoinmentDAO extends CrudDAO<Appoinment,String>{


    boolean deleteCustomer(String id);

    ArrayList<String> loadCustomerIds();

    ArrayList<Integer> countorder();

    ArrayList<String> loadCustomerTypes();

    String generateNextAppoinmentId();

    Appoinment searchCustomer(String id);


}
