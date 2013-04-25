package api.v2;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import api.resources.Laptop;

public class MockXmlRpcClient extends XmlRpcClient {

	private final Log log = LogFactory.getLog(MockXmlRpcClient.class);

	@Override
	public Object execute(String pMethodName, List pParams) throws XmlRpcException {

		Map<String, Object> response = new HashMap<String, Object>();

		switch (pMethodName) {
		case LabdooClient.METHOD_NODE_CREATE:
			response.put("nid", "1");
			log.info("Creating node....");
			break;
		case LabdooClient.METHOD_NODE_DELETE:
			log.info("Deleting node....");
			break;

		case LabdooClient.METHOD_NODE_RETRIEVE:
			response = newLaptop(1).toMap();
			break;

		case LabdooClient.METHOD_NODE_UPDATE:
			log.info("Updating node....");
			break;
		case LabdooClient.METHOD_NODE_LIST:
			for (int i = 1; i < 5; i++) {
				response.put(Integer.toString(i), newLaptop(i).toMap());
			}
			log.info("Updating node....");
			break;
		case LabdooClient.METHOD_USER_CREATE:
			response.put("nid", "1");
			log.info("Creating node....");
			break;

		default:
			log.info("last hello");

		}

		return response;
	}

	private static Laptop newLaptop(int id) {
		Laptop laptop = new Laptop();
		laptop.setNid(Integer.toString(id));
		laptop.setStatus(Laptop.TAGGED_S0);
		Date dateDelivered = new Date();
		dateDelivered.setHours(12);
		dateDelivered.setMinutes(45);
		dateDelivered.setSeconds(10);
		dateDelivered.setDate(1);
		dateDelivered.setMonth(3);
		dateDelivered.setYear(2012);

		laptop.setDateDelivered(dateDelivered);
		Date dateReceived = new Date();
		dateReceived.setHours(1);
		dateReceived.setMinutes(41);
		dateReceived.setSeconds(10);
		dateReceived.setDate(1);

		dateReceived.setMonth(5);
		dateReceived.setYear(2012);
		laptop.setDateReceived(dateReceived);

		Date dateRecycled = new Date();
		dateRecycled.setHours(1);
		dateRecycled.setMinutes(35);
		dateRecycled.setSeconds(10);
		dateReceived.setDate(1);
		dateRecycled.setMonth(4);
		dateRecycled.setYear(2012);
		laptop.setDateRecycled(dateRecycled);

		Date availableDay = new Date();
		availableDay.setHours(1);
		availableDay.setMinutes(35);
		availableDay.setSeconds(10);
		availableDay.setDate(1);
		laptop.setAvailableDay(availableDay);

		availableDay.setMonth(5);
		availableDay.setYear(2012);
		laptop.setDateRecycled(dateRecycled);

		laptop.setA501c3Recip("");
		laptop.setCpu(1);
		laptop.setCpuType(Laptop.CPU_FIVE);
		laptop.setCurrentOS("EduUbuntu");
		laptop.setSerialNumber("1111111");

		laptop.setDestination("destination1");

		laptop.setDevType(Laptop.TYPE_DESKTOP);
		laptop.setHardDrive(500);
		laptop.setHowDidYouLearn("For a friend");
		laptop.setLaptopDomain(Laptop.DOMAIN_CASASITO);
		laptop.setMemory(100);
		laptop.setLibraryNotification("library_notification");
		HashMap<String, String> location = new HashMap<String, String>();
		location.put("latitude", "38.889139");
		location.put("longitude", "-77.049");
		laptop.setLocation(location);
		laptop.setModel("dell mock laptop");
		laptop.setNotes("MOCK NOTES");
		return laptop;
	}

}
