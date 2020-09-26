package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.ClientNotFoundException;
import exceptions.OrderNotFoundException;
import exceptions.ProductNotFoundException;
import exceptions.RestaurantNotFoundException;
import model.AdminSystem;
import model.Client;
import model.IdType;
import model.Product;
import model.Status;


public class Menu {

	private AdminSystem adminSystem;
	private BufferedReader br;

	public Menu() {
		adminSystem= new AdminSystem();
		br= new BufferedReader(new InputStreamReader(System.in));
	}

	public void mainMenu() {
		int option=0;
		int exit=12;
		System.out.println("================================================");
		System.out.println("WELCOME TO MR.DELIVERY RESTAURANT MANAGEMENT APP");
		System.out.println("================================================");
		do {
			System.out.println("Please select the option that represents what you would like to do");
			System.out.println("1) Create Order");
			System.out.println("2) Change Order Status");
			System.out.println("3) Register Client");
			System.out.println("4) Register Restaurant");
			System.out.println("5) Add Products");
			System.out.println("6) Update Information");
			System.out.println("7) Display Restaurants Ordered by Name");
			System.out.println("8) Display Clients Ordered by Phone");
			System.out.println("9) Find a Client Efficiently");
			System.out.println("10) Export Data");
			System.out.println("11) Import Data");
			System.out.println("12) Exit Program");
			try {
				option=Integer.parseInt(br.readLine());
				switch (option) {
				
				case 1:
					orderCreater();
				break;
				case 2:
					changeOrderStatus();
					break;
				case 3:
					clientAdder();
					break;
				case 4:
					restaurantAdder();
					break;
				case 5:
					productAdder();
					break;
				case 6:
					updateInfo();
					break;
				case 7:
					adminSystem.orderRestaurants();
					System.out.println(adminSystem.showRestaurants());
					break;
				case 8:
					System.out.println(adminSystem.printOrderedClientsByPhone());
					break;
				case 9:
					efficientClientFinder();
					break;
				case 10:
					export();
					break;
				case 11:
					importMenu();
					break;
				}
				
				
			} catch (NumberFormatException | IOException e) {
				System.err.print(e);
				e.printStackTrace();
			}
			
		}while(option !=exit);
		
		System.out.println("Thank you for using Mr Delivery, See you soon :)");
	}
	
	
	public void efficientClientFinder() {
		System.out.println("Please enter the client Name (FirstName LastName)");
		try {
			String name= br.readLine();
			try {
				//Start time
				long startTime=System.currentTimeMillis();
				int index= adminSystem.searchClientName(name);
				long stopTime=System.currentTimeMillis();
				System.out.println("Client Found:");
				System.out.println(adminSystem.getClients().get(index));
				//End time
				long elapsedTime1=stopTime-startTime;
				double elapsedTime= (stopTime-startTime)/(double)1000;
				System.out.println("Time it took in milliseconds: "+ elapsedTime1);
				System.out.println("Time it took in seconds: "+ elapsedTime);
			} catch (ClientNotFoundException e) {
				System.err.print(e);
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			System.err.print(e);
			e.printStackTrace();
		}
	}
	
	public void changeOrderStatus() {
		int index=0;
		int index2=0;
		System.out.println("Changing order Status...");
		System.out.println("Please enter client document number");
		try {
			int cID= Integer.parseInt(br.readLine());
			try {
				index=adminSystem.searchClient(cID);
				System.out.println("Please enter order code");
				int code= Integer.parseInt(br.readLine());
				
				try {
					index2=adminSystem.searchOrder(code,index);
					if(adminSystem.getClients().get(index).getOrder().get(index2).getStatus()==Status.DELIVERED) {
						System.out.println("The order has already been delivered cannot change status");
					}else {
						adminSystem.getClients().get(index).getOrder().get(index2).modifyStatus();
						System.out.println("Order status changed successfully to: "
						+adminSystem.getClients().get(index).getOrder().get(index2).getStatus());
					}
					
				} catch (OrderNotFoundException e) {
					System.err.print(e);
					e.printStackTrace();
				}
			} catch (ClientNotFoundException e) {
				System.err.print(e);
				e.printStackTrace();
			}
		} catch (NumberFormatException | IOException e) {
			System.err.print(e);
			e.printStackTrace();
		}
	}
	
	public void orderCreater() {
		
		int option=0;
		
		int index1=0;
		int index2=0;
		try {
			System.out.println("Order Creation Started...");
			do {
			System.out.println("Has the client already been registered");
			System.out.println("1) yes");
			System.out.println("2) no");
			option= Integer.parseInt(br.readLine());
			}while(option<0 || option>2);
			
			if (option==1) {
				System.out.println("Please enter client document number");
				int dc=Integer.parseInt(br.readLine());
				try {
					index1=adminSystem.searchClient(dc);
					do {
						
						System.out.println("Please select the restaurant you want to order from");
						System.out.println(adminSystem.showRestaurantsOptions());
						index2= Integer.parseInt(br.readLine());
						
					}while(index2>adminSystem.getRestaurants().size() || index2<0);
					
					index2-=1;
					int id=adminSystem.getClients().get(index1).getIdNumber();
					int resID=adminSystem.getRestaurants().get(index2).getTaxID();
					adminSystem.getClients().get(index1).addOrder(id, resID);
					int orderNumber=adminSystem.getClients().get(index1).getOrder().size()-1;
					adminSystem.getClients().get(index1).getOrder().get(orderNumber).generateCode(adminSystem.getClients());
					addProductToOrder(index1, index2, orderNumber);
					
					System.out.println("Order Created Successfully:");
					System.out.println(adminSystem.getClients().get(index1).getOrder().get(orderNumber));
				} catch (ClientNotFoundException e) {
					System.err.print(e);
					e.printStackTrace();
				}
			}else if(option==2){
				System.out.println("Please Register Client");
				clientAdder();
			}

		} catch (IOException | NumberFormatException e) {
			System.err.print(e);
			e.printStackTrace();
		}

	}
	
	public void addProductToOrder(int index1, int index2, int orderNumber) throws NumberFormatException, IOException {
		
		int selection=0;
		int exit=0;
		int quantity=0;
		boolean orderAgain=true;
		int again=0;
	
		exit=adminSystem.getRestaurants().get(index2).getMenuItems().size()+1;
		
		do {
			
			System.out.println("Please select products to add to order");
			System.out.println(adminSystem.showProducts(adminSystem.getRestaurants().get(index2)));
			System.out.println(exit+")"+"Exit");
			selection= Integer.parseInt(br.readLine());
			if(selection!=exit && selection<exit && selection>0) {
				
				System.out.println("Please enter how many of this product would you like to order");
				quantity=Integer.parseInt(br.readLine());
				Product p=adminSystem.getRestaurants().get(index2).getMenuItems().get(selection-1);
				adminSystem.getClients().get(index1).getOrder().get(orderNumber).addProduct(p,quantity);
				do {
					System.out.println("Would you like to add another product?");
					System.out.println("1) Yes");
					System.out.println("2) No");
					again= Integer.parseInt(br.readLine());
				}while (again<0 || again>2);
				if (again==1) {
					orderAgain=true;
				}else if(again==2){
					orderAgain=false;
				}
				
			}
			
			
		}while(selection!=exit && orderAgain==true);
		System.out.println("Order creation finished");
		
	}
	
	public void orderUpdater() {
		int code=0;
		int index1 = 0;
		int index2 = 0;
		int select=0;
		int select2=0;
		
		int option;
		try {
			System.out.println("Please enter the client document number of the person whose order you want to update");
			int docNum= Integer.parseInt(br.readLine());
			
			try {
				index1=adminSystem.searchClient(docNum);
				System.out.println("Please enter the order's code");
				code=Integer.parseInt(br.readLine());
				try {
					index2=adminSystem.searchOrder(code,index1);
					
					do {
						
						System.out.println("Please select the information you want to update");
						System.out.println("1) Order Code");
						System.out.println("2) Order Products Quantity");
						System.out.println("3) Add Product");
						System.out.println("4) Remove Product");
						System.out.println("5) Exit");
						option= Integer.parseInt(br.readLine());

						switch(option) {
						case 1: 
							System.out.println("Please enter new order code");
							int c=Integer.parseInt(br.readLine());
							adminSystem.getClients().get(index1).getOrder().get(index2).setCode(c);
							System.out.println("Product code updated succesfully to: "+c);
							break;
						case 2:
							System.out.println("Please select product to update quantity");
							int exitProducts=adminSystem.getClients().get(index1).getOrder().get(index2).getProducts().size()+1;
							do {
								String ps=adminSystem.getClients().get(index1).getOrder().get(index2).showProducts();
								System.out.println(ps);
								System.out.println(exitProducts+") Exit");
								select=Integer.parseInt(br.readLine());
							}while(select>exitProducts || select<0 );
							select-=1;
							System.out.println("Please enter the new product quantity");
							int pq= Integer.parseInt(br.readLine());
							adminSystem.getClients().get(index1).getOrder().get(index2).getProductQuantities().set(select,pq);
							System.out.println("Product quantity updated succesfully to: "+pq);
							
							break;
						case 3:
							int resTaxId=adminSystem.getClients().get(index1).getOrder().get(index2).getResTaxId();
							int resPos;
							try {
								resPos = adminSystem.searchRestaurant(resTaxId);
								addProductToOrder(index1, resPos, index2);
							} catch (RestaurantNotFoundException e) {
								System.err.print(e);
								e.printStackTrace();
							}
							
							break;	
						case 4:
							System.out.println("Please select product to remove");
							int exitProducts2=adminSystem.getClients().get(index1).getOrder().get(index2).getProducts().size()+1;
							do {
								String ps=adminSystem.getClients().get(index1).getOrder().get(index2).showProducts();
								System.out.println(ps);
								System.out.println(exitProducts2+") Exit");
								select2=Integer.parseInt(br.readLine());
							}while(select2>exitProducts2 || select2<0 );
							select2-=1;
					
							adminSystem.getClients().get(index1).getOrder().get(index2).getProducts().remove(select2);
							adminSystem.getClients().get(index1).getOrder().get(index2).getProductQuantities().remove(select2);
							System.out.println("Product removed succesfully");
							break;
						}

					}while(option!=5);
					
				} catch (OrderNotFoundException e) {
					System.err.print(e);
					e.printStackTrace();
				}
			} catch (ClientNotFoundException cnfe) {
				System.err.print(cnfe);
				cnfe.printStackTrace();
			}
			
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void restaurantUpdater() {
		int taxID=0;
		int index = 0;
		int option;
		try {
			System.out.println("Please enter the restaurant's tax ID");
			taxID=Integer.parseInt(br.readLine());

			try {
				index=adminSystem.searchRestaurant(taxID);
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
				do {
					System.out.println("Please select the restaurant whose product's you want to update");
					System.out.println(adminSystem.showRestaurantsOptions());
					
					index1= Integer.parseInt(br.readLine());
				}while(index1>adminSystem.getRestaurants().size() && index1<0);
				index1-=1;
			
			
			System.out.println("Please enter the product's code");
			code=Integer.parseInt(br.readLine());
			try {
				index2=adminSystem.searchProduct(code,index1);
				do {
					
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
				index=adminSystem.searchClient(docNum);
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
	
	public void updateInfo() {
		int option=0;
		
		do {
			System.out.println("Please select the element you would like to update");
			System.out.println("1) Update Restaurant");
			System.out.println("2) Update Product");
			System.out.println("3) Update Client");
			System.out.println("4) Update Order");
			System.out.println("5) Exit");
			
			try {
				option=Integer.parseInt(br.readLine());
				switch (option) {
				
				case 1:
					restaurantUpdater();
				break;
				case 2:
					productUpdater();
					break;
				case 3:
					clientUpdater();
					break;
				case 4:
					orderUpdater();
					break;
			
				}
				
				
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(option!=5);
		
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
			System.out.println("Client added succesfully");
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
			System.out.println("Restaurant added successfully");
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
					System.out.println("Restaurant added successfully");
				}

			} catch (IOException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}while(option!=exit);
	}

	public void importMenu() {
		
		int option=0;
		int exit=5;
		do {
			System.out.println("Please select what you would like to import");
			System.out.println("1) Restaurants");
			System.out.println("2) Products");
			System.out.println("3) Clients");
			System.out.println("4) Orders");
			System.out.println("5) Exit");
			try {
				option=Integer.parseInt(br.readLine());
				
				switch(option) {	
				case 1:
					System.out.println("Please enter the file path");
					String path= br.readLine();
					adminSystem.importRestaurants(path);
					System.out.println("Data imported successfully");
					break;
				case 2:
					System.out.println("Please enter the file path");
					String path2= br.readLine();
					System.out.println("Please enter the restaurant tax ID to import products");
					int taxID= Integer.parseInt(br.readLine());
					try {
						adminSystem.importProducts(path2,taxID);
						System.out.println("Data imported successfully");
					} catch (RestaurantNotFoundException e) {
						System.err.print(e);
						e.printStackTrace();
					}
					
					break;
				case 3:
					System.out.println("Please enter the file path");
					String path3= br.readLine();
					adminSystem.importClients(path3);
					System.out.println("Data imported successfully");
					break;
				case 4:
					System.out.println("Please enter the file path");
					String path4= br.readLine();
					int[] cantAdd=adminSystem.importOrders(path4);
					System.out.println("Data imported successfully");
					System.out.println(cantAdd[0]+ " Orders weren't added because the client was not found");
					System.out.println(cantAdd[1]+ " Orders weren't added because the restaurant was not found");
					break;
				}
				
				
			} catch (NumberFormatException | IOException e) {
				System.err.print(e);
				e.printStackTrace();
			}
			
		}while(option !=exit);
	}
	
	public void export() {
		System.out.println("Please enter the separator you would like to use");
		String sep;
		try {
			sep = br.readLine();
			char separator=sep.charAt(0);
			adminSystem.addAllOrders();
			adminSystem.orderOrdersForExport();
			adminSystem.exportOrders(separator);
			System.out.println("Orders exported succesfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void start() {
		//Loading
		try {
			adminSystem.loadRestaurants();
		
			adminSystem.loadClients();
		
		} catch (ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}
		
		mainMenu();
		
		//Saving
		try {
			adminSystem.saveRestaurants();
			
			adminSystem.saveClients();
			
		} catch (ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}
	}

}
