package tdd.kgh.models.jdbc;
public class Role {
	int id;
	String name;
	String desc;
	
	public int getId() {
		return id;
	}

	public Role() {
		super();
	}

	public Role(int id, String name, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
