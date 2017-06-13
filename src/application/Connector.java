package application;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created Ghavrilin Oleg  06.06.2017.
 */
public class Connector {

    private static Connection connection;

    private static final String URL ="jdbc:mysql://localhost:3306/greenapp";
    private static final String USER ="root";
    private static final String PASSWORD ="masterkey";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (Exception ex)
        {
            System.out.print(ex.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        Connector.connection = connection;
    }
}