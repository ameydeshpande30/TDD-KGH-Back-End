package tdd.kgh.endpoints;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("")
public class Login {
	@GetMapping("/login")
	public String findAll(){
		return "hello";
	}

}
