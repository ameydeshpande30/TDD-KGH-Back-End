package tdd.kgh.models.jdbc;
public class Order_Items {
	int id;
	int OId;
	int IId;
	int qty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOId() {
		return OId;
	}

	public void setOId(int OId) {
		this.OId = OId;
	}
	
	public int getIId() {
		return IId;
	}

	public void setIId(int IId) {
		this.IId = IId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
}
