package tdd.kgh.models.jdbc;
public class Order {
	int id;
	String check_in_date;
	String check_out_date;
	int RId;
	int CId;
	int amount;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCheckInDate(){
		return check_in_date;
	}
	
	public void setCheckInDate(String date){
		this.check_in_date = date;
	}
	
	public String getCheckOutDate(){
		return check_out_date;
	}
	
	public void setCheckOutDate(String date){
		this.check_out_date = date;
	}
	
	public int getRId() {
		return RId;
	}

	public void setRId(int RId) {
		this.RId = RId;
	}
	
	public int getCId() {
		return CId;
	}

	public void setCId(int CId) {
		this.CId = CId;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
