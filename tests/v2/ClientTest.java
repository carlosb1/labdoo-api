package v2;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import api.APIException;
import api.resources.Laptop;
import api.resources.User;
import api.v2.LabdooClient;

//TODO Implements wrong cases
public class ClientTest {
	LabdooClient client = null;
	final Log log = LogFactory.getLog(ClientTest.class);

	@Before
	public void setUp() throws Exception {
		// client = new LabdooClient("http://domain.com/test");
		client = new LabdooClient("mock://example/test");
		client.login("user", "pass");

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
	public void testCreateUser() throws APIException {
		User user = new User();
		user.setName("Foo Bar");
		user.setUsername("foobar");
		user.setEmail("mail@mail.com");
		user.setPassword("pass");
		user.setPostal_code("111");
		user.setSkills("Programming");
		user.setState_province("Sprinfield");
		user.setSome_words_like("");
		user.setStreet("Sweet street");
		List<String> volunteering_interests = new ArrayList<String>();
		volunteering_interests.add("music");
		user.setVolunteering_interests(volunteering_interests);

		String nid = client.createNewUser(user);

		Assert.assertTrue(nid != null);
		// log.info("It was deleted: "+result);

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
		laptop.setSerialNumber("1111111");

		String nid = client.addLaptop(laptop);

		// log.info("Response test: "+nid);
		boolean result = client.deleteLaptop(nid);
		Assert.assertTrue(result);
		// log.info("It was deleted: "+result);

	}

	@Test
	public void testGetLaptop() throws APIException {
		String nid = "464";
		Laptop newLaptop = client.getLaptop(nid);
		// FIXME fix this test
		// Assert.assertEquals(null, newLaptop);
		// log.info(newLaptop.getId());
		// log.info(newLaptop.toString());

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

		// Check date laptop

		// Laptop laptop = client.getLaptop(nid);
		// log.info(laptop);
		// log.info("Response test: "+nid);
		boolean result = client.deleteLaptop(nid);
		// log.info("It was deleted: "+result);
		Assert.assertTrue(result);

	}

	@Test
	public void testUpdateLaptop() throws APIException {
		Laptop laptop = new Laptop();
		laptop.setStatus(Laptop.TAGGED_S0);
		String nid = client.addLaptop(laptop);
		boolean updated = client.updateLaptop(laptop);
		Assert.assertTrue(updated);

		// Check date laptop

		// Laptop Laptop = client.getLaptop(nid);
		// log.info(laptop);
		// log.info("Response test: "+nid);
		boolean result = client.deleteLaptop(nid);
		// log.info("It was deleted: "+result);
		Assert.assertTrue(result);

	}

	@Test
	public void testListLaptops() throws APIException {
		Map<String, String> filters = new HashMap<String, String>();
		// TODO check these names and operations in the real API from the web
		filters.put("status", Laptop.ASSIGNED_S3);
		List<Laptop> results = client.listLaptops(filters);
		Assert.assertTrue(results.size() > 0);
	}
}
