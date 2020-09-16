package model;

import java.util.ArrayList;

public class Restaurant {
	private String name;
	private int taxID;
	private String managerName;
	private ArrayList<Product> menuItems;
	
	public Restaurant(String name, int taxID, String managerName) {
		this.name = name;
		this.taxID = taxID;
		this.managerName = managerName;
		menuItems=new ArrayList<Product>();
	}
	


	public void addProduct() {}

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
	
}
