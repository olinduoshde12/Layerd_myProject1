package lk.ijse.medical.dao.custom;

import lk.ijse.medical.dao.CrudDAO;
import lk.ijse.medical.dao.SuperDAO;
import lk.ijse.medical.dto.DoctorDTO;
import lk.ijse.medical.entity.Doctor;

import java.util.ArrayList;

public interface DoctorDAO extends CrudDAO<Doctor,String> {



     ArrayList<String> loadAppoinmentTypes();

     ArrayList<String> loadD_names();

     ArrayList<Doctor> getData();

    ArrayList<Doctor> getData2();

     ArrayList<Doctor> getData3();

    ArrayList<Doctor> getData4();

     String genarateMedicineId();


    boolean deleteCustomer(String id);

    boolean update(Doctor doctor);

     Doctor searchCustomer(String id);

     ArrayList<Integer> countDocors();


}
