package tdd.kgh;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public final class DBConnection {
	static Statement stmt=null;
	static Connection con=null;
	static ClassLoader loader;
	static  Properties props;
		
	public static Connection getConnection(){
		return con;
	}
	
	public static Properties getProperties() {
		return props;
	}
	
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/KondwaGuestHouse","bunksheet", "sheetbunk123");
		}
		catch(Exception e){
			System.out.print(e);
		}
	}
	
	public static void connect()throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/KondwaGuestHouse","bunksheet", "sheetbunk123");
		}
		catch(Exception e){
			System.out.print(e);
		}
	}
	
	public static void closeConnection() throws Exception {
		try {
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void loadPropertiesFile(){
		
		try{
			String resourceName = "db.properties"; // could also be a constant
			loader = Thread.currentThread().getContextClassLoader();
			props = new Properties();
			InputStream resourceStream = loader.getResourceAsStream(resourceName);
			props.load(resourceStream);
		}
		catch(Exception e) {
				e.printStackTrace();
		}
	}
}
