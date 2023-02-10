package lk.ijse.medical.dao.custom;

import lk.ijse.medical.dao.SuperDAO;
import lk.ijse.medical.dto.PlaceAppoinmentDTO;

import java.sql.SQLException;

public interface PlaceAppoinmentDAO extends SuperDAO {
    boolean placeOrder(PlaceAppoinmentDTO placeAppoinment) throws SQLException, ClassNotFoundException;
}
