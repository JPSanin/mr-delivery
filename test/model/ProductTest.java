package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class ProductTest {


	private Product p ;

	/**
	 * Setup stage 1.
	 */
	public void setupStage1() {
		p= new Product(962, "French Fries", "Classic French Fries", 3.99, 72703);
		
	}
	
	
	/**
	 * Test product.
	 */
	@Test
	public void testProduct() {
		int code=1547;
		String name= "Cheese Burger";
		String description= "150g of angus meat with cheese";
		double price = 6.99;
		int resTaxID=421077;
		Product product= new Product(code, name, description, price, resTaxID);
		assertEquals(code,product.getCode(),"The product code is incorrect");
		assertEquals(name,product.getName(),"The product name is incorrect");
		assertEquals(description,product.getDescription(),"The product description is incorrect");
		assertEquals(price,product.getPrice(),"The product price is incorrect");
		assertEquals(resTaxID,product.getResTaxID(),"The product restaurant TaxID is incorrect");
	
	}
	
	/**
	 * Test to string.
	 */
	@Test
	public void testToString() {
		setupStage1();
		String s= p.toString();
		assertEquals(s,p.toString(),"The product string incorrect");
		
		
	}

}
