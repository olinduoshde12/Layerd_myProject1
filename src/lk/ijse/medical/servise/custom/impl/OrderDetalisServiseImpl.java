package lk.ijse.medical.servise.custom.impl;

import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.MedicineDAO;
import lk.ijse.medical.dao.custom.OrderDetailDAO;
import lk.ijse.medical.dto.MedicineDetails;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.OrderDetailServise;

import java.util.ArrayList;

public class OrderDetalisServiseImpl implements OrderDetailServise {
    private final OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.ORDERDETAIL);
    @Override
    public boolean saveOrderDetails(ArrayList<MedicineDetails> medicineDetails) {
        return orderDetailDAO.saveOrderDetails(medicineDetails);
    }
}
