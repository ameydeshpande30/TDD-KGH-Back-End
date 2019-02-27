package tdd.kgh.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import tdd.kgh.DBConnection;
import tdd.kgh.models.jdbc.Department;
@Component
public class DepartmentOperations {
	/*
	 * add dept
	 * update dept
	 * delete dept
	 * show count staff
	 * import dept file
	 * show dept list
	 * */
	

	public boolean addDepartment(Department d)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("addDepartment");
		System.out.println(qry);
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		pstmt.setString(1, d.getName());
		pstmt.setString(2, d.getDesc());
		
		/*Statement stmt =DBConnection.con.createStatement();
		stmt.executeUpdate("insert into room (size, Price, name) values (1,2, 'eferf' );");
		*/
		int i = 0 ;
		i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean updateDeparmtent(Department d)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("updateDepartmentDetails");

		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setString(1, d.getName());
		pstmt.setString(2, d.getDesc());
		pstmt.setInt(3, d.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean deleteDepartment(Department d)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("deleteDepartment");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		pstmt.setInt(1, d.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public ArrayList<Department> showDepartmentList() throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("showDepartmentList");

		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		ResultSet rs = pstmt.executeQuery(qry);
		
		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();
		
		ArrayList<Department> departmentList = new ArrayList<Department>(rows);
		while(rs.next()){
				departmentList.add(getDepartmentObject(rs));
		}
		
		return departmentList;
	}
	
	public Department getDepartmentObject(ResultSet rs) throws Exception {
		Department d = new Department();
		d.setName(rs.getString("DName"));
		d.setId(rs.getInt("DID"));
		d.setDesc(rs.getString("DDesc"));
		return d;
	}

}
