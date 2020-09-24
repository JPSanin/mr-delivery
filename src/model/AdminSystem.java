package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	
	

	public void exportOrders() {}

	public void importData() {}

	
	//Gets and Sets
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	public ArrayList<Client> getClients() {
		return clients;
	}
}
