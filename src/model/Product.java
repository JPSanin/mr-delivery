package model;

public class Product  {

	private int code;
	private String name;
	private String description;
	private double price;
	private int resTaxID;
	
	public Product(int code, String name, String description, double price, int resTaxID) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.resTaxID = resTaxID;
	}
	
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getResTaxID() {
		return resTaxID;
	}

	public void setResTaxID(int resTaxID) {
		this.resTaxID = resTaxID;
	}
	
	
	
}
