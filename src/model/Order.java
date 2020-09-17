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
		
		date = LocalDateTime.now();
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		dateString = date.format(formatter);
		this.clientID = clientID;
		this.resTaxId = resTaxId;
		status=Status.REQUESTED;
		products=new ArrayList<Product>();
	}
	
	public void generateCode(ArrayList<Client> clients) {
		boolean isCodeDifferent= false;
		boolean out=false;
		do {
			code= (int) (Math.random()*10000000+1000);
			for(int i=0; i< clients.size() && out==false;i++) {
				
				for(int j=0; j< clients.get(i).getOrder().size() && out==false;i++) {
				if(code==clients.get(i).getOrder().get(j).getCode()) {
					isCodeDifferent=true;
					out=true;
				}
				}
			}
			
		}while(isCodeDifferent==true);
		
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
