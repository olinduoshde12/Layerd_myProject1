package lk.ijse.medical.dao.custom;

import lk.ijse.medical.dao.SuperDAO;
import lk.ijse.medical.dto.MedicineDetails;

import java.util.ArrayList;

public interface OrderDetailDAO extends SuperDAO {

     boolean saveOrderDetails(ArrayList<MedicineDetails> medicineDetails);
}
