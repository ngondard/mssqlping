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

        try {
           
            Scanner scanner = new Scanner(System.in);            
            System.out.println("Enter hostname:");
            String server = scanner.nextLine();
            System.out.println("Enter port:");
            int port = scanner.nextInt();
            scanner.nextLine(); //This is needed to pick up the new line                       
            System.out.println("Enter db name:");
            String dbName = scanner.nextLine();
            System.out.println("Enter user name:");
            String user=scanner.nextLine();
            System.out.println("Enter password:");
            String pass=scanner.nextLine();
            scanner.close();
            /* MS driver connection URL            

            * String user = "sa";
            * String pass = "secret";
            * String connectionUrl = "jdbc:sqlserver://" + server + ":" + port +";databaseName=" 
            * + dbName + ";user=" + user + ";password=" + pass;
            conn = DriverManager.getConnection(connectionUrl);
            */
            String dbURL = "jdbc:jtds:sqlserver://" + server + ":" + port + "/" + dbName ;
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