package tdd.kgh.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Room implements Serializable {
	 
	 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
        private int RID;
		private String Name;
		private int Price;
		private int Size;
		
		public Room() {
			
		}
		
		public Room(String name, int price, int size) {
			Name = name;
			Price = price;
			Size = size;
		}
		public int getRID() {
			return RID;
		}
		public void setRID(int rID) {
			RID = rID;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public int getPrice() {
			return Price;
		}
		public void setPrice(int price) {
			Price = price;
		}
		public int getSize() {
			return Size;
		}
		public void setSize(int size) {
			Size = size;
		}
		
		

}
