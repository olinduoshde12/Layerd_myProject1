package lk.ijse.medical.servise.custom;

import lk.ijse.medical.dto.PlaceAppoinmentDTO;
import lk.ijse.medical.servise.SuperServise;

import java.sql.SQLException;

public interface PlaceAppoinmentServise extends SuperServise {
    boolean placeOrder(PlaceAppoinmentDTO placeAppoinment) throws SQLException, ClassNotFoundException;
}
