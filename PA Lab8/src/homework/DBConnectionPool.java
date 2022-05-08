package homework;
import com.mchange.v2.c3p0.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionPool {

    private static ComboPooledDataSource cpds = null;

    private DBConnectionPool(){
    }

    public static Connection getConnection(){
        if (cpds == null){
            cpds = new ComboPooledDataSource();
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/pa_db");
            cpds.setUser("root");
            cpds.setPassword("toor");

            //cpds.set
            cpds.setMinPoolSize(3);
            cpds.setMaxPoolSize(100);
            cpds.setAcquireIncrement(5);
        }
        try {
            Connection connection = cpds.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void closeConnection(){
        cpds.close();
    }
}
