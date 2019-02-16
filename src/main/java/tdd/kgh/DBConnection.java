package tdd.kgh;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public final class DBConnection {
	public static Statement stmt=null;
	public static Connection con=null;
	public static int connFlag=0;
	public static ClassLoader loader;
	public static  Properties props;
	//public InputStream resourceStream;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/KondwaGuestHouse","bunksheet", "sheetbunk123");
			stmt = con.createStatement();
			connFlag=1;
		}
		catch(Exception e){
			System.out.print(e);
		}
	}
	
	public static void connect()throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/KondwaGuestHouse","bunksheet", "sheetbunk123");
			stmt = con.createStatement();
			connFlag=1;
		}
		catch(Exception e){
			System.out.print(e);
		}
	}
	
	public static void closeConnection() throws Exception {
		try {
			stmt.close();
			con.close();
			connFlag=0;
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
			//String qry = props.getProperty("showCategoryList");
		}
		catch(Exception e) {
			System.out.println("edvervv\nvrtbr5\n");
				e.printStackTrace();
		}
	}
}
