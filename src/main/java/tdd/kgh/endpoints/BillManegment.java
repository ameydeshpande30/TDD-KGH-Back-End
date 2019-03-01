package tdd.kgh.endpoints;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import tdd.kgh.models.jdbc.Order;
import tdd.kgh.operations.OrderOperations;

@RestController
@RequestMapping("/api")
public class BillManegment {
	@Autowired
	OrderOperations orderOperations;
	
	@GetMapping("/bill/{checkIN}/{checkOut}")
	public ArrayList<Order> findAll(@PathVariable String checkIN, @PathVariable String checkOut) {
		try {
			return orderOperations.getAllStatements(checkIN, checkOut);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/bill-list")
	public ArrayList<Order> getList() {
		try {
			return orderOperations.showOrderList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
