package tdd.kgh.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.stereotype.Component;

import tdd.kgh.DBConnection;
import tdd.kgh.models.jdbc.Bill;
import tdd.kgh.models.jdbc.Order;
import tdd.kgh.models.jdbc.Order_Items;
@Component
public class OrderOperations {
	public boolean generateBill(Order o) throws Exception {
		if (DBConnection.getConnection() == null)
			DBConnection.connect();

		DBConnection.loadPropertiesFile();

		String qry = DBConnection.getProperties().getProperty("generateBill");
		System.out.println(qry);

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(qry);
		pstmt.setInt(1, o.getId());
		int i = pstmt.executeUpdate();

		if (i == 0)
			return false;
		return true;
	}

	public boolean addOrder(Order o) throws Exception {
		if (DBConnection.getConnection() == null)
			DBConnection.connect();

		DBConnection.loadPropertiesFile();

		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("addOrder");

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(qry);

		pstmt.setString(1, o.getCheckInDate());
		pstmt.setString(2, o.getCheckOutDate());
		pstmt.setInt(3, o.getRId());
		pstmt.setInt(4, o.getCId());
		pstmt.setInt(5, o.getAmount());

		int i = pstmt.executeUpdate();

		if (i == 0)
			return false;
		return true;
	}

	public boolean updateOrder(Order o) throws Exception {

		if (DBConnection.getConnection() == null)
			DBConnection.connect();

		DBConnection.loadPropertiesFile();
		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("updateOrder");

		// updateOrder = update orders set Check_in = ? , Check_out = ? , RID = ? , CID
		// = ? ,Total_Amount = ? where OID = ?

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(qry);

		pstmt.setString(1, o.getCheckInDate());
		pstmt.setString(2, o.getCheckOutDate());
		pstmt.setInt(3, o.getRId());
		pstmt.setInt(4, o.getCId());
		pstmt.setInt(5, o.getAmount());
		pstmt.setInt(6, o.getId());

		int i = pstmt.executeUpdate();

		if (i == 0)
			return false;
		return true;
	}

	public ArrayList<Order> showOrderList() throws Exception {

		if (DBConnection.getConnection() == null)
			DBConnection.connect();

		DBConnection.loadPropertiesFile();

		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("showOrderList");

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(qry);
		ResultSet rs = pstmt.executeQuery(qry);

		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();

		ArrayList<Order> orderList = new ArrayList<Order>(rows);
		while (rs.next()) {
			orderList.add(getOrderObject(rs));
		}

		return orderList;
	}

	public Order getOrderObject(ResultSet rs) throws Exception {

		// addOrder = insert into orders (Check_in, Check_out, RID, CID, Total_Amount)
		// values ( ? , ? , ? , ? , ? )

		Order o = new Order();
		o.setCheckInDate(rs.getString("Checdk_in_date"));
		o.setCheckOutDate(rs.getString("Check_out_date"));
		o.setRId(rs.getInt("RID"));
		o.setCId(rs.getInt("CID"));
		o.setAmount(rs.getInt("Total_Amount"));
		o.setId(rs.getInt("OID"));
		return o;
	}

	public ArrayList<Order_Items> getOrderItemDetails(Order o) throws Exception {

		if (DBConnection.getConnection() == null)
			DBConnection.connect();

		DBConnection.loadPropertiesFile();

		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("showItemDetailsforOrder");

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(qry);
		pstmt.setInt(1, o.getId());

		ResultSet rs = pstmt.executeQuery(qry);

		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();

		ArrayList<Order_Items> orderItemList = new ArrayList<Order_Items>(rows);
		while (rs.next()) {
			orderItemList.add(getOrderItemObject(rs));
		}

		return orderItemList;

		// showItemDetailsforOrder = select * from order_item where OID = ?
	}

	public Order_Items getOrderItemObject(ResultSet rs) throws Exception {

		// addOrder = insert into orders (Check_in, Check_out, RID, CID, Total_Amount)
		// values ( ? , ? , ? , ? , ? )
		// insertItemforOrder = insert into order_item values (OID , Item_id, Qty)
		// values (? , ? , ?)

		Order_Items oi = new Order_Items();
		oi.setId(rs.getInt("IID"));
		oi.setIId(rs.getInt("Item_id"));
		oi.setOId(rs.getInt("OID"));
		oi.setQty(rs.getInt("Qty"));
		return oi;
	}

	public boolean addItemToOrder(Order_Items oi) throws Exception {

		if (DBConnection.getConnection() == null)
			DBConnection.connect();

		DBConnection.loadPropertiesFile();
		// insertItemforOrder = insert into order_item values (OID , Item_id, Qty)
		// values (? , ? , ?)

		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("insertItemforOrder");

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(qry);

		pstmt.setInt(1, oi.getOId());
		pstmt.setInt(2, oi.getIId());
		pstmt.setInt(3, oi.getQty());

		int i = pstmt.executeUpdate();

		if (i == 0)
			return false;
		return true;
	}

	public ArrayList<Order> getAllStatements(String in, String out) throws Exception {

		if (DBConnection.getConnection() == null)
			DBConnection.connect();

		DBConnection.loadPropertiesFile();
		// insertItemforOrder = insert into order_item values (OID , Item_id, Qty)
		// values (? , ? , ?)

		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("showAllStatements");

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(qry);

		pstmt.setString(1, in);
		pstmt.setString(2, out);

		ResultSet rs = pstmt.executeQuery();

		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();

		ArrayList<Order> orderList = new ArrayList<Order>(rows);
		while (rs.next()) {
			orderList.add(getOrderObject(rs));
		}

		return orderList;
	}

	public Order getOrderDetails(Bill bill) throws Exception {

		if (DBConnection.getConnection() == null)
			DBConnection.connect();

		DBConnection.loadPropertiesFile();
		// insertItemforOrder = insert into order_item values (OID , Item_id, Qty)
		// values (? , ? , ?)

		Properties props = DBConnection.getProperties();
		String qry = props.getProperty("showBillDetails");

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(qry);

		pstmt.setInt(1, bill.getId());

		ResultSet rs = pstmt.executeQuery(qry);
		Order o = new Order();

		while (rs.next())
			o = getOrderObject(rs);
		return o;
	}	

}
