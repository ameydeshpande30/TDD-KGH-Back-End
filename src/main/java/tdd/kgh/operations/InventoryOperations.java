package tdd.kgh.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import tdd.kgh.DBConnection;
import tdd.kgh.models.jdbc.Inventory;

public class InventoryOperations {
	/*
	 * add Inventory
	 * update Inventory
	 * delete Inventory
	 * import Inventory file
	 * show Inventory list
	 * */
	
	
	/*
showItemList = select * from items
showItemDetails = select * from items where IID = ?
updateItemDetails = update items set IName = ? , CID = ? , QTY = ? , isAvailable = ? , CP = ? , SP = ? where IID = ? 
deleteItem = delete from items where IID = ? 
addItem = insert into items (IName, CID, QTY, isAvailable, CP, SP) values ( ?, ?, ?, ?, ?, ?)

*/
	
	public boolean addInventory(Inventory i)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("addItem");
		System.out.println(qry);
	
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);

		pstmt.setString(1, i.getName());
		pstmt.setInt(2, i.getCId());
		pstmt.setInt(3, i.getQty());
		pstmt.setBoolean(4, i.getAvailability());
		pstmt.setInt(5, i.getCp());
		pstmt.setInt(6, i.getSp());
		/*Statement stmt =DBConnection.con.createStatement();
		stmt.executeUpdate("insert into room (size, Price, name) values (1,2, 'eferf' );");
		*/
		int ii =0;
		ii = pstmt.executeUpdate();
		
		if(ii==0) return false;
		return true;
	}
	
	public boolean updateInventory(Inventory i)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("updateItemDetails");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setString(1, i.getName());
		pstmt.setInt(2, i.getCId());
		pstmt.setInt(3, i.getQty());
		pstmt.setBoolean(4, i.getAvailability());
		pstmt.setInt(5, i.getCp());
		pstmt.setInt(6, i.getSp());
		pstmt.setInt(7, i.getId());
		int ii = pstmt.executeUpdate();
		
		if(ii==0) return false;
		return true;
	}
	
	public boolean deleteInventory(Inventory i)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("deleteItem");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setInt(1, i.getId());
		
		int ii = pstmt.executeUpdate();
		
		if(ii==0) return false;
		return true;
	}
	
	public ArrayList<Inventory> showInventoryList() throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("showItemList");

		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		ResultSet rs = pstmt.executeQuery(qry);
		
		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();
		
		ArrayList<Inventory> inventoryList = new ArrayList<Inventory>(rows);
		while(rs.next()){
			inventoryList.add(getInventoryObject(rs));
		}
		
		return inventoryList;
	}
	
	public Inventory getInventoryObject(ResultSet rs) throws Exception {

		/*(IName, CID, QTY, isAvailable, CP, SP*/
		Inventory i = new Inventory();
		i.setId(rs.getInt("IID"));
		i.setName(rs.getString("IName"));
		i.setCId(rs.getInt("CID"));
		i.setQty(rs.getInt("QTY"));
		i.setAvailability(rs.getBoolean("isAvailable"));
		i.setCp(rs.getInt("CP"));
		i.setSp(rs.getInt("SP"));
		return i;
	}
}
