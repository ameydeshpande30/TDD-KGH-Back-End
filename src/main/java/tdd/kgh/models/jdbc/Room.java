package tdd.kgh.models.jdbc;

public class Room {
	int id;
	int size;
	int price;
	String name;
	public Room(){
		
	}
	
	
	public Room(int id, int size, int price, String name) {
		super();
		this.id = id;
		this.size = size;
		this.price = price;
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
