import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "SYSTEM";
    private static final String PASSWORD = "YOUR_PASSWORD_HERE";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully!");
            return con;
        } catch (Exception e) {
            System.out.println("not connected: ");
            e.printStackTrace();
            return null;
        }
    }
}