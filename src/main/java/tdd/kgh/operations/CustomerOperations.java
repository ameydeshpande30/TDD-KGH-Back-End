package tdd.kgh.operations;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.stereotype.Component;

import tdd.kgh.DBConnection;
import tdd.kgh.models.jdbc.*;;
@Component
public class CustomerOperations {

	/*
	 * add cust
	 * update customer
	 * delete customer
	 * show customer list
	 * import cust file
	 * */
	
	/*
	 * 

showCustomerList = "select * from customer;"
showCustomerDetails = "select * from customer where CID = ?"
updateCustomerDetails = "update customer set Name = ? , Address = ? , Phone = ? , Email = ? , AADHAR = ? , IDPROOF = ? where CID = ?"
deleteCustomer = "delete from customer where CID = ? "
addCustomer  = "insert into Customer (Name, Address, Phone, Email, AADHAR, IDPROOF) values (?, ?, ?, ?, ?, ?)"

	 * */

	public boolean addCustomer(Customer c)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("addCustomer");
		System.out.println(qry);
	
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);

		pstmt.setString(1, c.getName());
		pstmt.setString(2, c.getAddress());
		pstmt.setString(3, c.getContactNumber());
		pstmt.setString(4, c.getEmail());
		pstmt.setString(5, c.getAadhar());
		pstmt.setString(6, c.getIDProof());
		
		/*Statement stmt =DBConnection.con.createStatement();
		stmt.executeUpdate("insert into room (size, Price, name) values (1,2, 'eferf' );");
		*/
		
		int i =0;
		i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean updateCustomer(Customer c)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("updateCustomerDetails");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setString(1, c.getName());
		pstmt.setString(2, c.getAddress());
		pstmt.setString(3, c.getContactNumber());
		pstmt.setString(4, c.getEmail());
		pstmt.setString(5, c.getAadhar());
		pstmt.setString(6, c.getIDProof());
		pstmt.setInt(7, c.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean deleteCustomer (Customer c)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("deleteCustomer");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setInt(1, c.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public ArrayList<Customer> showCustomerList() throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("showCustomerList");

		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		ResultSet rs = pstmt.executeQuery(qry);
		
		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();
		
		ArrayList<Customer> customerList = new ArrayList<Customer>(rows);
		while(rs.next()){
				customerList.add(getCustomerObject(rs));
		}
		
		return customerList;
	}
	
	public Customer getCustomerObject(ResultSet rs) throws Exception {
		Customer c = new Customer();
		c.setName(rs.getString("Name"));
		c.setIDProof(rs.getString("IDPROOF"));
		c.setAadhar(rs.getString("AADHAR"));
		c.setAddress(rs.getString("Address"));
		c.setId(rs.getInt("CID"));
		c.setEmail(rs.getString("Email"));
		c.setContactNumber(rs.getString("Phone"));
		return c;
	}
	
}
