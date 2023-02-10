package lk.ijse.medical.servise.custom;

import lk.ijse.medical.dao.SuperDAO;
import lk.ijse.medical.dto.MedicineDetails;
import lk.ijse.medical.servise.SuperServise;

import java.util.ArrayList;

public interface OrderDetailServise extends SuperServise {

     boolean saveOrderDetails(ArrayList<MedicineDetails> medicineDetails);
}
