package lk.ijse.medical.servise.custom.impl;

import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.PayMentDAO;
import lk.ijse.medical.dto.PaymentDTO;
import lk.ijse.medical.entity.Payment;
import lk.ijse.medical.servise.custom.PayMentServise;
import lk.ijse.medical.servise.util.Conveter;

import java.util.ArrayList;

public class PaymentServiseImpl implements PayMentServise {
    private final PayMentDAO payMentDAO= (PayMentDAO) DaoFactory.getDaoFactory().getDao(DaoTypes.PAYMENT);
    @Override
    public String generateNextPaymentId() {
        return payMentDAO.generateNextPaymentId();
    }

    @Override
    public boolean addPayment(PaymentDTO payment) {
        return payMentDAO.save(Conveter.topayment(payment));
    }

    @Override
    public ArrayList<Payment> getAllPayment() {
        return payMentDAO.getAll();
    }
}
