package tdd.kgh.dao;

import java.util.List;

import tdd.kgh.models.Room;

public interface RoomDao {
	public List<Room> findAll();
	public void add(Room room);
}
