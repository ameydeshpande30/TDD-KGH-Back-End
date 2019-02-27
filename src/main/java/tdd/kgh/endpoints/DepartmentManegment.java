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

import tdd.kgh.models.jdbc.Department;
import tdd.kgh.operations.DepartmentOperations;

@RestController
@RequestMapping("/api")
public class DepartmentManegment {
	@Autowired
	DepartmentOperations departmentOperations;
	@GetMapping("/department")
	public ArrayList<Department> findAll() {
		try {
			return departmentOperations.showDepartmentList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/department/add")
	public String addUpdate(@RequestBody Map<String, Object> payload) {
		System.out.println(payload.toString());
		int update = (int) payload.get("update");
		int id = Integer.parseInt((String) payload.get("id"));
		String name = (String) payload.get("name");
		String desc = (String) payload.get("desc");
		if(update == 1) {
			try {
				
				departmentOperations.updateDeparmtent(new Department(id, name, desc));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				departmentOperations.addDepartment(new Department(id, name, desc));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	@DeleteMapping("/department/del/{id}")
	public boolean del(@PathVariable int id) {
		try {
			return departmentOperations.deleteDepartment(new Department(id, "name", "desc"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
