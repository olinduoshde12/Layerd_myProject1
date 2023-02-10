package lk.ijse.medical.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private Connection connection;
    private static DBconnection dBConnection;

    private DBconnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection= DriverManager.getConnection("jdbc:mysql://localhost/medical_center","root","1234");
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException("Failed to load the database");
        }

    }

    public static DBconnection getInstance(){
        if (dBConnection == null) {
            dBConnection = new DBconnection();
        }
        return dBConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
