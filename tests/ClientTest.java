import static org.junit.Assert.*;
import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.APIException;
import api.LabdooClient;
import api.resources.Laptop;

//TODO Implements wrong cases
public class ClientTest {
	LabdooClient client = null;
	final Log log = LogFactory.getLog(ClientTest.class);

	@Before
	public void setUp() throws Exception {
		client = new LabdooClient();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}

	@Test
	public void testSayHello() throws APIException {
		String response = client.sayHello();
		Assert.assertEquals("Messages are not equals","Hello, carlos!", response);
		
	}
	
	@Test
	public void testAddRemoveLaptop() throws APIException {
		String nid = client.addLaptop(Laptop.newSimpleLaptop());
		log.info("Response test: "+nid);
		boolean result = client.deleteLaptop(nid);
		log.info("It was deleted: "+result);
		
	}
	
//	@Test
//	public void testGetLaptop() throws APIException {
//		Laptop laptop = client.addLaptop(Laptop.newSimpleLaptop("demo"));
//		Laptop newLaptop = client.getLaptop(laptop.getId());
//		Assert.assertEquals("The name is not correct",laptop.getName(),newLaptop.getName());
//		Assert.assertEquals("The  STATUS is not well initialized",laptop.getSTATUS(), newLaptop.getSTATUS());		
//		Assert.assertEquals("The Id is not equals",laptop.getId(),newLaptop.getId());
//		Assert.assertEquals("The description is not equals",laptop.getDescription(),newLaptop.getDescription());
//		Assert.assertEquals("The owner is not equals",laptop.getOwner(),newLaptop.getOwner());
//		Assert.assertEquals("The project is not equals",laptop.getProject(),newLaptop.getProject());
//		
//	}
//	
//	
//	@Test
//	public void testUpdateLaptop() throws APIException {
//		Laptop laptop = client.addLaptop(Laptop.newSimpleLaptop("demo"));
//		laptop.setDescription("new description");
//		Laptop updatedLaptop = client.updateLaptop(laptop);
//		Assert.assertEquals("Laptops are not equals",laptop, updatedLaptop);
//		
//	}
//	
//	@Test
//	public void testRemoveLaptop() throws APIException {
//		Laptop laptop = client.addLaptop(Laptop.newSimpleLaptop("demo"));
//		client.removeLaptop(laptop.getId());
//		Laptop removedLaptop = client.getLaptop(laptop.getId());	
//		Assert.assertNotSame(Laptop.EMPTYLAPTOP,removedLaptop);
//		
//	}
	

}
