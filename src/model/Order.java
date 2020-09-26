package model;
import java.io.Serializable;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.ArrayList;


public class Order implements Serializable {
	

	private static final long serialVersionUID = 1L;
	

	private int code;
	

	private LocalDateTime date;
	

	private String dateString;
	

	private int clientID;
	

	private int resTaxId;
	
	
	private Status status;
	

	private ArrayList<Product> products;
	

	private ArrayList<Integer> productQuantities;
	
	 /**
 	 *  Constructor method for an Order <br>
 	 * 	<b> pre: </b> <br>
 	 * 	<b> post: </b> Creates a Order and initializes its attributes<br>.
 	 *
 	 * @param clientID the client ID
 	 * @param resTaxId the res tax id
 	 */
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
	
	 /**
 	 *  Constructor method for an Order <br>
 	 * 	<b> pre: </b> <br>
 	 * 	<b> post: </b> Creates a Order and initializes its attributes<br>.
 	 *
 	 * @param code the code
 	 * @param clientID the client ID
 	 * @param resTaxId the res tax id
 	 * @param date the date
 	 * @param s the s
 	 */
	public Order(int code,int clientID, int resTaxId, String date, Status s) {
		dateString = date;
		this.code=code;
		this.clientID = clientID;
		this.resTaxId = resTaxId;
		status=s;
		products=new ArrayList<Product>();
		productQuantities= new ArrayList<Integer>();
	}
	
	
	/**
	 *  Method for generating order code <br>
	 * 	<b> pre: </b> Must have created order and assigned client<br>
	 * 	<b> post: </b> generates order code <br>.
	 *
	 * @param clients the clients
	 */
	public void generateCode(ArrayList<Client> clients) {
		code= (int) (Math.random()*10000000+1000);
	}
	
	
	/**
	 *  Method for adding products to order <br>
	 * 	<b> pre: </b> Must have created order and product<br>
	 * 	<b> post: </b> adds existing product to order with quantity <br>.
	 *
	 * @param p the p
	 * @param q the q
	 */
	public void addProduct(Product p,int q){
			products.add(p);
			productQuantities.add(q);
	}
	
	
	/**
	 *  Method for displaying products <br>
	 * 	<b> pre: </b> Must have created products <br>
	 * 	<b> post: </b> Shows products attributes in a string <br>.
	 *
	 */
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
	
	
	/**
	 *  Method for displaying orders <br>
	 * 	<b> pre: </b> Must have created orders <br>
	 * 	<b> post: </b> Shows orders attributes in a string <br>.
	 
	 */
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
	
	/**
	 *  Method for displaying orders <br>
	 * 	<b> pre: </b> Must have created orders <br>
	 * 	<b> post: </b> Shows orders attributes in a string with custom separator <br>.
	 *
	 * @param separator the separator
	 * @return the string
	 */
	public String exportToString(char separator) {
		String r = "";
		r+=code + ""+ separator +""+dateString+ separator+clientID +separator+ resTaxId+ separator+status;
		return r;
	}
	
	/**
	 *  Method for modifying order status <br>
	 * 	<b> pre: </b> Must have created orders <br>
	 * 	<b> post: </b> Modifies order status, can only go forward <br>.
	 */
	public void modifyStatus() {
		if (status==Status.REQUESTED) {
			status=Status.IN_PROCESS;
		}else if(status==Status.IN_PROCESS) {
			status=Status.SHIPPED;
		}else if(status==Status.SHIPPED) {
			status=Status.DELIVERED;
		}
	}
	
	
	
	//Getters and Setters
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
