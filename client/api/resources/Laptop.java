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
package api.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import api.APIException;

public class Laptop {
	static final Log log = LogFactory.getLog(Laptop.class);
	
	public final static String ASSIGNED_S3 = "Assigned to a child, waiting to be shipped (S3)";

	public final static String AVAILABLE_S8 = "(Library) Available (S8)";
	public final static String CHECKED_OUT_S7 = "(Library) Checked out (S7)";
	public final static String CPU_EIGHT = "8";
	public final static String CPU_FIVE = "5";
	public final static String CPU_FOUR = "4";

	// number of cpus
	public final static String CPU_ONE = "1 (Single core)";
	public final static String CPU_SEVEN = "7";
	public final static String CPU_SIX = "6";
	public final static String CPU_THREE = "3";
	public final static String CPU_TWO = "2 (Duo core)";
	public final static String DELIVERED_AND_BEING_USED_S4 = "Delivered and being used (S4)";
	public final static String DOMAIN_CASASITO = "Casasito, Guatemala";
	public final static String DOMAIN_EWB_MASSACHUSETTS = "Engineers Without Borders @ Western Massachusetts";
	public final static String DOMAIN_EWB_UCI = "Engineers Without Borders @ UCI";
	public final static String DOMAIN_FOXCONN = "Foxconn";
	public final static String DOMAIN_I2CAT = "I2CAT, Barcelona";
	// domain
	public final static String DOMAIN_INDIVIDUAL = "Individual volunteer (grassroots)";
	public final static String DOMAIN_MERCIMED = "MerciMed, New York";
	public final static String DOMAIN_SPRINGSOFT = "SpringSoft";
	public final static String DOMAIN_UCI = "University of California, Irvine";
	public final static String DOMAIN_UCLA = "University of California, Los Angeles";

	public final static String DOMAIN_WCE = "World Computer Exchange, Boston";
	public final static String DONATED_S1 = "Donated, in quality assurance process (S1)";
	public final static String FROM_CONFERENCE_OR_EVENT = "From a conference or Labdoo event";
	// HOW DID YOU LEARN
	public final static String FROM_FRIEND = "From a friend";
	public final static String FROM_HUB = "I belong to a Labdoo hub";
	public final static String FROM_INTERNET = "From the Internet";
	public final static String FROM_UCI_BOOKSTORE = "From the UCI bookstore";
	

	public final static String NULL = "null";
	public final static String PASSED_QUALITY_ASSURANCE_S2 = "Passed quality assurance (S2)";
	public final static String RECIP_EWB_ORANGE_COUNTY = "EWB Orange County";
	public final static String RECIP_EWB_UCI = "EWB UCI";
	public final static String RECIP_EWB_UCLA = "EWB UCLA";
	public final static String RECIP_EWB_WESTERN_MA = "EWB Western MA";

	// a501
	public final static String RECIP_NOT_KNOW = "Not known yet";
	public final static String RECIP_WCE = "World Computer Exchange";
	public final static String RECYCLED_S6 = "Recycled (S6)";
	// STATUS
	public final static String TAGGED_S0 = "Tagged with a Labdoo ID (S0)";
	public final static String TYPE_DESKTOP = "Desktop";
	public final static String TYPE_GOOGLE_PHONE = "Google phone";
	public final static String TYPE_INTERNET_TABLET = "Internet tablet";
	public final static String TYPE_IPAD = "iPad";
	public final static String TYPE_IPHONE = "iPhone";
	// devType
	public final static String TYPE_LAPTOP = "Laptop";
	public final static String WAITING_TO_BE_RECYCLED_S5 = "Waiting to be recycled (S5)";

	private static String dateToString(Date dateToFormat) {
		// 2010-08-20T00:00:00
		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		return dateFormater.format(dateToFormat);

	}

	private static Object getUIDParam(Object param) {
		Object[] arrayParam = (Object[]) param;
		Map hashParam = (Map) arrayParam[0];
		return hashParam.get("uid");

	}

	private static Object hashValueToParam(Object drupalParam)
			throws APIException {
		if (drupalParam instanceof Object[]
				&& ((Object[]) drupalParam).length > 0) {
			Object arrayParam = ((Object[]) drupalParam)[0];
			if (arrayParam instanceof Map
					&& ((Map) arrayParam).get("value") != null) {
				return ((Map) arrayParam).get("value");
			}

		}

		return drupalParam;
	}

	public static Laptop newInstance(Map<String, Object> params)
			throws APIException {
		Laptop laptop = new Laptop();
		Set<String> keys = params.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();

			if (key.equals("title"))
				laptop.setId((String) params.get(key));

			if (key.equals("nid"))
				laptop.setNid((String) params.get(key));

			if (key.equals("location") && params.get(key) != null)
				laptop.setLocation((HashMap) params.get(key));

			Object param = (Object) hashValueToParam(params.get(key));
			if (param == null || param.equals(""))
				continue;

			if (key.equals("field_how_did_you_learn"))
				laptop.setHowDidYouLearn((String) param);

			if (key.equals("field_current_manager"))
				laptop.setCurrentManager((String) getUIDParam(param));

			if (key.equals("field_model"))
				laptop.setModel((String) param);

			if (key.equals("field_cpu"))
				laptop.setCpu(((Integer) param).intValue());

			if (key.equals("field_cpu_type"))
				laptop.setCpuType((String) param);

			if (key.equals("field_memory"))
				laptop.setMemory(((Integer) param).intValue());

			if (key.equals("field_hard_drive integer"))
				laptop.setHardDrive(((Integer) param).intValue());

			if (key.equals("field_current_os"))
				laptop.setCurrentOS((String) param);

			if (key.equals("field_destination"))
				laptop.setDestination((String) param);

			if (key.equals("field_501c3_recipient"))
				laptop.setA501c3Recip((String) param);

			if (key.equals("field_laptop_domain"))
				laptop.setLaptopDomain((String) param);

			if (key.equals("field_status"))
				laptop.setStatus((String) param);

			if (key.equals("field_dev_type"))
				laptop.setDevType((String) param);

			if (key.equals("field_library_notification"))
				laptop.setLibraryNotification((String) param);

			if (key.equals("field_checkedout_location"))
				laptop.setCheckedoutLocation((String) param);

			if (key.equals("field_notes"))
				laptop.setNotes((String) param);
			
			//field_laptop_serial_number			
			if (key.equals("field_laptop_serial_number")) 
				laptop.setSerialNumber((String) param);


			if (key.equals("field_date_received")) {
				try {
					laptop.setDateReceived(stringToDate((String) param));
				} catch (Exception e) {
					log.error("It had a problem with the date received", e);
				}
			}
			if (key.equals("field_date_delivered")) {
				try {
					laptop.setDateDelivered(stringToDate((String) param));
				} catch (Exception e) {
					log.error("It had a problem with the date delivered", e);
				}
			}
			if (key.equals("field_available_day")) {
				try {
					laptop.setAvailableDay(stringToDate((String) param));
				} catch (Exception e) {
					log.error("It had a problem with the available day", e);
				}
			}
			if (key.equals("field_date_recycled")) {
				try {
					laptop.setDateRecycled(stringToDate((String) param));
				} catch (Exception e) {
					log.error("It had a problem with the date recycled", e);
				}

			}
			
			

		}
		return laptop;
	}


	private static Date stringToDate(String dateToParse) throws ParseException {
		// 2010-08-20T00:00:00

		SimpleDateFormat dateFormater = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		return dateFormater.parse(dateToParse);

	}

	private String a501c3Recip = "";
	private Date availableDay = null;

	private String checkedoutLocation = "";
	private int cpu = 0;
	private String cpuType = "";
	// TODO TO TEST, It WOULD BE AN USER ACCOUNT
	private String currentManager = "";
	private String currentOS = "";
	private Date dateDelivered = null;
	private Date dateReceived = null;
	private Date dateRecycled = null;
	private String destination = "";
	private String devType = "";
	private int hardDrive = 0;
	private String howDidYouLearn = "";
	private String id = "";

	private String laptopDomain = "";

	private String libraryNotification = "";

	private HashMap<String, String> location = new HashMap<String, String>();

	private int memory = 0;

	// //$newnode->field_picture->NULL;
	// //$newnode->field_pic_deployed->NULL;
	private String model = "";

	private String nid = "";

	private String notes = "";

	private String status = "";

	private String serialNumber;

	public String getA501c3Recip() {
		return a501c3Recip;
	}

	public Date getAvailableDay() {
		return availableDay;
	}

	public String getCheckedoutLocation() {
		return checkedoutLocation;
	}

	public int getCpu() {
		return cpu;
	}

	public String getCpuType() {
		return cpuType;
	}

	public String getCurrentManager() {
		return currentManager;
	}

	public String getCurrentOS() {
		return currentOS;
	}

	public Date getDateDelivered() {
		return dateDelivered;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public Date getDateRecycled() {
		return dateRecycled;
	}

	public String getDestination() {
		return destination;
	}

	public String getDevType() {
		return devType;
	}

	public int getHardDrive() {
		return hardDrive;
	}

	public String getHowDidYouLearn() {
		return howDidYouLearn;
	}

	public String getId() {
		return id;
	}

	public String getLaptopDomain() {
		return laptopDomain;
	}

	public String getLibraryNotification() {
		return libraryNotification;
	}

	public HashMap<String, String> getLocation() {
		return location;
	}

	public int getMemory() {
		return memory;
	}

	public String getModel() {
		return model;
	}

	public String getNid() {
		return nid;
	}

	public String getNotes() {
		return notes;
	}

	public String getStatus() {
		return status;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public Laptop setA501c3Recip(String a501c3Recip) {
		this.a501c3Recip = a501c3Recip;
		return this;

	}

	public Laptop setAvailableDay(Date availableDay) {
		this.availableDay = availableDay;
		return this;

	}

	public Laptop setCheckedoutLocation(String checkedoutLocation) {
		this.checkedoutLocation = checkedoutLocation;
		return this;

	}

	public Laptop setCpu(int cpu) {
		this.cpu = cpu;
		return this;

	}

	public Laptop setCpuType(String cpuType) {
		this.cpuType = cpuType;
		return this;

	}

	public Laptop setCurrentManager(String currentManager) {
		this.currentManager = currentManager;
		return this;
	}

	public Laptop setCurrentOS(String currentOS) {
		this.currentOS = currentOS;
		return this;

	}

	public Laptop setDateDelivered(Date dateDelivered) {
		this.dateDelivered = dateDelivered;
		return this;

	}

	public Laptop setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
		return this;

	}

	public Laptop setDateRecycled(Date dateRecycled) {
		this.dateRecycled = dateRecycled;
		return this;

	}

	public Laptop setDestination(String destination) {
		this.destination = destination;
		return this;

	}

	public Laptop setDevType(String devType) {
		this.devType = devType;
		return this;

	}

	public Laptop setHardDrive(int hardDrive) {
		this.hardDrive = hardDrive;
		return this;

	}

	public Laptop setHowDidYouLearn(String howDidYouLearn) {
		this.howDidYouLearn = howDidYouLearn;
		return this;

	}

	public Laptop setId(String id) {
		this.id = id;
		return this;

	}

	public Laptop setLaptopDomain(String laptopDomain) {
		this.laptopDomain = laptopDomain;
		return this;

	}

	public Laptop setLibraryNotification(String libraryNotification) {
		this.libraryNotification = libraryNotification;
		return this;

	}

	public Laptop setLocation(HashMap<String, String> location) {
		this.location = location;
		return this;

	}

	public Laptop setMemory(int memory) {
		this.memory = memory;
		return this;

	}

	public Laptop setModel(String model) {
		this.model = model;
		return this;

	}

	public Laptop setNid(String nid) {
		this.nid = nid;
		return this;

	}

	public Laptop setNotes(String notes) {
		this.notes = notes;
		return this;

	}

	public Laptop setStatus(String status) {
		this.status = status;
		return this;

	}
	
	private void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
		
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> params = new HashMap<String, Object>();

		// if (!uid.equals("")) params.put("uid",uid);
		if (!howDidYouLearn.equals(""))
			params.put("field_how_did_you_learn", howDidYouLearn);
		if (!currentManager.equals(""))
			params.put("field_current_manager", currentManager);
		if (!model.equals(""))
			params.put("field_model", model);
		if (cpu != 0)
			params.put("field_cpu", new Integer(cpu));
		if (!cpuType.equals(""))
			params.put("field_cpu_type", cpuType);
		if (memory != 0)
			params.put("field_memory", new Integer(memory));
		if (hardDrive != 0)
			params.put("field_hard_drive integer", new Integer(hardDrive));
		if (!currentOS.equals(""))
			params.put("field_current_os", currentOS);
		if (!destination.equals(""))
			params.put("field_destination", destination);
		if (!a501c3Recip.equals(""))
			params.put("field_501c3_recipient", a501c3Recip);
		if (!laptopDomain.equals(""))
			params.put("field_laptop_domain", laptopDomain);
		if (!status.equals(""))
			params.put("field_status", status);
		if (!devType.equals(""))
			params.put("field_dev_type", devType);
		if (!libraryNotification.equals(""))
			params.put("field_library_notification", libraryNotification);
		if (!checkedoutLocation.equals(""))
			params.put("field_checkedout_location", checkedoutLocation);
		if (!notes.equals(""))
			params.put("field_notes", notes);
		if (!serialNumber.equals(""))
			params.put("field_laptop_serial_number", serialNumber);
		
		if (dateReceived != null)
			params.put("field_date_received", dateToString(dateReceived));
		if (dateDelivered != null)
			params.put("field_date_delivered", dateToString(dateDelivered));
		if (dateRecycled != null)
			params.put("field_date_recycled", dateToString(dateRecycled));
		if (availableDay != null)
			params.put("field_available_day", dateToString(availableDay));

		return params;

	}

	@Override
	public String toString() {
		return "Laptop [serialNumber="+serialNumber+", a501c3Recip=" + a501c3Recip + ", availableDay="
				+ availableDay + ", checkedoutLocation=" + checkedoutLocation
				+ ", cpu=" + cpu + ", cpuType=" + cpuType + ", currentManager="
				+ currentManager + ", currentOS=" + currentOS
				+ ", dateDelivered=" + dateDelivered + ", dateReceived="
				+ dateReceived + ", dateRecycled=" + dateRecycled
				+ ", destination=" + destination + ", devType=" + devType
				+ ", hardDrive=" + hardDrive + ", howDidYouLearn="
				+ howDidYouLearn + ", id=" + id + ", laptopDomain="
				+ laptopDomain + ", libraryNotification=" + libraryNotification
				+ ", location=" + location + ", memory=" + memory + ", model="
				+ model + ", nid=" + nid + ", notes=" + notes + ", status="
				+ status + "]";
	}

}
