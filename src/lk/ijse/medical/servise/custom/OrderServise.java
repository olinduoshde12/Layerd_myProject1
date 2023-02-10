package lk.ijse.medical.servise.custom;

import lk.ijse.medical.dto.OrderDTO;
import lk.ijse.medical.entity.Order;
import lk.ijse.medical.servise.SuperServise;

import java.util.ArrayList;

public interface OrderServise extends SuperServise {

    boolean save(OrderDTO order);

    String generateNextOrderId();

    ArrayList<String> loadOrderIds();

    ArrayList<Order> getAllOrder();

    String generateNextPaymentId();
}

