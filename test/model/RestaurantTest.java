package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestaurantTest {

	/*private Restaurant r ;

	public void setupStage1() {
		r= new Restaurant("Burger King", 9852564, "Bobby Pujols");	
		
	}*/
	
	//Test restaurant creation
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
		//setupStage1();
	}
	
	

}
