import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
 
/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class Main {
 
    public static void main(String[] args) {
 
        Connection conn = null;
        Scanner scanner = new Scanner(System.in);
 
        try {
            System.out.println("Enter JDBC URL:");
            String dbURL = scanner.nextLine();
            //scanner.nextLine(); //This is needed to pick up the new line
            System.out.println("Enter username:");
            String user=scanner.nextLine();
            System.out.println("Enter password:");
            String pass=scanner.nextLine();
            /*String dbURL = "jdbc:sqlserver://localhost\\sqlexpress";
            String user = "sa";
            String pass = "secret";*/
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}