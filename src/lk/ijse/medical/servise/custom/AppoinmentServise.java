package lk.ijse.medical.servise.custom;

import javassist.NotFoundException;
import lk.ijse.medical.dto.AppoinmentDTO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.servise.SuperServise;

import java.util.ArrayList;

public interface AppoinmentServise extends SuperServise {

    boolean addCustomer(AppoinmentDTO appoinment);

    ArrayList<AppoinmentDTO> getAllCustomer();

    void deleteCustomer(String id) throws NotFoundException;

    ArrayList<String> loadCustomerIds();

    ArrayList<Integer> countorder();

    ArrayList<String> loadCustomerTypes();

    String generateNextAppoinmentId();

    Appoinment searchCustomer(String id);


}
