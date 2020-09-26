package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClientTest {

	private Client c ;

	public void setupStage1() {
		c= new Client(IdType.LICENSE, 564654,"Pedro Salazar",3187284546L,"421 Rocky rd");
		
	}
	
	
	@Test
	public void testClient() {
		IdType idType=IdType.PASSPORT;
		int id= 457812;
		String name= "Ricky Rubio";
		String checkName="Rubio Ricky";
		Long phone=5648881234L;
		String address="634 Buckingham St";
		Client client= new Client(idType, id, name, phone, address);
		assertEquals(idType,client.getIdType(),"The client idType is incorrect");
		assertEquals(checkName,client.getName(),"The client name is incorrect");
		assertEquals(id,client.getIdNumber(),"The client ID number is incorrect");
		assertEquals(phone,client.getPhoneNumber(),"The client phone number is incorrect");
		assertEquals(address,client.getAddress(),"The client address is incorrect");
	
	}
	
	@Test
	public void testAddOrder() {
		setupStage1();
		int clientID=564654;
		int taxId=547623;
		c.addOrder(clientID,taxId);
		assertEquals(1,c.getOrder().size(),"The orders size is incorrect");
		assertEquals(clientID,c.getOrder().get(0).getClientID(),"The order client ID is incorrect");
		assertEquals(taxId,c.getOrder().get(0).getResTaxId(),"The order restaurant tax ID is incorrect");
		assertEquals(Status.REQUESTED,c.getOrder().get(0).getStatus(),"The order status is incorrect");
		
	}
	
	

}
