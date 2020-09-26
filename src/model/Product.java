package model;

import java.io.Serializable;


public class Product implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	

	private int code;
	
	
	private String name;
	
	
	private String description;
	

	private double price;
	
	
	private int resTaxID;
	
	 /**
 	 *  Constructor method for a Product <br>
 	 * 	<b> pre: </b> <br>
 	 * 	<b> post: </b> Creates a product and initializes its attributes<br>.
 	 *
 	 * @param code the code
 	 * @param name the name
 	 * @param description the description
 	 * @param price the price
 	 * @param resTaxID the res tax ID
 	 */
	public Product(int code, String name, String description, double price, int resTaxID) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.resTaxID = resTaxID;
	}
	
	
	/**
	 *  Method for displaying product <br>
	 * 	<b> pre: </b> Must have created product <br>
	 * 	<b> post: </b> Shows products attributes in a string <br>.
	 *
	 *
	 */
	@Override
	public String toString() {
		String r = "";
		r+=code+";"+name+ ";"+description +";"+ price+ ";"+resTaxID;
		return r;
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
