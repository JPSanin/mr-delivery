package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.ClientNotFoundException;
import exceptions.ProductNotFoundException;
import exceptions.RestaurantNotFoundException;
import model.AdminSystem;
import model.Client;
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
	public void restaurantUpdater() {
		int taxID=0;
		int index = 0;
		int option;
		try {
			System.out.println("Please enter the restaurant's tax ID");
			taxID=Integer.parseInt(br.readLine());

			try {
				index=adminSystem.updateRestaurant(taxID);
				do {
					System.out.println("Please select the information you want to update");
					System.out.println("1) Restaurant Name");
					System.out.println("2) Restaurant Tax Id");
					System.out.println("3) Restaurant's Manager Name");
					System.out.println("4) Exit");
					option= Integer.parseInt(br.readLine());

					switch(option) {
					case 1: 
						System.out.println("Please enter new restaurant name");
						String rn= br.readLine();
						adminSystem.getRestaurants().get(index).setName(rn);
						System.out.println("Restaurant name updated succesfully to: "+rn);
						break;
					case 2:
						System.out.println("Please enter new tax ID");
						int tax= Integer.parseInt(br.readLine());
						adminSystem.getRestaurants().get(index).setTaxID(tax);
						System.out.println("Restaurant taxID updated succesfully to: "+tax);
						break;
					case 3:
						System.out.println("Please enter new Manager name");
						String mang= br.readLine();
						adminSystem.getRestaurants().get(index).setManagerName(mang);
						System.out.println("Restaurant Manager name updated succesfully to: "+mang);
						break;	
					}

				}while(option!=4);
				adminSystem.orderRestaurants();
			} catch (RestaurantNotFoundException rnfe) {
				// TODO Auto-generated catch block
				System.err.print(rnfe);
				rnfe.printStackTrace();
			}


		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void productUpdater() {
		int code=0;
		int index1 = 0;
		int index2 = 0;
		
		
		int option;
		try {

				System.out.println("Please select the restaurant whose product's you want to update");
				System.out.println(adminSystem.showRestaurantsOptions());
				
				index1= Integer.parseInt(br.readLine());
			
			
			System.out.println("Please enter the products's code");
			code=Integer.parseInt(br.readLine());
			try {
				index2=adminSystem.updateProduct(code,index1);
				do {
					/*
					 * 	private int code;
						private String name;
						private String description;
						private double price;
						private int resTaxID;*/
					System.out.println("Please select the information you want to update");
					System.out.println("1) Product Code");
					System.out.println("2) Product Name");
					System.out.println("3) Product Description");
					System.out.println("4) Product Price");
					System.out.println("5) Exit");
					option= Integer.parseInt(br.readLine());

					switch(option) {
					case 1: 
						System.out.println("Please enter new product code");
						int c=Integer.parseInt(br.readLine());
						adminSystem.getRestaurants().get(index1).getMenuItems().get(index2).setCode(c);
						System.out.println("Product code updated succesfully to: "+c);
						break;
					case 2:
						System.out.println("Please enter new product name");
						String name= br.readLine();
						adminSystem.getRestaurants().get(index1).getMenuItems().get(index2).setName(name);
						System.out.println("Product name updated succesfully to: "+name);
						break;
					case 3:
						System.out.println("Please enter new Product description");
						String desc= br.readLine();
						adminSystem.getRestaurants().get(index1).getMenuItems().get(index2).setDescription(desc);
						System.out.println("Product description updated succesfully to: "+desc);
						break;	
					case 4:
						System.out.println("Please enter new Product price");
						Double p= Double.parseDouble(br.readLine());
						adminSystem.getRestaurants().get(index1).getMenuItems().get(index2).setPrice(p);
						System.out.println("Product description updated succesfully to: "+p);
						break;
					}

				}while(option!=5);
				
			} catch (ProductNotFoundException pnfe) {
				// TODO Auto-generated catch block
				System.err.print(pnfe);
				pnfe.printStackTrace();
			}




		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clientUpdater() {
		int docNum=0;
		int index = 0;
		int option;
		try {
			System.out.println("Please enter the client's Document Number");
			docNum=Integer.parseInt(br.readLine());

			try {
				index=adminSystem.updateClient(docNum);
				do {
					 
					System.out.println("Please select the client's information you want to update");
					System.out.println("1) Client Name");
					System.out.println("2) Client Document Type");
					System.out.println("3) Client Document Number");
					System.out.println("4) Client Phone Number");
					System.out.println("5) Client adress");
					System.out.println("6) Exit");
					option= Integer.parseInt(br.readLine());

					switch(option) {
					case 1: 
						System.out.println("Please enter client's new name");
						String cn= br.readLine();
						adminSystem.getClients().get(index).setName(cn);
						System.out.println("Client name updated succesfully to: "+cn);
						break;
					case 2:
						int option2;
						System.out.println("Please select new Document Type");
						System.out.println("1) Passport");
						System.out.println("2) ID");
						System.out.println("3) License");
						option2= Integer.parseInt(br.readLine());
						switch(option2) {
						case 1:
							adminSystem.getClients().get(index).setIdType(IdType.PASSPORT);
							System.out.println("Client document type updated succesfully to: Passport");
							break;
						case 2:
							adminSystem.getClients().get(index).setIdType(IdType.ID);
							System.out.println("Client document type updated succesfully to: ID");
							break;
						case 3:
							adminSystem.getClients().get(index).setIdType(IdType.LICENSE);
							System.out.println("Client document type updated succesfully to: License");
							break;
						}
						break;
					case 3:
						System.out.println("Please enter Client's new document number");
						int dn= Integer.parseInt(br.readLine());
						adminSystem.getClients().get(index).setIdNumber(dn);;
						System.out.println("Client's document number updated succesfully to: "+dn);
						break;	
					case 4:
						System.out.println("Please enter Client's new phone number (10 digit)");
						Long pn= Long.parseLong(br.readLine());
						adminSystem.getClients().get(index).setPhoneNumber(pn);;
						System.out.println("Client's phone number updated succesfully to: "+pn);
						break;	
					case 5: 
						System.out.println("Please enter client's new address");
						String ad= br.readLine();
						adminSystem.getClients().get(index).setAddress(ad);
						System.out.println("Client's address updated succesfully to: "+ad);
						break;
					}

				}while(option!=6);
				Client c=adminSystem.getClients().get(index);
				adminSystem.getClients().remove(index);
				adminSystem.addClient(c.getIdType(), c.getIdNumber(), c.getName(), c.getPhoneNumber(), c.getAddress());
				
			} catch (ClientNotFoundException cnfe) {
				// TODO Auto-generated catch block
				System.err.print(cnfe);
				cnfe.printStackTrace();
			}


		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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

			
				System.out.println(adminSystem.showRestaurantsOptions());
				System.out.println(exit+")"+" Exit");
			
			

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
		
		//productAdder();
		//System.out.println(adminSystem.showProducts(adminSystem.getRestaurants().get(2)));
		//productUpdater();
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

		
		
		/*
		System.out.println(adminSystem.showRestaurants());
		restaurantUpdater();
		System.out.println(adminSystem.showRestaurants());*/
		/*adminSystem.addClient(IdType.PASSPORT, 4646511,"Collin Sherman", 2128885471L,"Elms Street 342");
		adminSystem.addClient(IdType.ID, 9848486,"Dustin Jhonson",7874541232L ,"Carmelo Dr 876");
		adminSystem.addClient(IdType.LICENSE, 42487212,"Bryson DeChambeau",3187287349L ,"Natty Ave 784");
		adminSystem.addClient(IdType.LICENSE, 42487212,"Carson DeChambeau",3187287349L ,"Natty Ave 784");
		adminSystem.addClient(IdType.LICENSE, 42487212,"Sebastian Muñoz", 4795683241L,"Colo Street 151");
		System.out.println("ordered by name");
		System.out.println(adminSystem.showClients());*/


		System.out.println(adminSystem.showClients());
		clientUpdater();
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
