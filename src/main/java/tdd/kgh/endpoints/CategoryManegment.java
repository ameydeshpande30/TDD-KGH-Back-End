package tdd.kgh.endpoints;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdd.kgh.models.jdbc.Category;
import tdd.kgh.models.jdbc.Customer;
import tdd.kgh.models.jdbc.Inventory;
import tdd.kgh.operations.CategoryOperations;

@RestController
@RequestMapping("/api")
public class CategoryManegment {
	@Autowired
	CategoryOperations categoryOperations ;
	@GetMapping("/category")
	public ArrayList<Category> findAll() {
		try {
			return categoryOperations.showCategoryList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/category/add")
	public String addUpdate(@RequestBody Map<String, Object> payload) {
		System.out.println(payload.toString());
		int update = (int) payload.get("update");
		int id = Integer.parseInt((String) payload.get("id"));
		int count = Integer.parseInt((String) payload.get("count"));
		String name = (String) payload.get("name");
		
		if(update == 1) {
			try {
				
				categoryOperations.updateCategory(new Category(id, name, count));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				categoryOperations.addCategory(new Category(id, name, count));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	@DeleteMapping("/category/del/{id}")
	public boolean del(@PathVariable int id) {
		try {
			return categoryOperations.deleteCategory(new Category(id, "asd", 0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
