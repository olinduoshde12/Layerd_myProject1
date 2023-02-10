package lk.ijse.medical.dao.custom.impl;

import lk.ijse.medical.dao.custom.OrderDetailDAO;
import lk.ijse.medical.dto.MedicineDetails;
import lk.ijse.medical.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    public  boolean saveOrderDetails(ArrayList<MedicineDetails> medicineDetails) {
        for (MedicineDetails  medicineDetails1 : medicineDetails) {
            if (!save(medicineDetails1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean save(MedicineDetails medicineDetails){
        try {
            return CrudUtil.execute("INSERT INTO OrderDetails VALUES(?, ?, ?, ?)", medicineDetails.getM_id(), medicineDetails.getO_id(), medicineDetails.getUnit_price(),
                    medicineDetails.getQty());
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
}
