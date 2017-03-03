package database;
import java.sql.*;
import javax.swing.*;
public class DBConnected {
	
  Connection conn =null;
  public static void main(String[] args) {
	  
	  dbConnector();
	  
  }
  public static Connection dbConnector(){
	 try{
	  String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/project?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String pass ="";
		Class.forName(driver);
		
		Connection con = DriverManager.getConnection(url,user,pass);
	    return con;
		
	}catch(Exception e){
		System.out.println(e);
	}
	return null;
}
  }
	

