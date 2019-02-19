package tdd.kgh.endpoints;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tdd.kgh.dao.RoomDao;
import tdd.kgh.models.jdbc.*;
import tdd.kgh.operations.*;

@RestController
@RequestMapping("/api/room")
public class RoomManagement {
	RoomOperations roomOperations = new RoomOperations();
	private RoomDao roomdao;
	@Autowired
	public RoomManagement(RoomDao rd) {
		roomdao = rd;
	}
	@GetMapping("/getList")
	public ArrayList<Room> findAll() {
		try {
			return roomOperations.showRoomList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //remove e.log trace
		}
		return null;
	}
	@GetMapping("/delRoom/{id}")
	public boolean delRoom(@PathVariable int id) {
		try {
			return roomOperations.deleteRoom(new Room(id,30,20,"name"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@PostMapping("/addUpdate")
	public String addUpdate(@RequestBody Map<String, Object> payload) {
		System.out.println(payload.get("update"));
		int update = (int) payload.get("update");
		int id = Integer.parseInt((String) payload.get("id"));
		int size = Integer.parseInt((String) payload.get("size"));
		int price = Integer.parseInt((String) payload.get("price"));
		String name = (String) payload.get("name");
		
		if(update == 1) {
			try {
				roomOperations.updateRoom(new Room(id, size, price, name));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				roomOperations.addRoom(new Room(id, size, price, name));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
//	@GetMapping("/addRoom")
//	public List<Room> add() {
//		Room r = new Room("Test",20,30);
//		roomdao.add(r);
//		return roomdao.findAll();
//	}

}
