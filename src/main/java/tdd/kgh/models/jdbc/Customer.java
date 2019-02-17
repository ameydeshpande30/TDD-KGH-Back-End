package tdd.kgh.models.jdbc;

public class Customer {

	int id;
	String name;
	String address;
	String contactNumber;
	String email;
	String aadhar;
	String idproof;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Customer() {
		
	}
	public Customer(int id, String name, String address, String contactNumber, String email, String aadhar,
			String idproof) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.aadhar = aadhar;
		this.idproof = idproof;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	
	public String getIDProof() {
		return idproof;
	}

	public void setIDProof(String idproof) {
		this.idproof = idproof;
	}

}
