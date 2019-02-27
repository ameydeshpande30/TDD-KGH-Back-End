package tdd.kgh.endpoints;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdd.kgh.operations.*;
import tdd.kgh.models.jdbc.Customer;
import tdd.kgh.models.jdbc.Room;

@RestController
@RequestMapping("/api/customer")
public class CustomerManagement {
	@Autowired
	CustomerOperations CustomerOperations ;
	@GetMapping("/getList")
	public ArrayList<Customer> findAll() {
		try {
			
			return CustomerOperations.showCustomerList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/del/{id}")
	public boolean delRoom(@PathVariable int id) {
		try {
			return CustomerOperations.deleteCustomer(new Customer(id, "name", "address", "7588758032", "ameyd30@gmail.com", "aadhar",
					"idproof"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@PostMapping("/addUpdate")
	public String addUpdate(@RequestBody Map<String, Object> payload) {
		
		int update = (int) payload.get("update");
		int id = Integer.parseInt((String) payload.get("id"));
		String name = (String) payload.get("name");
		String address = (String) payload.get("address");
		String contactNumber = (String) payload.get("contactNumber");
		String email = (String) payload.get("email");
		String aadhar = (String) payload.get("aadhar");
		String idproof = (String) payload.get("idproof");
		
		
		if(update == 1) {
			try {
				CustomerOperations.updateCustomer(new Customer(id, name, address, contactNumber, email, aadhar, idproof));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				CustomerOperations.addCustomer(new Customer(id, name, address, contactNumber, email, aadhar, idproof));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
