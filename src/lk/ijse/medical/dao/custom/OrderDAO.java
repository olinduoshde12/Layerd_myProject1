package lk.ijse.medical.dao.custom;

import lk.ijse.medical.dao.CrudDAO;
import lk.ijse.medical.dao.SuperDAO;
import lk.ijse.medical.dto.OrderDTO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.entity.Order;

import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Order,String> {

   // boolean save(OrderDTO order);

    String generateNextOrderId();

    ArrayList<String> loadOrderIds();

   // ArrayList<OrderDTO> getAllOrder();

    //boolean save(OrderDTO order);

    String generateNextPaymentId();
}

