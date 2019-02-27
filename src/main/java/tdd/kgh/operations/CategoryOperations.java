package tdd.kgh.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import tdd.kgh.DBConnection;
import tdd.kgh.models.jdbc.Category;
@Component
public class CategoryOperations {
	/*
	 * add category
	 * update category
	 * delete category
	 * show count
	 * show category list
	 * import category file
	 * */
		

	public boolean addCategory(Category c)throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("addCategory");
		System.out.println(qry);
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		pstmt.setString(1, c.getName());
		
		/*Statement stmt =DBConnection.con.createStatement();
		stmt.executeUpdate("insert into room (size, Price, name) values (1,2, 'eferf' );");
		*/
		int i = 0 ;
		i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean updateCategory(Category c)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("updateCategoryDetails");
		System.out.println(qry);
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setString(1, c.getName());
		pstmt.setInt(2, c.getId());
		System.out.println(pstmt);
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public boolean deleteCategory(Category c)throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("deleteCategory");
		System.out.println(qry);
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		pstmt.setInt(1, c.getId());
		System.out.println(pstmt);
		int i = pstmt.executeUpdate();
		
		if(i==0) return false;
		return true;
	}
	
	public ArrayList<Category> showCategoryList() throws Exception{
		
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("showCategoryList");
		System.out.println(qry);
		
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		ResultSet rs = pstmt.executeQuery(qry);
		
		rs.last();
		int rows = rs.getRow();
		rs.beforeFirst();
		
		ArrayList<Category> categoryList = new ArrayList<Category>(rows);
		while(rs.next()){
				categoryList.add(getCategoryObject(rs));
		}
		
		/*Iterator<Category> iterator = categoryList.iterator();
		 
		 // while loop
		 while (iterator.hasNext()) {
			 Category o = iterator.next();
		 }
		*/
		return categoryList;
	}
	
	public Category getCategoryObject(ResultSet rs) throws Exception {
		Category c = new Category();
		c.setName(rs.getString("Name"));
		c.setId(rs.getInt("CID"));
		//c.setCount(getItemCount(c.getId()));
		
		return c;
	}
	
	public int getItemCount(int a) throws Exception{
		if(DBConnection.getConnection()==null) 
			DBConnection.connect();
		
		DBConnection.loadPropertiesFile();
		
		String qry = DBConnection.getProperties().getProperty("getItemCountforCategory");
		System.out.println(qry);
		PreparedStatement pstmt  = DBConnection.getConnection().prepareStatement(qry);
		
		pstmt.setInt(1,a);
		System.out.println(pstmt);
		
		//Statement stmt = DBConnection.getConnection().createStatement();
		//ResultSet rs = stmt.executeQuery("select count(*) from items where CID = 1; ");
		ResultSet rs = pstmt.executeQuery(qry);
		
		rs.next();
		return rs.getInt("count(*)");
	}

}
