package lk.ijse.medical.dao.custom;

import lk.ijse.medical.dao.CrudDAO;
import lk.ijse.medical.dao.SuperDAO;
import lk.ijse.medical.dto.PaymentDTO;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.entity.Payment;

import java.util.ArrayList;

public interface PayMentDAO extends CrudDAO<Payment,String> {
    String generateNextPaymentId();
   // boolean addPayment(PaymentDTO payment);
   // ArrayList<PaymentDTO> getAllPayment();
}
