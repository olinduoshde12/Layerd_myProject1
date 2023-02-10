package lk.ijse.medical.servise;

import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.SuperDAO;
import lk.ijse.medical.dao.custom.impl.*;
import lk.ijse.medical.servise.custom.impl.*;

public class ServiseFactory {
    private static ServiseFactory serviseFactory;


    private ServiseFactory() {
    }

    public static ServiseFactory getServiseFactory(){
        if (serviseFactory == null) {
            serviseFactory = new ServiseFactory();
        }
        return serviseFactory;
    }

    public SuperServise getServise(ServiseTypes serviseTypes) {
        switch (serviseTypes){
            case APPOINMENT:
                return new AppoinmentServiseImpl();
            case DOCTOR:
                return new DoctorServiseImpl();
            case EMPLOYEE:
                return new EmployeeServiseImpl();
            case MEDICINE:
                return new MedicineServiseImpl();
            case ORDER:
                return new OrderServiseImpl();
            case ORDERDETAIL:
                return new OrderDetalisServiseImpl();
            case PAYMENT:
                return new PaymentServiseImpl();
            case PLACEAPPOINMENT:
                return new PlaceAppoinmentServiseImpl();
            default:
                return null;
        }
    }
}
