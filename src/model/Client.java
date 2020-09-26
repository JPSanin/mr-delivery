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
	
	 /** Constructor method for a Client <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates a client and initializes its attributes<br>
		@param idType, id type of the client, must be a Constant from enum IdType
		@param idNumber, a positive whole number 
		@param name, a name for the client, must be a String !=null or !=" "
		@param phoneNumber, a positive 10 digit whole number 
		@param address, an address,must be a String !=null or !=" "
		*/
	public Client(IdType idType, int idNumber, String name, Long phoneNumber, String address) {
		this.idType = idType;
		this.idNumber = idNumber;
		String[] names= name.split(" ",2);
		this.name = names[1]+ " "+ names[0];
		this.phoneNumber = phoneNumber;
		this.address = address;
		order=new ArrayList<Order>();
	}

	
	/** Method for comparing client to another client <br>
	<b> pre: </b> Must have created this client and another one<br>
	<b> post: </b> returns a integer representing the comparison<br>
	@param otherClient, client to compare 
	*/
	@Override
	public int compareTo(Client otherClient) {
		int comp;
		Long p1= phoneNumber;
		Long p2= otherClient.getPhoneNumber();
		comp = p2.compareTo(p1);
		return comp;
	}
	

	/**  method for adding an Order <br>
	<b> pre: </b> <br>
	<b> post: </b> Creates a Order adds it to list of orders<br>
	@param clientID, a positive whole number that represents a client
	@param resTaxId, a positive whole number that represents a Restaurant
	*/
	public void addOrder(int clientId, int resTaxId) {
		Order o= new Order (clientId, resTaxId);
		order.add(o);		
	}
	
	/**  method for adding an Order <br>
	<b> pre: </b> <br>
	<b> post: </b> Creates a Order adds it to list of orders<br>
	@param code, a positive whole number representing an order code 
	@param clientID, a positive whole number that represents a client
	@param resTaxId, a positive whole number that represents a Restaurant
	@param date, a date in string format,must be a String !=null or !=" 
	@param s, a Status of enum Status representing the status of an order
	*/
	public void addOrder(int code,int clientID, int resTaxId, String date, Status s) {
		Order o= new Order (code,clientID, resTaxId, date, s);
		order.add(o);		
	}

	/** Method for displaying client <br>
	<b> pre: </b> Must have created client <br>
	<b> post: </b> Shows client attributes in a string <br>
	*/
	@Override
	public String toString() {
		String r = "";
		r+=idType+ ";"+idNumber +";"+ name+ ";"+phoneNumber +";"+ address;
		return r;
	}

	//Getters and Setters
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
