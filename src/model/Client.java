package model;

import java.util.ArrayList;

public class Client {
	private IdType idType;
	private int idNumber;
	private String name;
	private int phoneNumber;
	private String address;
	private ArrayList<Order> order;
	
	public Client(IdType idType, int idNumber, String name, int phoneNumber, String address) {
		this.idType = idType;
		this.idNumber = idNumber;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		order=new ArrayList<Order>();
	}

	

	public void addOrder() {}




	public IdType getIdType() {
		return idType;
	}



	public void setIdType(IdType idType) {
		this.idType = idType;
	}



	public int getIdNumber() {
		return idNumber;
	}


	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	public ArrayList<Order> getOrder() {
		return order;
	}
	
	
}
