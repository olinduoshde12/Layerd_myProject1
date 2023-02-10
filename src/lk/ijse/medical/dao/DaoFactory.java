package lk.ijse.medical.dao;

import lk.ijse.medical.dao.custom.impl.*;
import lk.ijse.medical.db.DBconnection;

import java.sql.Connection;

public class DaoFactory {
    private static  DaoFactory daoFactory;


    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory(){
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDao(DaoTypes daoTypes) {
        switch (daoTypes){
            case APPOINMENT:
                return new AppoinmentDAOImpl();
            case DOCTOR:
                return new DoctorDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MEDICINE:
                return new MedicineDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case MEDICINEDETAILS:
                return new MedicineDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PLACEAPPOINMENT:
                return new PlaceAppoinmentDAOImpl();
            default:
                return null;
        }
    }
}
