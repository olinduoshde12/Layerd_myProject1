package lk.ijse.medical.servise.custom.impl;

import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.OrderDAO;
import lk.ijse.medical.dto.OrderDTO;
import lk.ijse.medical.entity.Order;
import lk.ijse.medical.servise.custom.OrderServise;
import lk.ijse.medical.servise.util.Conveter;

import java.util.ArrayList;

public class OrderServiseImpl implements OrderServise {
    private final OrderDAO orderDAO= (OrderDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.ORDER);

    @Override
    public boolean save(OrderDTO order) {
        return orderDAO.save(Conveter.toOrder(order));
    }

    @Override
    public String generateNextOrderId() {
        return orderDAO.generateNextOrderId();
    }

    @Override
    public ArrayList<String> loadOrderIds() {
        return orderDAO.loadOrderIds();
    }

    @Override
    public ArrayList<Order> getAllOrder() {
        return orderDAO.getAll();
    }

    @Override
    public String generateNextPaymentId() {
        return orderDAO.generateNextPaymentId();
    }
}
