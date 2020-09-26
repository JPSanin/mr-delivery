package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import exceptions.ClientNotFoundException;
import exceptions.OrderNotFoundException;
import exceptions.ProductNotFoundException;
import exceptions.RestaurantNotFoundException;



public class AdminSystem {
	public final static String FILE_RES_SER= "data/infoRestaurant.dat";
	public final static String FILE_CLI_SER= "data/infoClient.dat";
	public final static String FILE_EXPORT= "data/Orders.csv";
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Client> clients;
	private ArrayList<Order> ordersExport;
	
	/** Constructor method for Admin System <br>
	<b> pre: </b> <br>
	<b> post: </b> Creates an Admin System and initializes its attributes<br>

	*/
	public AdminSystem() {
		restaurants= new ArrayList<Restaurant>();
		clients=new ArrayList<Client>();
		ordersExport=new ArrayList<Order>();
	}

	//****************************************************************************************
	//Restaurant Methods
	/** Method for searching restaurants <br>
	<b> pre: </b> Must have created restaurant list<br>
	<b> post: </b> returns position of restaurant in the list <br>
	@param int taxID, restaurant tax id so it can be found
	*/
	public int searchRestaurant(int taxID) throws RestaurantNotFoundException{
		int search= taxID;
		boolean found= false;
		int index=0;
		for (int i = 0; i < restaurants.size() && !found; i++) {
			if(restaurants.get(i).getTaxID()==search) {
				index=i;
				found =true;
			}
		}

		if(found) {
			return index;
		}else {
			throw new RestaurantNotFoundException(search);
		}	
	}

	public void orderRestaurants() {
		RestaurantNameComparator rnc= new RestaurantNameComparator();
		Collections.sort(restaurants, rnc);
	}

	/** Method for showing restaurants as options<br>
	<b> pre: </b> Must have created restaurant list<br>
	<b> post: </b> returns string displaying restaurants <br>
	
	*/
	public String showRestaurantsOptions() {
		String info="";
		for(int i=0; i<restaurants.size(); i++) {
			if(i==restaurants.size()-1) {
				info+=(i+1)+")"+restaurants.get(i).getName();		
			}else {
				info+=(i+1)+")"+restaurants.get(i).getName()+"\n";	
			}
		}
		return info;
	}
	
	/** Method for showing restaurant information<br>
	<b> pre: </b> Must have created restaurant list<br>
	<b> post: </b> returns string with restaurants and their attributes <br>
	
	*/
	public String showRestaurants() {
		String info="";
		for(int i=0; i<restaurants.size(); i++) {
			info+=restaurants.get(i)+"\n";	
		}
		return info;
	}

	/**  method for adding a Restaurant <br>
	<b> pre: </b> List already has to be initialized<br>
	<b> post: </b> Creates a restaurant and adds it to list<br>
	@param name, a name for the restaurant, must be a String !=null or !=" "
	@param resTaxID, the restaurant tax ID, whole positive number
	@param managerName,the name of the restaurant's manager, must be a String !=null or !=" "
	*/
	public void addRestaurant(String name, int taxID, String managerName) {
		Restaurant r= new Restaurant (name, taxID, managerName);
		restaurants.add(r);
	}
	
	
	/**  method for serializing restaurants <br>
	<b> pre: </b> List already has to be initialized<br>
	<b> post: </b> Serializes restaurants<br>
	*/
	public void saveRestaurants() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_RES_SER);
		ObjectOutputStream oos= new ObjectOutputStream (new FileOutputStream(f));
		oos.writeObject(restaurants);
		oos.close();
	}
	
	/**  method for deserializing restaurants <br>
	<b> pre: </b> List already has to be initialized<br>
	<b> post: </b> deserializes restaurants and loads them<br>
	*/
	@SuppressWarnings("unchecked")
	public void loadRestaurants() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_RES_SER);
		ObjectInputStream ois= new ObjectInputStream (new FileInputStream(f));
		ArrayList<Restaurant> restaurants2=(ArrayList<Restaurant>)ois.readObject();
		if(restaurants2!=null) {
			restaurants=restaurants2;
			ois.close();
		}
	}


	//****************************************************************************************
	//Product Methods
	/** Method for searching products <br>
	<b> pre: </b> Must have created restaurant list<br>
	<b> post: </b> returns position of restaurant in the list <br>
	@param c, product code to search for in restaurant
	@param resIndex, position of the restaurant to search in
	*/
	public int searchProduct(int c, int resIndex) throws ProductNotFoundException {
		int search = c;
		boolean found= false;
		int index =0;
		for (int i = 0; i < restaurants.get(resIndex).getMenuItems().size() && !found; i++) {
			if(restaurants.get(resIndex).getMenuItems().get(i).getCode()==search) {
				index=i;
				found =true;
			}
		}
		if(found) {
			return index;
		}else {
			throw new ProductNotFoundException(search);
		}	
	}

	/** Method for showing products of a restaurant<br>
	<b> pre: </b> Must have created restaurant list and added products to it<br>
	<b> post: </b> returns string with products <br>
	@param r, Restaurant object to display its' products
	
	*/
	public String showProducts(Restaurant r) {
		String info="";
		for(int i=0; i<r.getMenuItems().size(); i++) {
			if(i==r.getMenuItems().size()-1) {
				info+=(i+1)+") "+r.getMenuItems().get(i);	
			}else {
				info+=(i+1)+") "+r.getMenuItems().get(i)+"\n";	
			}
		}
		return info;
	}


	//****************************************************************************************
	//Client methods
	/** Method for searching clients <br>
	<b> pre: </b> Must have created client list<br>
	<b> post: </b> returns position of client in the list <br>
	@param docNum, client id to search for in clients
	
	*/
	public int searchClient(int docNum) throws ClientNotFoundException {
		int search= docNum;
		boolean found= false;
		int index=0;
		for (int i = 0; i < clients.size() && !found; i++) {
			if(clients.get(i).getIdNumber()==search) {
				index=i;
				found =true;
			}
		}

		if(found) {
			return index;
		}else {
			throw new ClientNotFoundException(search);
		}	
	}

	
	/** Method for searching clients efficiently<br>
	<b> pre: </b> Must have created client list<br>
	<b> post: </b> returns position of client in the list <br>
	@param name, client name to search for in list
	
	*/
	public int searchClientName(String name) throws ClientNotFoundException {
		int r=0;
		ArrayList<Client> clientsSearch= orderClientByNames();
		String[] names= name.split(" ",2);
		String realname = names[1]+ " "+ names[0];
		boolean found=false;
		int start=0;
		int end= clients.size()-1;

		while(start<=end && !found) {

			int mid= (start+end)/2;
			if(clientsSearch.get(mid).getName().equals(realname)) {
				found=true;
				r=mid;

			}else if(clientsSearch.get(mid).getName().compareTo(realname)>0) {
				end= mid-1;
			}else {
				start=mid+1;	
			}

		}
		if (found==true) {
			return r;
		}else {
			throw new ClientNotFoundException(name);
		}

	}


	
	/** Method for sorting clients by names<br>
	<b> pre: </b> Must have created client list<br>
	<b> post: </b> returns ordered client list <br>
	
	*/
	public ArrayList<Client> orderClientByNames() {
		ArrayList<Client> clientByNames= clients;

		int count= clientByNames.size();

		for (int i = 0; i < count; i++) 
		{
			for (int j = i + 1; j < count; j++) { 

				if (clientByNames.get(i).getName().compareTo(clientByNames.get(j).getName())>0) 
				{
					System.out.println("yes");
					Client temp = clientByNames.get(i);
					clientByNames.set(i,clientByNames.get(j));
					clientByNames.set(j,temp);
				}
			}
		}


		return clientByNames;


	}

	/** Method for printing clients sorted by phone<br>
	<b> pre: </b> Must have created client list<br>
	<b> post: </b> returns string ordered client list <br>
	
	*/
	public String printOrderedClientsByPhone() {
		String info="";
		ArrayList<Client> phoneClients= clients;
		Collections.sort(phoneClients);

		for(int i=0; i<phoneClients.size(); i++) {
			info+=phoneClients.get(i)+"\n";	
		}
		return info;
	}
	
	
	/** method for adding a client <br>
	<b> pre: </b> <br>
	<b> post: </b> Creates a client and adds it to list in corresponding position<br>
	@param idType, id type of the client, must be a Constant from enum IdType
	@param idNumber, a positive whole number 
	@param name, a name for the client, must be a String !=null or !=" "
	@param phoneNumber, a positive 10 digit whole number 
	@param address, an address,must be a String !=null or !=" "
	*/
	public void addClient(IdType idType, int idNumber, String name, Long phoneNumber, String address) {
		Client c= new Client (idType,idNumber,name,phoneNumber,address);
		if(clients.isEmpty()) {
			clients.add(c);
		}else {
			int i = 0;
			while(i<clients.size() && (c.getName().compareTo(clients.get(i).getName())<=0)) {
				i++;
			}

			if(i==clients.size()) {
				clients.add(c);	
			}else {
				clients.add(i,c);	
			}

		}

	}

	/** Method for displaying clients<br>
	<b> pre: </b> Must have created client list<br>
	<b> post: </b> returns string with clients information <br>
	
	*/
	public String showClients() {
		String info="";
		for(int i=0; i<clients.size(); i++) {
			info+=clients.get(i)+"\n";	
		}
		return info;
	}

	/**  method for serializing clients <br>
	<b> pre: </b> List already has to be initialized<br>
	<b> post: </b> Serializes clients <br>
	*/
	public void saveClients() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_CLI_SER);
		ObjectOutputStream oos= new ObjectOutputStream (new FileOutputStream(f));
		oos.writeObject(clients);
		oos.close();
	}

	/**  method for deserializing clients <br>
	<b> pre: </b> List already has to be initialized<br>
	<b> post: </b> deserializes clients and loads them <br>
	*/
	@SuppressWarnings("unchecked")
	public void loadClients() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_CLI_SER);
		ObjectInputStream ois= new ObjectInputStream (new FileInputStream(f));
		ArrayList<Client> clients2=(ArrayList<Client>)ois.readObject();
		if(clients2!=null) {
			clients=clients2;
			ois.close();
		}
	}

	//************************************************************************************
	// Order methods
	/** Method for searching orders <br>
	<b> pre: </b> Must have created order list in client<br>
	<b> post: </b> returns position of the order in the list <br>
	@param c, order code
	@param cliIndex, the position where the client whose order is being searched for
	
	*/
	public int searchOrder(int c, int cliIndex) throws OrderNotFoundException {
		int search = c;
		boolean found= false;
		int index =0;
		for (int i = 0; i < clients.get(cliIndex).getOrder().size() && !found; i++) {
			if(clients.get(cliIndex).getOrder().get(i).getCode()==search) {
				index=i;
				found =true;
			}
		}
		if(found) {
			return index;
		}else {
			throw new OrderNotFoundException(search);
		}	
	}

	/** Method for showing client's orders <br>
	<b> pre: </b> Must have created order list in client<br>
	<b> post: </b> returns string with client orders <br>
	@param c, client with orders

	*/
	public String showOrders(Client c) {
		String info="";
		for(int i=0; i<c.getOrder().size(); i++) {
			if(i==c.getOrder().size()-1) {
				info+=c.getOrder().get(i);	
			}else {
				info+=c.getOrder().get(i)+"\n";	
			}
		}
		return info;
	}

	/** Method for showing all client's orders <br>
	<b> pre: </b> Must have created orders list<br>
	<b> post: </b> returns string with all orders <br>
	@param separator, char that will separate information

	*/
	public String showAllOrders(char separator) {
		String info="";
		
		for(int i=0; i<ordersExport.size(); i++) {
			if(i==ordersExport.size()-1) {
				info+=ordersExport.get(i).exportToString(separator);	
			}else {
				info+=ordersExport.get(i).exportToString(separator)+"\n";	
			}
		}
		return info;
	}

	/** Method for sorting all client's orders <br>
	<b> pre: </b> Must have created all orders list<br>
	<b> post: </b> sorts the list by the criteria, restaurant tax id, client id, date (all ascending) <br>

	*/
	public void orderOrdersForExport() {
		
		Comparator<Order> c;

		c = new Comparator<Order>() {			
			public int compare(Order n1, Order n2) {
				int comp;
				comp = n1.getResTaxId()-n2.getResTaxId();
				if(comp==0) {
					comp = n1.getClientID()-n2.getClientID();
				}if(comp==0){
					String dateString1 = n1.getDateString();
					String dateString2 =  n2.getDateString();

					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

					try {
						Date date1 = format.parse(dateString1);
						Date date2 = format.parse(dateString2);
						comp= date1.compareTo(date2);
					} catch (ParseException e) {
						
					
					}
					


				}
				return comp;
			}
		};
		
		Collections.sort(ordersExport,c);	
		
	}

	/** Method for adding all client's orders <br>
	<b> pre: </b> Must have created all orders list<br>
	<b> post: </b> adds all orders to list <br>

	*/
	public void addAllOrders() {
		int size=clients.size();
		ordersExport.clear();
		for(int i=0; i<size; i++) {
			for(int j=0; j<clients.get(i).getOrder().size();j++) {
				ordersExport.add(clients.get(i).getOrder().get(j));
			}
		}
		

	}


	//Import Export Methods
	
	/** Method for importing restaurants <br>
	<b> pre: </b> Must have created restaurant list<br>
	<b> post: </b>imports restaurants into program <br>
	@param path, String representing the file path to import into program
	*/
	public void importRestaurants(String path) throws IOException {
		BufferedReader brf= new BufferedReader(new FileReader(path));
		String line=brf.readLine();
		line=brf.readLine();
		while(line != null) {
			String[] info= line.split(",");
			String name= info[0].trim();
			int taxId= Integer.parseInt(info[1].trim());
			String managerName= info[2].trim();
			Restaurant r= new Restaurant (name, taxId, managerName);
			restaurants.add(r);
			line=brf.readLine();
		}

		brf.close();
	}

	/** Method for importing products into restaurant <br>
	<b> pre: </b> Must have created restaurant list and product list<br>
	<b> post: </b>imports products into program <br>
	@param path, String representing the file path to import into program
	@param taxID, tax id of the restaurant products to be imported to
	*/
	public void importProducts(String path, int taxID) throws IOException, RestaurantNotFoundException {
		BufferedReader brf= new BufferedReader(new FileReader(path));
		String line=brf.readLine();
		line=brf.readLine();
		while(line != null) {
			String[] info= line.split(",");
			int code= Integer.parseInt(info[0].trim());
			String name= info[1].trim();
			String desc= info[2].trim();
			String price= info[3].trim();
			Double price2= Double.parseDouble(price);
			int index=searchRestaurant(taxID);
			restaurants.get(index).addProduct(code, name, desc, price2);
			line=brf.readLine();
		}

		brf.close();
	}

	/** Method for importing clients <br>
	<b> pre: </b> Must have created client list<br>
	<b> post: </b>imports clients into program <br>
	@param path, String representing the file path to import into program
	*/
	public void importClients(String path) throws IOException {
		BufferedReader brf= new BufferedReader(new FileReader(path));
		String line=brf.readLine();
		line=brf.readLine();
		while(line != null) {
			String[] info= line.split(",");
			String idTypeEnumValue= info[0].trim().toUpperCase();
			IdType idType=IdType.valueOf(idTypeEnumValue);
			int id=Integer.parseInt(info[1].trim());
			String name= info[2].trim();
			Long phoneNumber=Long.parseLong(info[3].trim());
			String address= info[4].trim();
			Client c= new Client(idType, id, name, phoneNumber, address);
			clients.add(c);
			line=brf.readLine();
		}

		brf.close();
	}
	
	
	/** Method for importing orders <br>
	<b> pre: </b> Must have created client list<br>
	<b> post: </b>imports orders into program if the client and restaurant exist in program <br>
	@param path, String representing the file path to import into program
	*/
	public int[] importOrders(String path) throws IOException {
		BufferedReader brf= new BufferedReader(new FileReader(path));
		String line=brf.readLine();
		line=brf.readLine();
		int[] cantAdd=new int[2];
		cantAdd[0]=0;
		cantAdd[1]=0;
		while(line != null) {
			String[] info= line.split(",");
			int code=Integer.parseInt(info[0].trim());
			String date= info[1].trim();
			int clientId=Integer.parseInt(info[2].trim());
			int resTaxId=Integer.parseInt(info[3].trim());
			String statusvalue=info[4].trim().toUpperCase();
			Status s=Status.valueOf(statusvalue);
			try {
				int index1=searchClient(clientId);
				try {
					searchRestaurant(resTaxId);
					clients.get(index1).addOrder(code,clientId, resTaxId,date,s);
				} catch (RestaurantNotFoundException e) {
					cantAdd[1]++;

				}

			} catch (ClientNotFoundException e) {
				cantAdd[0]++;

			}
			line=brf.readLine();
		}
		brf.close();
		return cantAdd;

	}
	
	/** Method for exporting orders <br>
	<b> pre: </b> Must have created exportOrders list<br>
	<b> post: </b>exports orders to csv file <br>
	@param separator, char that will separate information
	*/
	
	public void exportOrders(char separator) throws IOException, FileNotFoundException {
		File f= new File (FILE_EXPORT);
		FileWriter fw= new FileWriter(f);
		PrintWriter pw= new PrintWriter(f);
		String info= "";
		info+="Order code" + ""+ separator +""+"Date"+ separator+"Client ID" +separator+ "Restaurant Tax ID"+ separator+"Status"+"\n";
		info+=showAllOrders(separator);
		pw.print(info);
		fw.close();
		pw.close();

	}




	//Gets and Sets
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	public ArrayList<Client> getClients() {
		return clients;
	}
}
