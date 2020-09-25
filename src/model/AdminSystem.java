package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.ClientNotFoundException;
import exceptions.OrderNotFoundException;
import exceptions.ProductNotFoundException;
import exceptions.RestaurantNotFoundException;



public class AdminSystem {
	public final static String FILE_RES_SER= "data/infoRestaurant.dat";
	public final static String FILE_CLI_SER= "data/infoClient.dat";
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Client> clients;

	public AdminSystem() {
		restaurants= new ArrayList<Restaurant>();
		clients=new ArrayList<Client>();
	}

	//****************************************************************************************
	//Restaurant Methods
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

	public String showRestaurants() {
		String info="";
		for(int i=0; i<restaurants.size(); i++) {
			info+=restaurants.get(i)+"\n";	
		}
		return info;
	}

	public void addRestaurant(String name, int taxID, String managerName) {
		Restaurant r= new Restaurant (name, taxID, managerName);
		restaurants.add(r);
	}

	public void saveRestaurants() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_RES_SER);
		ObjectOutputStream oos= new ObjectOutputStream (new FileOutputStream(f));
		oos.writeObject(restaurants);
		oos.close();
	}

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
	

	public String printOrderedClientsByPhone() {
		String info="";
		ArrayList<Client> phoneClients= clients;
		Collections.sort(phoneClients);

		for(int i=0; i<phoneClients.size(); i++) {
			info+=phoneClients.get(i)+"\n";	
		}
		return info;
	}
	
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

	public String showClients() {
		String info="";
		for(int i=0; i<clients.size(); i++) {
			info+=clients.get(i)+"\n";	
		}
		return info;
	}

	public void saveClients() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_CLI_SER);
		ObjectOutputStream oos= new ObjectOutputStream (new FileOutputStream(f));
		oos.writeObject(clients);
		oos.close();
	}

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
	
	public void exportOrders() {}

	

	
	//Gets and Sets
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	public ArrayList<Client> getClients() {
		return clients;
	}
}
