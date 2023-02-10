package lk.ijse.medical.servise.custom.impl;

import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.DoctorDAO;
import lk.ijse.medical.dto.DoctorDTO;
import lk.ijse.medical.entity.Doctor;
import lk.ijse.medical.servise.custom.DoctorServise;
import lk.ijse.medical.servise.custom.exeption.InUseException;
import lk.ijse.medical.servise.custom.exeption.NotFoundException;
import lk.ijse.medical.servise.util.Conveter;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DoctorServiseImpl implements DoctorServise {

    private final DoctorDAO doctorDAO= (DoctorDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.DOCTOR);

    @Override
    public ArrayList<String> loadCustomerTypes() {
        return doctorDAO.loadAppoinmentTypes();
    }

    @Override
    public ArrayList<DoctorDTO> getAllDoctors() {
        return (ArrayList<DoctorDTO>) doctorDAO.getAll().stream().map(doctor -> Conveter.todoctorDTO(doctor)).
                collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> loadAppoinmentTypes() {
        return doctorDAO.loadAppoinmentTypes();
    }

    @Override
    public ArrayList<String> loadD_names() {
        return doctorDAO.loadD_names();
    }

    @Override
    public ArrayList<DoctorDTO> getData() {
        return (ArrayList<DoctorDTO>) doctorDAO.getData().stream().map(doctor -> Conveter.todoctorDTO(doctor)).
                collect(Collectors.toList());
    }

    @Override
    public ArrayList<DoctorDTO> getData2() {
        return (ArrayList<DoctorDTO>) doctorDAO.getData2().stream().map(doctor -> Conveter.todoctorDTO(doctor)).
                collect(Collectors.toList());
    }

    @Override
    public ArrayList<DoctorDTO> getData3() {
        return (ArrayList<DoctorDTO>) doctorDAO.getData3().stream().map(doctor -> Conveter.todoctorDTO(doctor)).
                collect(Collectors.toList());
    }

    @Override
    public ArrayList<DoctorDTO> getData4() {
        return (ArrayList<DoctorDTO>) doctorDAO.getData4().stream().map(doctor -> Conveter.todoctorDTO(doctor)).
                collect(Collectors.toList());
    }

    @Override
    public boolean addDoctor(DoctorDTO doctor) {
        return doctorDAO.save(Conveter.toDoctor(doctor));
    }


    @Override
    public boolean deleteCustomer(String id) {
        if (!doctorDAO.deleteCustomer(id)) throw new NotFoundException("Book not found");

        try {
            doctorDAO.deleteCustomer(id);
        }catch (ConstraintViolationException e){
            throw new InUseException("Doctor already in used");
        }
        return false;
    }

    @Override
    public boolean update(DoctorDTO doctorDTO) {

        return doctorDAO.update(Conveter.toDoctor(doctorDTO));
    }





    @Override
    public DoctorDTO searchCustomer(String id) {
        return null;
    }

    @Override
    public ArrayList<Integer> countDocors() {
        return doctorDAO.countDocors();
    }
}
