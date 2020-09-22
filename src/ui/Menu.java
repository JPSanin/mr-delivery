package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.AdminSystem;
import model.IdType;

public class Menu {

	private AdminSystem adminSystem;
	private BufferedReader br;
	
	public Menu() {
		adminSystem= new AdminSystem();
		br= new BufferedReader(new InputStreamReader(System.in));
	}
	
	/* Luego lo armas, primero lista ordenada de restaurantes
	 * luego lista ordenada de clientes
	public void orderCreator() {
		String[] orderInfo;
		int option=0;
		try {
			System.out.println("Order Creation Started...");
			do {
			System.out.println("Has the client already been registered");
			System.out.println("1) yes");
			System.out.println("2) no");
			option= Integer.parseInt(br.readLine());
			}while(option!=1 || option!=2);
			
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public void clientAdder() {
		String[] clientInfo;

		try {
			System.out.println("Please enter the client's information in the following format:");
			System.out.println("Client IdType (Passport, ID or License); Client ID Number; Client Name; Client Phone Number (10 digit); Client Address");
			clientInfo=br.readLine().split(";");
			String idTypeEnumValue= clientInfo[0].trim().toUpperCase();
			IdType idType=IdType.valueOf(idTypeEnumValue);
			int id=Integer.parseInt(clientInfo[1].trim());
			String name= clientInfo[2].trim();
			Long phoneNumber=Long.parseLong(clientInfo[3].trim());
			String address= clientInfo[4].trim();
			adminSystem.addClient(idType, id,name,phoneNumber,address);
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void restaurantAdder()  {
		String[] resInfo;

		try {
			System.out.println("Please enter the restaurant's information in the following format:");
			System.out.println("Restaurant Name; Restaurant Tax ID; Restaurant Manager Name");
			resInfo=br.readLine().split(";");
			String name= resInfo[0].trim();
			int taxId=Integer.parseInt(resInfo[1].trim());
			String managerName= resInfo[2].trim();
			adminSystem.addRestaurant(name, taxId, managerName);
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void productAdder()  {

		String[] productInfo;
		int exit=adminSystem.getRestaurants().size()+1;
		int option=0;
		do {
			System.out.println("Please select the restaurant that you want to add products to");

			for (int i = 0; i<adminSystem.getRestaurants().size(); i++) {
				System.out.println((i+1)+") "+adminSystem.getRestaurants().get(i).getName());
			}
			System.out.println(exit+") "+"Exit");

			try {
				option=Integer.parseInt(br.readLine());
				if(option!=exit) {
					System.out.println("Please enter the product's information in the following format:");
					System.out.println("Product Code; Product Name; Product Description; Product Price");
					productInfo=br.readLine().split(";");
					int code= Integer.parseInt(productInfo[0].trim());
					String name= productInfo[1].trim();
					String description= productInfo[2].trim();
					double price=Double.parseDouble(productInfo[3].trim());		
					adminSystem.getRestaurants().get(option-1).addProduct(code, name, description, price); 
				}

			} catch (IOException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}while(option!=exit);
	}

	public void start() {
		//Loading
		try {
			adminSystem.loadRestaurants();
			adminSystem.loadProducts();
			adminSystem.loadClients();
		} catch (ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}
		
		/*adminSystem.addRestaurant("Authentic Wings", 1098771, "Paco Perea");
		adminSystem.addRestaurant("Zebra Flavor", 1098771, "Paco Perea");
		adminSystem.addRestaurant("Sushi Green", 4641651, "Dongjoon Lee");
		adminSystem.addRestaurant("Sushi Market", 87494, "Bee Song Yu");
		adminSystem.addRestaurant("PF Changs", 8854201, "Albert Morikawa");
		System.out.println("Before");
		System.out.println(adminSystem.showRestaurants());
		adminSystem.orderRestaurants();
		System.out.println("After");
		System.out.println(adminSystem.showRestaurants());*/
	
		
		adminSystem.addClient(IdType.PASSPORT, 4646511,"Collin Sherman", 2128885471L,"Elms Street 342");
		adminSystem.addClient(IdType.ID, 9848486,"Dustin Jhonson",7874541232L ,"Carmelo Dr 876");
		adminSystem.addClient(IdType.LICENSE, 42487212,"Bryson DeChambeau",3187287349L ,"Natty Ave 784");
		adminSystem.addClient(IdType.LICENSE, 42487212,"Carson DeChambeau",3187287349L ,"Natty Ave 784");
		adminSystem.addClient(IdType.LICENSE, 42487212,"Sebastian Muñoz", 4795683241L,"Colo Street 151");
		System.out.println("ordered by name");
		System.out.println(adminSystem.showClients());
		
		
		
	
		/*
		clientAdder();
		System.out.println(adminSystem.showClients());
		restaurantAdder();
		System.out.println(adminSystem.showRestaurants());
		productAdder();
		System.out.println(adminSystem.showProducts(adminSystem.getRestaurants().get(0)));*/

		//Saving
		try {
			adminSystem.saveRestaurants();
			adminSystem.saveProducts();
			adminSystem.saveClients();
		} catch (ClassNotFoundException | IOException e) {
			
			e.printStackTrace();
		}
	}

}
