package tdd.kgh.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.stereotype.Component;

import tdd.kgh.DBConnection;
import tdd.kgh.models.jdbc.Employee;
@Component
public class EmployeeOperations {
	

	/*
	 * add emp
	 * update emp
	 * show emp list
	 * delete emp
	 * import emp file
	 * */
	

/*showEmployeeList = "select * from employee;"
showEmployeeDetails = "select * from employee where EID = ?"
updateEmployeeDetails = "update employee set EName = ? , ENumber = ? , EUsername = ? , DID = ?, RID = ?, Date_of_join = ? , salary = ? "
deleteEmployee = "delete from employee where EID =  ? "
addEmployee = "insert into employee (EName, ENumber, EUsername, DID, RID, Date_of_join, salary, password) values (?, ?, ?, ?, ?, ?, ?, ?)"
*/
	
	public boolean addEmployee(Employee e)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("addEmployee");
		System.out.println(qry);
	
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);

		pstmt.setString(1, e.getName());
		pstmt.setString(3, e.getUsername());
		pstmt.setString(2, e.getContactNumber());
		pstmt.setInt(4, e.getDId());
		pstmt.setInt(5, e.getRId());
		pstmt.setString(6, e.getDOJ());
		pstmt.setInt(7, e.getSal());
		pstmt.setString(8, e.getPassword());
		
		System.out.println(pstmt);
		
		
		/*Statement stmt =DBConnection.con.createStatement();
		stmt.executeUpdate("insert into room (size, Price, name) values (1,2, 'eferf' );");
		*/
		
		int i =0;
		i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean updateEmployee(Employee e)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("updateEmployeeDetails");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setString(1, e.getName());
		pstmt.setString(3, e.getUsername());
		pstmt.setString(2, e.getContactNumber());
		pstmt.setInt(4, e.getDId());
		pstmt.setInt(5, e.getRId());
		pstmt.setString(6, e.getDOJ());
		pstmt.setInt(7, e.getSal());
		pstmt.setInt(8, e.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean deleteEmployee(Employee e)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("deleteEmployee");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setInt(1, e.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public ArrayList<Employee> showEmployeeList() throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("showEmployeeList");

		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		ResultSet rs = pstmt.executeQuery(qry);
		
		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();
		
		ArrayList<Employee> employeeList = new ArrayList<Employee>(rows);
		while(rs.next()){
			employeeList.add(getEmployeeObject(rs));
		}
		
		return employeeList;
	}
	
	public Employee getEmployeeObject(ResultSet rs) throws Exception {
		/*EName, ENumber, EUsername, DID, RID, Date_of_join, salary, password*/
		Employee e = new Employee();
		e.setId(rs.getInt("EID"));
		e.setName(rs.getString("EName"));
		e.setUsername(rs.getString("EUsername"));
		e.setPassword(rs.getString("EPassword"));
		e.setContactNumber(rs.getString("ENumber"));
		e.setDid(rs.getInt("DID"));
		e.setRId(rs.getInt("RID"));
		e.setDOJ(rs.getString("Date_of_join"));
		e.setSal(rs.getInt("salary"));
		return e;
	}

}
