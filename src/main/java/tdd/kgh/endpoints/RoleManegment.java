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
import tdd.kgh.models.jdbc.Role;
import tdd.kgh.operations.RolesOperations;
@RestController
@RequestMapping("/api")
public class RoleManegment {
	@Autowired
	RolesOperations rolesOperations;
	@GetMapping("/roles")
	public ArrayList<Role> findAll() {
		try {
			return rolesOperations.showRoleList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@PostMapping("/roles/add")
	public String addUpdate(@RequestBody Map<String, Object> payload) {
		System.out.println(payload.toString());
		int update = (int) payload.get("update");
		int id = Integer.parseInt((String) payload.get("id"));
		String name = (String) payload.get("name");
		String desc = (String) payload.get("desc");
		if(update == 1) {
			try {
				
				rolesOperations.updateRole(new Role(id, name, desc));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				rolesOperations.addRole(new Role(id, name, desc));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	@DeleteMapping("/roles/del/{id}")
	public boolean del(@PathVariable int id) {
		try {
			return rolesOperations.deleteRole(new Role(id, "name", "desc"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
