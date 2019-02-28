package tdd.kgh.endpoints;



import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class Login {
	@GetMapping("/login")
	public HashMap<String, String> findAll(){
		 HashMap<String, String> map = new HashMap<>();
		    map.put("msg", "OK");
		   
		return map;
	}

}
