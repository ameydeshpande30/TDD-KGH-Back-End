package tdd.kgh.operations;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import tdd.kgh.DBConnection;
import tdd.kgh.models.jdbc.*;;

public class RoomOperations {
	/*
	 * addRoom()
	 * updateRoom()
	 * deleteRoom()
	 * showRooms()
	 * importRoomFile()
	 * */
	
	boolean addRoom (Room r)throws Exception{
		if(DBConnection.con==null) 
			DBConnection.connect();
		DBConnection.loadPropertiesFile();
		//Properties props = DBConnection.props;
		String qry = DBConnection.props.getProperty("addRoom");
		System.out.println(qry);
		//Connection con = DBConnection.con; 
		PreparedStatement pstmt  = DBConnection.con.prepareStatement(qry);
		int a   = r.getSize();
		pstmt.setString(3, r.getName());
		pstmt.setInt(1, a);
		pstmt.setInt(2, r.getPrice());
		
		/*Statement stmt =DBConnection.con.createStatement();
		stmt.executeUpdate("insert into room (size, Price, name) values (1,2, 'eferf' );");
		*/
		int i =0;
		pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean updateRoom(Room r)throws Exception{
		if(DBConnection.con==null) DBConnection.connect();
		DBConnection.loadPropertiesFile();
		Properties props = DBConnection.props;
		String qry = props.getProperty("updateRoomDetails");
		Connection con = DBConnection.con; 
		PreparedStatement pstmt  = con.prepareStatement(qry);
		pstmt.setInt(1, r.getSize());
		pstmt.setInt(2, r.getPrice());
		pstmt.setString(3, r.getName());
		pstmt.setInt(4, r.getId());
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	boolean deleteRoom (Room r)throws Exception{
		if(DBConnection.con==null) DBConnection.connect();
		DBConnection.loadPropertiesFile();
		Properties props = DBConnection.props;
		String qry = props.getProperty("deleteRoom");
		Connection con = DBConnection.con; 
		PreparedStatement pstmt  = con.prepareStatement(qry);
		pstmt.setInt(1, r.getId());
		
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public ArrayList<Room> showCategoryList() throws Exception{
		
		if(DBConnection.con==null) DBConnection.connect();
		DBConnection.loadPropertiesFile();
		Properties props = DBConnection.props;
		String qry = props.getProperty("showRoomList");
		Connection con = DBConnection.con;
		PreparedStatement pstmt  = con.prepareStatement(qry);
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
		r.setId(rs.getInt("id"));
		r.setName(rs.getString("name"));
		r.setSize(rs.getInt("size"));
		r.setPrice(rs.getInt("price"));
		
		return r;
	}
}
