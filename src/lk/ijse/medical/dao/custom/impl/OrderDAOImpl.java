package lk.ijse.medical.dao.custom.impl;

import lk.ijse.medical.dao.custom.OrderDAO;
import lk.ijse.medical.dto.OrderDTO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.entity.Order;
import lk.ijse.medical.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    public  String generateNextOrderId(){
        try {
           ResultSet result = CrudUtil.execute("SELECT o_id FROM Order1 ORDER BY o_id DESC LIMIT 1");
            if (result.next()) {
                return generateNextOrderId(result.getString(1));
            }
            return generateNextOrderId(result.getString(null));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }

    public static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "D0" + id;
        }
        return "O001";

    }
    public  ArrayList<String> loadOrderIds() {

        try {
           ResultSet result = CrudUtil.execute("SELECT * FROM Order1 ORDER BY o_id DESC LIMIT 1");
            ArrayList<String> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(result.getString(1));
            }
            return idList;
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }

    @Override
    public boolean save(Order order) {
        try {
            return CrudUtil.execute("INSERT INTO Order1 VALUES(?, ?, ?, ?)", order.getO_Id(), order.getDate(), order.getAppoinment_id(),order.getTotal());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }



    public  ArrayList<Order> getAll(){
        try {
           ResultSet rst = CrudUtil.execute("SELECT * FROM Order1");
            ArrayList<Order>customerList=new ArrayList<>();

            while(rst.next()){
                Order order = new Order(rst.getString(1
                ), rst.getString(2), rst.getString(3), rst.getDouble(4));
                System.out.println(rst.getString(1));

                customerList.add(order);

            }
            for (Order o:customerList
            ) {
                System.out.println(o.getO_Id());
            }
            return customerList;
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
    public  String generateNextPaymentId(){
        try {
          ResultSet  result = CrudUtil.execute("SELECT place_Order_id FROM PaymentDetails ORDER BY place_Order_id DESC LIMIT 1");

            if (result.next()) {
                return generateNextOrderId(result.getString(1));
            }
            return generateNextOrderId(result.getString(null));
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }

    private static String generateNextPaymentId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("P00");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "P00" + id;
        }
        return "O001";

    }
}
