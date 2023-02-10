package lk.ijse.medical.dao.util;

import lk.ijse.medical.db.DBconnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static PreparedStatement getPreparedStatement(String sql, Object[] args) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBconnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            stm.setObject(i+1,args[i]);
        }
        return stm;
    }

    public static ResultSet executeQuery(String sql, Object ... args) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBUtil.getPreparedStatement(sql, args);
        return stm.executeQuery();

    }

    public static boolean executeUpdate(String sql , Object ... args) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBUtil.getPreparedStatement(sql, args);
        return stm.executeUpdate()>0;
    }
}
