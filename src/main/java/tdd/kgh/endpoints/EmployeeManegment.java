package tdd.kgh.endpoints;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdd.kgh.models.jdbc.Employee;
import tdd.kgh.models.jdbc.Inventory;
import tdd.kgh.operations.EmployeeOperations;

@RestController
@RequestMapping("/api")
public class EmployeeManegment {
	EmployeeOperations employeeOperations = new EmployeeOperations();
	@GetMapping("/employee")
	public ArrayList<Employee> findAll() {
		try {
			return employeeOperations.showEmployeeList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //remove e.log trace
		}
		return null;
	}
	@PostMapping("/employee/add")
	public String addUpdate(@RequestBody Map<String, Object> payload) {
		System.out.println(payload.get("update"));
		int update = (int) payload.get("update");
		int id = Integer.parseInt((String) payload.get("id"));
		String name = (String) payload.get("name");
		String contactNumber = (String) payload.get("contactNumber");
		String username = (String) payload.get("username");
		String dOJ = (String) payload.get("dOJ");
		int sal = (int) payload.get("sal");
		int dId = (int) payload.get("dId");
		int rId = (int) payload.get("rId");
		String password = (String) payload.get("password");
		
		if(update == 1) {
			try {
				
				employeeOperations.updateEmployee(new Employee(id, name, contactNumber, username, dOJ, sal,
						dId, rId, password));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				employeeOperations.addEmployee(new Employee(id, name, contactNumber, username, dOJ, sal,
						dId, rId, password));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	@DeleteMapping("/employee/del/{id}")
	public boolean del(@PathVariable int id) {
		try {
			return employeeOperations.deleteEmployee(new Employee(id, "name", "75887580332", "username", "dOJ", 1500,
					1, 1, "password"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
