package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class AdminSystem {
	public final static String FILE_RES_SER= "data/infoRestaurant.dat";
	public final static String FILE_PRO_SER= "data/infoProduct.dat";
	public final static String FILE_CLI_SER= "data/infoClient.dat";
	public final static String FILE_ORD_SER= "data/infoOrder.dat";
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Client> clients;

	public AdminSystem() {
		restaurants= new ArrayList<Restaurant>();
		clients=new ArrayList<Client>();
	}

	public void updateRestaurant() {}
	public void updateProduct() {}
	public void updateClient() {}
	public void updateOrder() {}

	public void addClient(IdType idType, int idNumber, String name, int phoneNumber, String address) {
		Client c= new Client (idType,idNumber,name,phoneNumber,address);
		clients.add(c);
	}
	public String showClients() {
		String info="";
		for(int i=0; i<clients.size(); i++) {
			info+=clients.get(i)+"\n";	
		}
		return info;
	}

	public void addRestaurant(String name, int taxID, String managerName) {
		Restaurant r= new Restaurant (name, taxID, managerName);
		restaurants.add(r);
	}
	public String showRestaurants() {
		String info="";
		for(int i=0; i<restaurants.size(); i++) {
			info+=restaurants.get(i)+"\n";	
		}
		return info;
	}
	
	public String showProducts(Restaurant r) {
		String info="";
		for(int i=0; i<r.getMenuItems().size(); i++) {
			info+=r.getMenuItems().get(i)+"\n";	
		}
		return info;
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


	public void saveProducts() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_PRO_SER);
		ObjectOutputStream oos= new ObjectOutputStream (new FileOutputStream(f));
		for (int i = 0; i < restaurants.size(); i++) {
			oos.writeObject(restaurants.get(i).getMenuItems());
		}
		
		oos.close();
	}

	
	@SuppressWarnings("unchecked")
	public void loadProducts() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_PRO_SER);
		ObjectInputStream ois= new ObjectInputStream (new FileInputStream(f));
		ArrayList<Product> menuItems2=(ArrayList<Product>)ois.readObject();
		if(menuItems2!=null) {
			for (int i = 0; i < restaurants.size(); i++) {
				restaurants.get(i).setMenuItems(menuItems2);
			}
			
			ois.close();
		}
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

	
	

		public void saveOrders() throws FileNotFoundException, IOException, ClassNotFoundException {
			File f = new File(FILE_ORD_SER);
			ObjectOutputStream oos= new ObjectOutputStream (new FileOutputStream(f));
			for (int i = 0; i < clients.size(); i++) {
				oos.writeObject(clients.get(i).getOrder());
			}
			
			oos.close();
		}


		@SuppressWarnings("unchecked")
		public void loadOrders() throws FileNotFoundException, IOException, ClassNotFoundException {
			File f = new File(FILE_ORD_SER);
			ObjectInputStream ois= new ObjectInputStream (new FileInputStream(f));
			ArrayList<Order> order2=(ArrayList<Order>)ois.readObject();
			if(order2!=null) {
				for (int i = 0; i < clients.size(); i++) {
					clients.get(i).setOrder(order2);
				}
				ois.close();
			}
		}	

	public void exportOrders() {}
	public void createOrder() {}
	public void importData() {}

	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
}
