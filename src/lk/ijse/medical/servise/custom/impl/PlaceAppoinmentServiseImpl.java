package lk.ijse.medical.servise.custom.impl;

import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.dao.custom.OrderDAO;
import lk.ijse.medical.dao.custom.PlaceAppoinmentDAO;
import lk.ijse.medical.dto.PlaceAppoinmentDTO;
import lk.ijse.medical.servise.custom.PlaceAppoinmentServise;

import java.sql.SQLException;

public class PlaceAppoinmentServiseImpl implements PlaceAppoinmentServise {
    private final PlaceAppoinmentDAO placeAppoinmentDAO= (PlaceAppoinmentDAO) DaoFactory.
            getDaoFactory().getDao(DaoTypes.PLACEAPPOINMENT);
    @Override
    public boolean placeOrder(PlaceAppoinmentDTO placeAppoinment) throws SQLException, ClassNotFoundException {
        return placeAppoinmentDAO.placeOrder(placeAppoinment);


    }

}
