package tdd.kgh.endpoints;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdd.kgh.dao.RoomDao;
import tdd.kgh.models.*;


@RestController
@RequestMapping("/api/room")
public class RoomManagement {

	private RoomDao roomdao;
	@Autowired
	public RoomManagement(RoomDao rd) {
		roomdao = rd;
	}
	@GetMapping("/getList")
	public List<Room> findAll() {
		return roomdao.findAll();
	}
	@GetMapping("/addRoom")
	public List<Room> add() {
		Room r = new Room("Test",20,30);
		roomdao.add(r);
		return roomdao.findAll();
	}

}
