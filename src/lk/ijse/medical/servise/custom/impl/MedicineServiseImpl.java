package lk.ijse.medical.servise.custom.impl;

import javassist.NotFoundException;
import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.MedicineDAO;
import lk.ijse.medical.dto.MedicineDTO;
import lk.ijse.medical.dto.MedicineDetails;
import lk.ijse.medical.entity.Medicine;
import lk.ijse.medical.servise.custom.MedicineServise;
import lk.ijse.medical.servise.custom.exeption.InUseException;
import lk.ijse.medical.servise.util.Conveter;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MedicineServiseImpl implements MedicineServise {
    private final MedicineDAO medicineDAO= (MedicineDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.MEDICINE);

    @Override
    public ArrayList<String> loadMedicineIds() {
        return  medicineDAO.loadMedicineIds();
    }

    @Override
    public boolean updateStock(ArrayList<MedicineDetails> medicineDetailList) {
        return medicineDAO.updateStock(medicineDetailList);
    }

    @Override
    public boolean deleteMedicine(String id) throws NotFoundException {
        if (!medicineDAO.deleteMedicine(id))throw new NotFoundException("Book not found");

        try {
            medicineDAO.deleteMedicine(id);
        }catch (ConstraintViolationException e){
            throw new InUseException("Book already in used");
        }
        return false;
    }

    @Override
    public String genarateMedicineId() {
       return medicineDAO.generateNextMeddicineId();
    }

    @Override
    public boolean addmedicine(MedicineDTO medicine) {
        return medicineDAO.save(Conveter.toMedicine(medicine));
    }

    @Override
    public ArrayList<MedicineDTO> getAllMedicine() {
        return (ArrayList<MedicineDTO>) medicineDAO.getAll().stream().map(medicine -> Conveter.tomedicineDTO(medicine)).
                collect(Collectors.toList());
    }
}
