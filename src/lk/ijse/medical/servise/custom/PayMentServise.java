package lk.ijse.medical.servise.custom;

import lk.ijse.medical.dto.PaymentDTO;
import lk.ijse.medical.entity.Payment;
import lk.ijse.medical.servise.SuperServise;

import java.util.ArrayList;

public interface PayMentServise extends SuperServise {
    String generateNextPaymentId();

    boolean addPayment(PaymentDTO payment);

    ArrayList<Payment> getAllPayment();
}
