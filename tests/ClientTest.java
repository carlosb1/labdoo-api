/*
 *  Labdoo API
    Copyright (C) 2012  Labdoo team

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
import java.util.Date;

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
		client = new LabdooClient("http://dummy.test","asddadadasd999887");
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}

	@Test
	public void testSayHello() throws APIException {
		client.sayHello();
		
	}
	
	@Test
	public void testAddRemoveLaptop() throws APIException {
		Laptop laptop = new Laptop();
		laptop.setStatus(Laptop.TAGGED_S0);
		laptop.setA501c3Recip("");
		laptop.setCpu(1);
		laptop.setCpuType(Laptop.CPU_FIVE);
		laptop.setCurrentManager("carlos.baez");
		laptop.setCurrentOS("");

		String nid = client.addLaptop(laptop);
		
//		log.info("Response test: "+nid);
		boolean result = client.deleteLaptop(nid);
		Assert.assertTrue(result);		
//		log.info("It was deleted: "+result);
		
	}
	
	@Test
	public void testGetLaptop() throws APIException {
		String nid = "464";
		Laptop newLaptop = client.getLaptop(nid);
		Assert.assertEquals(null,newLaptop);
//		log.info(newLaptop.getId());
//		log.info(newLaptop.toString());
		
	}
	
	@Test
	public void testDatesLaptop() throws APIException {
		Laptop laptop = new Laptop();
		laptop.setStatus(Laptop.TAGGED_S0);
		Date dateDelivered = new Date();
		dateDelivered.setHours(12);
		dateDelivered.setMinutes(45);
		dateDelivered.setSeconds(10);
		
		dateDelivered.setMonth(3);
		dateDelivered.setYear(2012);
		
		laptop.setDateDelivered(dateDelivered);
		Date dateReceived = new Date();
		dateReceived.setHours(1);
		dateReceived.setMinutes(41);
		dateReceived.setSeconds(10);
		dateReceived.setMonth(5);
		dateReceived.setYear(2012);
		laptop.setDateReceived(dateReceived);
		
		Date dateRecycled = new Date();
		dateRecycled.setHours(1);
		dateRecycled.setMinutes(35);
		dateRecycled.setSeconds(10);
		dateRecycled.setMonth(4);
		dateRecycled.setYear(2012);
		laptop.setDateRecycled(dateRecycled);
		String nid = client.addLaptop(laptop);
		
		//Check date laptop
		
//		Laptop laptop = client.getLaptop(nid);
//		log.info(laptop);
//		log.info("Response test: "+nid);
		boolean result = client.deleteLaptop(nid);
//		log.info("It was deleted: "+result);		
		Assert.assertTrue(result);		
	
		
		
	}
	


	@Test
	public void testUpdateLaptop() throws APIException {
		Laptop laptop = new Laptop();
		laptop.setStatus(Laptop.TAGGED_S0);
		String nid = client.addLaptop(laptop);
		boolean updated = client.updateLaptop(laptop);
		Assert.assertTrue(updated);		
			
		//Check date laptop
		
//		Laptop Laptop = client.getLaptop(nid);
//		log.info(laptop);
//		log.info("Response test: "+nid);
		boolean result = client.deleteLaptop(nid);
//		log.info("It was deleted: "+result);		
		Assert.assertTrue(result);		
		
		
	}	
	
	

}
