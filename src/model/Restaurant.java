package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int taxID;
	private String managerName;
	private ArrayList<Product> menuItems;
	
	
	 /** Constructor method for a Restaurant <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates a restaurant and initializes its attributes<br>
		@param name, a name for the restaurant, must be a String !=null or !=" "
		@param resTaxID, the restaurant tax ID, whole positive number
		@param managerName,the name of the restaurant's manager, must be a String !=null or !=" "
		*/
	public Restaurant(String name, int taxID, String managerName) {
		this.name = name;
		this.taxID = taxID;
		this.managerName = managerName;
		menuItems=new ArrayList<Product>();
	}
	

	/** Method for adding products to restaurant <br>
	<b> pre: </b> Must have created restaurant<br>
	<b> post: </b> adds product to restaurant <br>
	@param code, code representing the product 
	@param name, a name for the product, must be a String !=null or !=" "
	@param description,a description for the product, must be a String !=null or !=" "
	@param price, a double representing the product price
	*/
	public void addProduct(int code, String name, String description, double price) {
		Product p= new Product(code, name, description,price,taxID);
		menuItems.add(p);
	}
	
	
	/** Method for adding products to restaurant <br>
	<b> pre: </b> Must have created restaurant and product<br>
	<b> post: </b> adds existing product to restaurant <br>
	@param p, Already existing product object
	*/
	public void addProduct(Product p) {
		menuItems.add(p);
	}
	
	/** Method for displaying restaurant <br>
	<b> pre: </b> Must have created restaurant <br>
	<b> post: </b> Shows restaurants attributes in a string <br>
	*/
	@Override
	public String toString() {
		String r = "";
		r+=name+ ";"+taxID +";"+ managerName;
		return r;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTaxID() {
		return taxID;
	}

	public void setTaxID(int taxID) {
		this.taxID = taxID;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	public ArrayList<Product> getMenuItems() {
		return menuItems;
	}
	
	public void setMenuItems(ArrayList<Product> menuItems) {
		this.menuItems = menuItems;
	}
	
}
