package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestaurantTest {

	private Restaurant r ;

	public void setupStage1() {
		r= new Restaurant("Burger King", 9852564, "Bobby Pujols");	
		
	}
	
	
	@Test
	public void testRestaurant() {
		String name= "McDonalds";
		int taxId=889456;
		String managerName= "Ricky Porter";
		Restaurant res=new Restaurant(name, taxId, managerName);
		assertEquals(name,res.getName(),"The restaurant name is incorrect");
		assertEquals(taxId,res.getTaxID(),"The restaurant tax ID is incorrect");
		assertEquals(managerName,res.getManagerName(),"The restaurant's manager name is incorrect");
	}
	
	@Test
	public void testAddProduct() {
		setupStage1();
		int code=1547;
		String name= "Cheese Burger";
		String description= "150g of angus meat with cheese";
		double price = 6.99;
		r.addProduct(code, name, description, price);
		assertEquals(1,r.getMenuItems().size(),"The menu items size is incorrect");
		assertEquals(code,r.getMenuItems().get(0).getCode(),"The product code is incorrect");
		assertEquals(name,r.getMenuItems().get(0).getName(),"The product name is incorrect");
		assertEquals(description,r.getMenuItems().get(0).getDescription(),"The product description is incorrect");
		assertEquals(price,r.getMenuItems().get(0).getPrice(),"The product price is incorrect");
		assertEquals(r.getTaxID(),r.getMenuItems().get(0).getResTaxID(),"The product restaurant TaxID is incorrect");
		
	}
	
	

}
