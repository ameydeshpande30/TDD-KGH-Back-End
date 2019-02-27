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

import tdd.kgh.models.jdbc.Inventory;
import tdd.kgh.models.jdbc.Room;
import tdd.kgh.operations.InventoryOperations;

@RestController
@RequestMapping("/api")
public class InventoryManegment {
	@Autowired
	InventoryOperations inventoryOperations ;
	@GetMapping("/inventory")
	public ArrayList<Inventory> findAll() {
		try {
			return inventoryOperations.showInventoryList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //remove e.log trace
		}
		return null;
	}
	@PostMapping("/inventory/add")
	public String addUpdate(@RequestBody Map<String, Object> payload) {
		System.out.println(payload.get("update"));
		int update = (int) payload.get("update");
		int id = Integer.parseInt((String) payload.get("id"));
		boolean available = Boolean.parseBoolean((String) payload.get("available"));
		int cp = Integer.parseInt((String) payload.get("cp"));
		int sp = Integer.parseInt((String) payload.get("sp"));
		int cid = Integer.parseInt((String) payload.get("cid"));
		int qty = Integer.parseInt((String) payload.get("qty"));
		String name = (String) payload.get("name");
		
		if(update == 1) {
			try {
				
				inventoryOperations.updateInventory(new Inventory(id, name, qty, available, cp, sp, cid));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				inventoryOperations.addInventory(new Inventory(id, name, qty, available, cp, sp, cid));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	@DeleteMapping("/inventory/del/{id}")
	public boolean del(@PathVariable int id) {
		try {
			return inventoryOperations.deleteInventory(new Inventory(id, "name", 10, true, 200, 400, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

}
