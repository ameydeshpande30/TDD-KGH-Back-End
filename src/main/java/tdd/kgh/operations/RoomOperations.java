package tdd.kgh.operations;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import tdd.kgh.DBConnection;
import tdd.kgh.models.jdbc.*;;

public class RoomOperations {
	public boolean addRoom (Room r)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("addRoom");
		System.out.println(qry);
	
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);

		pstmt.setString(3, r.getName());
		pstmt.setInt(1, r.getSize());
		pstmt.setInt(2, r.getPrice());
		
		/*Statement stmt =DBConnection.con.createStatement();
		stmt.executeUpdate("insert into room (size, Price, name) values (1,2, 'eferf' );");
		*/
		int i =0;
		i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean updateRoom(Room r)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("updateRoomDetails");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setInt(1, r.getSize());
		pstmt.setInt(2, r.getPrice());
		pstmt.setString(3, r.getName());
		pstmt.setInt(4, r.getId());
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean deleteRoom (Room r)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("deleteRoom");
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setInt(1, r.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public ArrayList<Room> showRoomList() throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("showRoomList");

		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		ResultSet rs = pstmt.executeQuery(qry);
		
		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();
		
		ArrayList<Room> roomList = new ArrayList<Room>(rows);
		while(rs.next()){
				roomList.add(getRoomObject(rs));
		}
		
		return roomList;
	}
	
	public Room getRoomObject(ResultSet rs) throws Exception {

		Room r = new Room();
		r.setName(rs.getString("name"));
		r.setSize(rs.getInt("size"));
		r.setPrice(rs.getInt("price"));
		r.setId(rs.getInt("id"));
		return r;
	}
}
