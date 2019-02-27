package tdd.kgh.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.stereotype.Component;

import tdd.kgh.models.jdbc.Employee;
import tdd.kgh.models.jdbc.Role;

import tdd.kgh.DBConnection;
@Component
public class RolesOperations {
	/*
	 * show count
	 * add role
	 * update role
	 * delete role
	 * import role file
	 * show role list

	 **/


	public boolean addRole(Role r)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("addRole");
		System.out.println(qry);
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		pstmt.setString(1, r.getName());
		pstmt.setString(2, r.getDesc());
		
		/*Statement stmt =DBConnection.con.createStatement();
		stmt.executeUpdate("insert into room (size, Price, name) values (1,2, 'eferf' );");
		*/
		int i = 0 ;
		i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean updateRole(Role r)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("updateRoleDetails");

		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setString(1, r.getName());
		pstmt.setString(2, r.getDesc());
		pstmt.setInt(3, r.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean deleteRole(Role r)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("deleteRole");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		pstmt.setInt(1, r.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public ArrayList<Role> showRoleList() throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("showRoleList");

		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		ResultSet rs = pstmt.executeQuery(qry);
		
		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();
		
		ArrayList<Role> roleList = new ArrayList<Role>(rows);
		while(rs.next()){
			roleList.add(getRoleObject(rs));
		}
		
		return roleList;
	}
	
	public Role getRoleObject(ResultSet rs) throws Exception {
		Role r = new Role();
		r.setName(rs.getString("RName"));
		r.setId(rs.getInt("RID"));
		r.setDesc(rs.getString("RDesc"));
		return r;
	}

}
