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
	
	@Test
	public void testGetLaptop() throws APIException {
		String nid = "464";
		Laptop newLaptop = client.getLaptop(nid);
		log.info(newLaptop.toString());
		
	}
	

	
	
	
	//TODO ADD TESTS AND DO JAVADOC
	
//	@Test
//	public void testUpdateLaptop() throws APIException {
//		Laptop laptop = client.addLaptop(Laptop.newSimpleLaptop());
//		laptop.setDescription("new description");
//		Laptop updatedLaptop = client.updateLaptop(laptop);
//		Assert.assertEquals("Laptops are not equals",laptop, updatedLaptop);
//		
//	}
//	
//	@Test
//	public void testRemoveLaptop() throws APIException {
//		Laptop laptop = client.addLaptop(Laptop.newSimpleLaptop());
//		client.removeLaptop(laptop.getId());
//		Laptop removedLaptop = client.getLaptop(laptop.getId());	
//		Assert.assertNotSame(Laptop.EMPTYLAPTOP,removedLaptop);
//		
//	}
	

}
