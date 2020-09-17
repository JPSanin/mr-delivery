package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.AdminSystem;

public class Menu {

	private AdminSystem adminSystem;
	private BufferedReader br;

	public Menu() {
		adminSystem= new AdminSystem();
		br= new BufferedReader(new InputStreamReader(System.in));
	}

	public void restaurantAdder()  {
	String name="";
	int taxId;
	String managerName="";
	 
	 try {
		 System.out.println("Please enter the restaurant's name");
		 name=br.readLine();
		 System.out.println("Please enter the restaurant's tax ID");
		 taxId=Integer.parseInt(br.readLine());
		 System.out.println("Please enter the restaurant's manager name");
		 managerName=br.readLine();
		 adminSystem.addRestaurant(name, taxId, managerName);
	} catch (IOException | NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 
	}

	public void start() {
		//Loading
		try {
			adminSystem.loadRestaurants();
		} catch (ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}

		restaurantAdder();
		System.out.println(adminSystem.showRestaurants());

		//Saving
		try {
			adminSystem.saveRestaurants();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
