package model;
import java.io.Serializable;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.ArrayList;

public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private LocalDateTime date;
	private String dateString;
	
	private int clientID;
	private int resTaxId;
	private Status status;
	private ArrayList<Product> products;
	private ArrayList<Integer> productQuantities;
	
	public Order(int clientID, int resTaxId) {
		DateTimeFormatter formatter; 
		date = LocalDateTime.now();
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		dateString = date.format(formatter);
		this.clientID = clientID;
		this.resTaxId = resTaxId;
		status=Status.REQUESTED;
		products=new ArrayList<Product>();
		productQuantities= new ArrayList<Integer>();
	}
	
	public void generateCode(ArrayList<Client> clients) {
		code= (int) (Math.random()*10000000+1000);
	}
	
	public void addProduct(Product p,int q){
			products.add(p);
			productQuantities.add(q);
	}
	
	
	public String showProducts() {
		String s="";
		for (int i = 0; i < products.size(); i++) {
			if(i==products.size()-1) {
				s+=(i+1)+")"+productQuantities.get(i)+";"+products.get(i).toString();
			}else {
				s+=(i+1)+")"+productQuantities.get(i)+";"+products.get(i).toString()+"\n";
			}
		}
		return s;
	}
	
	

	@Override
	public String toString() {
		String r = "";
		r+=code+";"+dateString+ ";"+clientID +";"+ resTaxId+ ";"+status+"\n";
		for (int i = 0; i < products.size(); i++) {
			if(i==products.size()-1) {
				r+=productQuantities.get(i)+";"+products.get(i).toString();
			}else {
				r+=productQuantities.get(i)+";"+products.get(i).toString()+"\n";
			}
		} 
		return r;
	}
	
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
	
	public ArrayList<Integer> getProductQuantities() {
		return productQuantities;
	}

	public void setProductQuantities(ArrayList<Integer> productQuantities) {
		this.productQuantities = productQuantities;
	}
	
}
