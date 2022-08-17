package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.ResourceBundle;

public class MyDB {
	public static Connection con;
	public static Connection getCon(){
	try {
		//Class.forName("oracle.jdbc.OracleDriver");
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//        ResourceBundle rb = ResourceBundle.getBundle("oracle");
		Class.forName ("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "db_user";
        String password = "db_user";
//	Class.forName("com.mysql.jdbc.Driver");
//	con =  DriverManager.getConnection(" jdbc:oracle:thin:@localhost:1521:xe", "db_user", "db_user");
	
        con = DriverManager.getConnection(url, username, password);}
        catch (Exception e) {
	        System.out.println(e.toString() + "\n\n" + con);
	    }
	return con;
	}
}
