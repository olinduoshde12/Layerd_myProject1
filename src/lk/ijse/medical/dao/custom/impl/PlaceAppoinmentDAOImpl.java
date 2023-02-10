package lk.ijse.medical.dao.custom.impl;


import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.*;
import lk.ijse.medical.db.DBconnection;
import lk.ijse.medical.dto.OrderDTO;
import lk.ijse.medical.dto.PlaceAppoinmentDTO;
import lk.ijse.medical.entity.Order;

import java.sql.SQLException;

public class PlaceAppoinmentDAOImpl implements PlaceAppoinmentDAO {

    private final OrderDAO orderDAO= (OrderDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.ORDER);
    private final MedicineDAO medicineDAO= (MedicineDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.MEDICINE);
    private final OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.ORDERDETAIL);

    public  boolean placeOrder(PlaceAppoinmentDTO placeAppoinment) throws SQLException, ClassNotFoundException {
        try {
            DBconnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = orderDAO.save(new Order(placeAppoinment.getO_id(),placeAppoinment.getDate(),placeAppoinment.getAppoinment_id(),
                    placeAppoinment.getTotal()));
            System.out.println(isOrderAdded);
            if (isOrderAdded) {
                //System.out.println(isOrderAdded);
                boolean isUpdated = medicineDAO.updateStock(placeAppoinment.getOrderDetailList());
                System.out.println(isUpdated);
                if (isUpdated) {
                    boolean isOrderDetailAdded = orderDetailDAO.saveOrderDetails(placeAppoinment.getOrderDetailList());
                    System.out.println(isOrderDetailAdded);
                    if (isOrderDetailAdded) {
                        DBconnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBconnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBconnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
