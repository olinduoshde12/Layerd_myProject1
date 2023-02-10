package lk.ijse.medical.dao.custom.impl;

import lk.ijse.medical.dao.custom.PayMentDAO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.entity.Payment;
import lk.ijse.medical.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PayMentDAO {
    public  String generateNextPaymentId()  {
        try {
            ResultSet result = CrudUtil.execute("SELECT place_Order_id FROM PaymentDetails ORDER BY place_Order_id DESC LIMIT 1");
            if (result.next()) {
                return generateNextPaymentId(result.getString(1));
            }
            return generateNextPaymentId(result.getString(null));
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }

    public static String generateNextPaymentId (String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("P0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "P0" + id;
        }
        return "O001";

    }



    @Override
    public boolean save(Payment payment) {
            try {
            return CrudUtil.execute("INSERT INTO PaymentDetails VALUES(?,?,?,?,?,?,?,?)",
                    payment.getPl_id(),
                    payment.getA_id(),
                    payment.getDate(),
                    payment.getP_name(),
                    payment.getD_name(),
                    payment.getM_Total(),
                    payment.getA_Total(),
                    payment.getF_Total()

            );
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }

    public  ArrayList<Payment> getAll(){
        try {
           ResultSet rst = CrudUtil.execute("SELECT * FROM PaymentDetails");

            ArrayList<Payment>customerList=new ArrayList<>();

            while(rst.next()){
                Payment payment = new Payment(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),
                        rst.getString(5),rst.getDouble(6),rst.getDouble(7),rst.getDouble(8));
                customerList.add(payment);
            }
            return customerList;
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }

    }
}
