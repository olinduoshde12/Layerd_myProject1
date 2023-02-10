package lk.ijse.medical.servise.custom;

import lk.ijse.medical.dto.DoctorDTO;
import lk.ijse.medical.entity.Doctor;
import lk.ijse.medical.servise.SuperServise;

import java.util.ArrayList;

public interface DoctorServise extends SuperServise {

     ArrayList<String> loadCustomerTypes();

     ArrayList<DoctorDTO> getAllDoctors();

     ArrayList<String> loadAppoinmentTypes();

     ArrayList<String> loadD_names();

     ArrayList<DoctorDTO> getData();

    ArrayList<DoctorDTO> getData2();

     ArrayList<DoctorDTO> getData3();

    ArrayList<DoctorDTO> getData4();



     boolean addDoctor(DoctorDTO doctor);


    boolean deleteCustomer(String id);

    boolean update(DoctorDTO doctorDTO);

     DoctorDTO searchCustomer(String id);

     ArrayList<Integer> countDocors();


}
