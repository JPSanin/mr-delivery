package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class OrderTest {

	
	private Order o ;

	/**
	 * Setup stage 1.
	 */
	public void setupStage1() {
		o= new Order(141422, 665544);
		
	}
	
	
	/**
	 * Test order.
	 */
	@Test
	public void testOrder() {
		setupStage1();
		int clientID=564654;
		int taxId=547623;
		Order order= new Order(clientID,taxId);
		assertEquals(clientID,order.getClientID(),"The order client ID is incorrect");
		assertEquals(taxId,order.getResTaxId(),"The order restaurant tax ID is incorrect");
		assertEquals(Status.REQUESTED,order.getStatus(),"The order status is incorrect");
	
	}
	
	/**
	 * Test add product.
	 */
	@Test
	public void testAddProduct() {
		setupStage1();
		int code=1547;
		String name= "Cheese Burger";
		String description= "150g of angus meat with cheese";
		double price = 6.99;
		Product p= new Product(code, name, description, price, 665544);
		int quantity=4;
		o.addProduct(p,quantity);
		assertEquals(1,o.getProducts().size(),"The order products size is incorrect");
		assertEquals(1,o.getProductQuantities().size(),"The product quantities size is incorrect");
		assertEquals(code,o.getProducts().get(0).getCode(),"The product code is incorrect");
		assertEquals(name,o.getProducts().get(0).getName(),"The product name is incorrect");
		assertEquals(description,o.getProducts().get(0).getDescription(),"The product description is incorrect");
		assertEquals(price,o.getProducts().get(0).getPrice(),"The product price is incorrect");
		assertEquals(665544,o.getProducts().get(0).getResTaxID(),"The product restaurant TaxID is incorrect");
		assertEquals(quantity,o.getProductQuantities().get(0),"The product quantity is incorrect");
	}
}
