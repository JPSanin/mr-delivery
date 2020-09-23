package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable, Comparable<Client> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IdType idType;
	private int idNumber;
	private String name;
	private Long phoneNumber;
	private String address;
	private ArrayList<Order> order;
	
	public Client(IdType idType, int idNumber, String name, Long phoneNumber, String address) {
		this.idType = idType;
		this.idNumber = idNumber;
		String[] names= name.split(" ",2);
		this.name = names[1]+ " "+ names[0];
		this.phoneNumber = phoneNumber;
		this.address = address;
		order=new ArrayList<Order>();
	}

	@Override
	public int compareTo(Client otherClient) {
		int comp;
		Long p1= phoneNumber;
		Long p2= otherClient.getPhoneNumber();
		comp = p2.compareTo(p1);
		return comp;
	}
	

	public void addOrder() {}

	@Override
	public String toString() {
		String r = "";
		r+=idType+ ";"+idNumber +";"+ name+ ";"+phoneNumber +";"+ address;
		return r;
	}


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


	public Long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(Long phoneNumber) {
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


	public void setOrder(ArrayList<Order> order) {
		this.order = order;
	}
	
	
}
