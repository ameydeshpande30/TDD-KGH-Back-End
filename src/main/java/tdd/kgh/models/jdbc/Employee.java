package tdd.kgh.models.jdbc;

public class Employee {
	int id;
	String name;
	String contactNumber;
	String username;
	String DOJ;
	int sal;
	int DId;
	int RId;
	String password;
	
	public Employee() {
		
	}

	public Employee(int id, String name, String contactNumber, String username, String dOJ, int sal,
			int dId, int rId, String password) {
		super();
		this.id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.username = username;
		DOJ = dOJ;
		this.sal = sal;
		DId = dId;
		RId = rId;
		this.password = password;
	}

	public int getId() {
		return id;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDOJ() {
		return DOJ;
	}

	public void setDOJ(String DOJ) {
		this.DOJ = DOJ;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}
	
	public int getDId() {
		return DId;
	}

	public void setDid(int DId) {
		this.DId = DId;
	}
	
	public int getRId() {
		return RId;
	}

	public void setRId(int RId) {
		this.RId = RId;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
