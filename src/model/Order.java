package model;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.ArrayList;

public class Order {
	
	private int code;
	private LocalDateTime date;
	private String dateString;
	private DateTimeFormatter formatter; 
	private int clientID;
	private int resTaxId;
	private Status status;
	private ArrayList<Product> products;
	
	public Order(int clientID, int resTaxId) {
		code= (int) (Math.random()*100000+1000);
		date = LocalDateTime.now();
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		dateString = date.format(formatter);
		this.clientID = clientID;
		this.resTaxId = resTaxId;
		status=Status.REQUESTED;
		products=new ArrayList<Product>();
	}
	
	public void addProduct(){}
	
	public void modifyStatus() {}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public int getResTaxId() {
		return resTaxId;
	}

	public void setResTaxId(int resTaxId) {
		this.resTaxId = resTaxId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	
	
}