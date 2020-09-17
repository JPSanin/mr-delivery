package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class System {
	public final static String FILE_RES_SER= "data/infoRestaurant.dat";

	private ArrayList<Restaurant> restaurants;
	private ArrayList<Client> clients;

	public System() {
		restaurants= new ArrayList<Restaurant>();
		clients=new ArrayList<Client>();
	}

	public void updateRestaurant() {}
	public void updateProduct() {}
	public void updateClient() {}
	public void updateOrder() {}

	public void addClient() {}
	public void showClients() {}

	public void addRestaurant() {}
	public void showRestaurants() {}

	//Cuando se acabe el programa
	public void saveRestaurants() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_RES_SER);
		ObjectOutputStream oos= new ObjectOutputStream (new FileOutputStream(f));
		oos.writeObject(restaurants);
		oos.close();
	}

	//Apenas inicie el programa
	public void loadRestaurants() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_RES_SER);
		ObjectInputStream ois= new ObjectInputStream (new FileInputStream(f));
		ArrayList<Restaurant> restaurants2=(ArrayList<Restaurant>)ois.readObject();
		if(restaurants2!=null) {
			restaurants=restaurants2;
			ois.close();
		}
	}


	public void exportOrders() {}
	public void createOrder() {}
	public void importData() {}

}
