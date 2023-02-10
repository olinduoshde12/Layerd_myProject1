package lk.ijse.medical.dao.custom;

import lk.ijse.medical.dao.CrudDAO;
import lk.ijse.medical.dto.MedicineDetails;
import lk.ijse.medical.entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineDAO extends CrudDAO<Medicine,String> {

    //boolean addCustomer(MedicineDTO medicine);

    //ArrayList<MedicineDTO> getAllMedicine();

    ArrayList<String> loadMedicineIds();

     boolean updateStock(ArrayList<MedicineDetails> medicineDetailList);

    boolean deleteMedicine(String id);

    String generateNextMeddicineId();
}
