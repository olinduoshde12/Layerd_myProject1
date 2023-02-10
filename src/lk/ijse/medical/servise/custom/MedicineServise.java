package lk.ijse.medical.servise.custom;

import javassist.NotFoundException;
import lk.ijse.medical.dto.MedicineDTO;
import lk.ijse.medical.dto.MedicineDetails;
import lk.ijse.medical.entity.Medicine;
import lk.ijse.medical.servise.SuperServise;

import java.util.ArrayList;

public interface MedicineServise extends SuperServise {

    boolean addmedicine(MedicineDTO medicine);

    ArrayList<MedicineDTO> getAllMedicine();

    ArrayList<String> loadMedicineIds();

    boolean updateStock(ArrayList<MedicineDetails> medicineDetailList);

    boolean deleteMedicine(String id) throws NotFoundException;

    String genarateMedicineId();

}
