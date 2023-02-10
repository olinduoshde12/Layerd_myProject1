package lk.ijse.medical.servise.custom.impl;

import javassist.NotFoundException;
import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.AppoinmentDAO;
import lk.ijse.medical.dto.AppoinmentDTO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.servise.custom.AppoinmentServise;
import lk.ijse.medical.servise.custom.exeption.InUseException;
import lk.ijse.medical.servise.util.Conveter;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AppoinmentServiseImpl implements AppoinmentServise {

    private final AppoinmentDAO appoinmentDAO= (AppoinmentDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.APPOINMENT);

    @Override
    public boolean addCustomer(AppoinmentDTO appoinment) {
        return appoinmentDAO.save(Conveter.toAppoinment(appoinment));
    }

    @Override
    public ArrayList<AppoinmentDTO> getAllCustomer() {
        return (ArrayList<AppoinmentDTO>) appoinmentDAO.getAll().stream().
                map(appoinment -> Conveter.toAppoinmentDTO(appoinment)).collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(String id) throws NotFoundException,InUseException{
        if (!appoinmentDAO.deleteCustomer(id))throw new NotFoundException("Book not found");

        try {
            appoinmentDAO.deleteCustomer(id);
        }catch (ConstraintViolationException e){
            throw new InUseException("Book already in used");
        }
    }

    @Override
    public ArrayList<String> loadCustomerIds() {
        return appoinmentDAO.loadCustomerIds();
    }

    @Override
    public ArrayList<Integer> countorder() {
        return appoinmentDAO.countorder();
    }

    @Override
    public ArrayList<String> loadCustomerTypes() {
        return appoinmentDAO.loadCustomerTypes();
    }

    @Override
    public String generateNextAppoinmentId() {
        return appoinmentDAO.generateNextAppoinmentId();
    }

    @Override
    public Appoinment searchCustomer(String id) {
        return appoinmentDAO.searchCustomer(id);
    }


}
