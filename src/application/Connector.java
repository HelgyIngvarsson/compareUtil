package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created Ghavrilin Oleg  06.06.2017.
 */
public class Connector {

    private Connection connection;

    private static final String URL ="jdbc:mysql://localhost:3306/greenapp";

    public Connector(String user, String password) throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URL, user, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}