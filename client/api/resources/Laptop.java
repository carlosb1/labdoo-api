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

//TODO IMPLEMENT EQUALS AND TOSTRING!!
public class Laptop {
	
	static final Log log = LogFactory.getLog(Laptop.class);

	
	//HOW DID YOU LEARN
	public final static String FROM_FRIEND = "From a friend";
	public final static String FROM_INTERNET = "From the Internet";
	public final static String FROM_UCI_BOOKSTORE = "From the UCI bookstore";
	public final static String FROM_CONFERENCE_OR_EVENT = "From a conference or Labdoo event";
	public final static String FROM_HUB= "I belong to a Labdoo hub";
	
	public final static String NULL = "null";
	//STATUS
	public final static String TAGGED_S0 = "Tagged with a Labdoo ID (S0)";
	public final static String DONATED_S1 = "Donated, in quality assurance process (S1)";
	public final static String PASSED_QUALITY_ASSURANCE_S2 = "Passed quality assurance (S2)";
	public final static String ASSIGNED_S3 = "Assigned to a child, waiting to be shipped (S3)";
	public final static String DELIVERED_AND_BEING_USED_S4 = "Delivered and being used (S4)";
	public final static String WAITING_TO_BE_RECYCLED_S5 = "Waiting to be recycled (S5)";
	public final static String RECYCLED_S6 = "Recycled (S6)";
	public final static String CHECKED_OUT_S7 = "(Library) Checked out (S7)";
	public final static String AVAILABLE_S8 = "(Library) Available (S8)";
	//devType
	public final static String TYPE_LAPTOP = "Laptop";
	public final static String TYPE_GOOGLE_PHONE = "Google phone";
	public final static String TYPE_IPHONE =  "iPhone";
	public final static String TYPE_INTERNET_TABLET = "Internet tablet";
	public final static String TYPE_IPAD = "iPad";
	public final static String TYPE_DESKTOP = "Desktop";
	
	//number of cpus
	public final static String CPU_ONE = "1 (Single core)";
	public final static String CPU_TWO ="2 (Duo core)";
	public final static String CPU_THREE ="3";
	public final static String CPU_FOUR ="4";
	public final static String CPU_FIVE ="5";
	public final static String CPU_SIX ="6";
	public final static String CPU_SEVEN ="7";
	public final static String CPU_EIGHT ="8";
	
	//a501
	public final static String RECIP_NOT_KNOW = "Not known yet";
	public final static String RECIP_EWB_ORANGE_COUNTY = "EWB Orange County";
	public final static String RECIP_EWB_UCI ="EWB UCI";
	public final static String RECIP_EWB_UCLA ="EWB UCLA";
	public final static String RECIP_EWB_WESTERN_MA ="EWB Western MA";
	public final static String RECIP_WCE ="World Computer Exchange";
	
	//domain
	public final static String DOMAIN_INDIVIDUAL = "Individual volunteer (grassroots)";
	public final static String  DOMAIN_CASASITO = "Casasito, Guatemala";
	public final static String  DOMAIN_EWB_UCI = "Engineers Without Borders @ UCI";
	public final static String  DOMAIN_EWB_MASSACHUSETTS = "Engineers Without Borders @ Western Massachusetts";
	public final static String  DOMAIN_FOXCONN = "Foxconn";
	public final static String  DOMAIN_I2CAT = "I2CAT, Barcelona";
	public final static String  DOMAIN_MERCIMED = "MerciMed, New York";
	public final static String  DOMAIN_SPRINGSOFT = "SpringSoft";
	public final static String  DOMAIN_UCI = "University of California, Irvine";
	public final static String  DOMAIN_UCLA = "University of California, Los Angeles";
	public final static String  DOMAIN_WCE = "World Computer Exchange, Boston";
	
	//field_status
	private String status = "";
// 	field_how_did_you_learn
	private String howDidYouLearn = "";
// 	//field_current_manager
	//TODO TO TEST, It WOULD BE AN USER ACCOUNT
	private String currentManager = "";
// 	field_dev_type = $dev_type;
	private String devType = "";
	
// 	//$newnode->field_date_received->date
	private Date dateReceived = null;
// 	//$newnode->field_date_delivered->date
	private Date dateDelivered = null;
// 	//$newnode->field_date_recycled->date
	private Date dateRecycled = null;	
// 	//$newnode->field_available_day->date
	private Date availableDay = null;

// 	//$newnode->field_picture->NULL;
// 	//$newnode->field_pic_deployed->NULL;	
// field_model = $model;
	private String model = "";
// field_cpu integer
	private int cpu = 0;
// field_cpu_type 
	private String cpuType = "";
// field_memory integer
	private int memory = 0;
// field_hard_drive integer
	private int hardDrive = 0;	
//  field_current_os
	private String currentOS = "";
// 	field_destination
	private String destination  = "";
// 	field_501c3_recipient
	private String a501c3Recip = "";
// 	field_laptop_domain 
	private String laptopDomain = "";
// 	field_library_notification 
	private String libraryNotification = "";
// 	field_checkedout_location
	private String checkedoutLocation = "";	
// 	field_notes
	private String notes = "";
	private String nid = "";
	
	private HashMap<String,String> location = new HashMap<String,String>();

	
	
	public static Laptop newSimpleLaptop () {
		Laptop laptop = new Laptop ();
		//laptop.uid = "1";
		laptop.status = TAGGED_S0;
		return laptop;
		
	}

	
	public String getStatus() {
		return status;
	}


	public String getHowDidYouLearn() {
		return howDidYouLearn;
	}


	public String getCurrentManager() {
		return currentManager;
	}


	public String getDevType() {
		return devType;
	}


	public String getModel() {
		return model;
	}


	public int getCpu() {
		return cpu;
	}


	public String getCpuType() {
		return cpuType;
	}


	public int getMemory() {
		return memory;
	}


	public int getHardDrive() {
		return hardDrive;
	}


	public String getCurrentOS() {
		return currentOS;
	}


	public String getDestination() {
		return destination;
	}


	public String getA501c3Recip() {
		return a501c3Recip;
	}


	public String getLaptopDomain() {
		return laptopDomain;
	}


	public String getLibraryNotification() {
		return libraryNotification;
	}


	public String getCheckedoutLocation() {
		return checkedoutLocation;
	}


	public String getNotes() {
		return notes;
	}

	public String getNid() {
		return nid;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setHowDidYouLearn(String howDidYouLearn) {
		this.howDidYouLearn = howDidYouLearn;
	}


	public void setCurrentManager(String currentManager) {
		this.currentManager = currentManager;
	}


	public void setDevType(String devType) {
		this.devType = devType;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public void setCpu(int cpu) {
		this.cpu = cpu;
	}


	public void setCpuType(String cpuType) {
		this.cpuType = cpuType;
	}


	public void setMemory(int memory) {
		this.memory = memory;
	}


	public void setHardDrive(int hardDrive) {
		this.hardDrive = hardDrive;
	}


	public void setCurrentOS(String currentOS) {
		this.currentOS = currentOS;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public void setA501c3Recip(String a501c3Recip) {
		this.a501c3Recip = a501c3Recip;
	}


	public void setLaptopDomain(String laptopDomain) {
		this.laptopDomain = laptopDomain;
	}


	public void setLibraryNotification(String libraryNotification) {
		this.libraryNotification = libraryNotification;
	}


	public void setCheckedoutLocation(String checkedoutLocation) {
		this.checkedoutLocation = checkedoutLocation;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public void setNid(String nid) {
		this.nid = nid;
	}


	public HashMap<String,String> getLocation() {
		return location;
	}


	public void setLocation(HashMap<String,String> location) {
		this.location = location;
	}
	
	public Date getDateReceived() {
		return dateReceived;
	}


	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}


	public Date getDateDelivered() {
		return dateDelivered;
	}


	public void setDateDelivered(Date dateDelivered) {
		this.dateDelivered = dateDelivered;
	}


	public Date getDateRecycled() {
		return dateRecycled;
	}


	public void setDateRecycled(Date dateRecycled) {
		this.dateRecycled = dateRecycled;
	}


	public Date getAvailableDay() {
		return availableDay;
	}


	public void setAvailableDay(Date availableDay) {
		this.availableDay = availableDay;
	}


	public Map<String,Object> toMap() {
		Map<String,Object> params = new HashMap<String,Object>();
		
//		if (!uid.equals("")) params.put("uid",uid);
		if (!howDidYouLearn.equals("")) params.put("field_how_did_you_learn",howDidYouLearn);
		if (!currentManager.equals("")) params.put("field_current_manager",currentManager);
		if (!model.equals("")) params.put("field_model",model);
		if (cpu !=0 ) params.put("field_cpu",new Integer(cpu));
		if (!cpuType.equals("")) params.put("field_cpu_type",cpuType);
		if (memory!=0 ) params.put("field_memory",new Integer(memory));
		if (hardDrive !=0 ) params.put("field_hard_drive integer",new Integer(hardDrive));
		if (!currentOS.equals("")) params.put("field_current_os",currentOS);
		if (!destination.equals("")) params.put("field_destination",destination);
		if (!a501c3Recip.equals("")) params.put("field_501c3_recipient",a501c3Recip);
		if (!laptopDomain.equals("")) params.put("field_laptop_domain",laptopDomain);
		if (!status.equals("")) params.put("field_status",status);
		if (!devType.equals("")) params.put("field_dev_type",devType);		
		if (!libraryNotification.equals("")) params.put("field_library_notification",libraryNotification);
		if (!checkedoutLocation.equals("")) params.put("field_checkedout_location",checkedoutLocation);
		if (!notes.equals("")) params.put("field_notes",notes);
		
		if (dateReceived!=null) params.put("field_date_received",dateToString(dateReceived));
		if (dateDelivered!=null) params.put("field_date_delivered",dateToString(dateDelivered));
		if (dateRecycled!=null) params.put("field_date_recycled",dateToString(dateRecycled));
		if (availableDay!=null) params.put("field_available_day",dateToString(availableDay));
		
		
		return params;
		
		
	}
	
	private static String dateToString(Date dateToFormat) {
		//2010-08-20T00:00:00
		SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
		return dateFormater.format(dateToFormat);		
		
		
	}
	
	private static Date stringToDate(String dateToParse) {
		//2010-08-20T00:00:00
		SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
		try {
			return dateFormater.parse(dateToParse);
		} catch (ParseException e) {
			log.error(e);
			return null;
		}		
		
		
	}
	
	

	public static Laptop newInstance(Map<String,Object> params) throws APIException {
		Laptop laptop = new Laptop();
		Set<String> keys = params.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();


			
			//TODO ADD UID
			if (key.equals("nid"))
				laptop.setNid((String)params.get(key));

			if (key.equals("location") && params.get(key) !=null)
				laptop.setLocation((HashMap)params.get(key));
			
			
			Object param = (Object)hashValueToParam(params.get(key));
			if (param == null || param.equals("")) continue;
						
			
			if (key.equals("field_how_did_you_learn"))
				laptop.setHowDidYouLearn((String)param);
		
			if (key.equals("field_current_manager"))
				laptop.setCurrentManager((String)getUIDParam(param));

			if (key.equals("field_model"))
				laptop.setModel((String)param);

			if (key.equals("field_cpu"))
				laptop.setCpu(((Integer)param).intValue());

			if (key.equals("field_cpu_type"))
				laptop.setCpuType((String)param);

			if (key.equals("field_memory"))
				laptop.setMemory(((Integer)param).intValue());

			if (key.equals("field_hard_drive integer"))
				laptop.setHardDrive(((Integer)param).intValue());

			if (key.equals("field_current_os"))
				laptop.setNid((String)param);

			if (key.equals("field_destination"))
				laptop.setDestination((String)param);

			if (key.equals("field_501c3_recipient"))
				laptop.setA501c3Recip((String)param);

			if (key.equals("field_laptop_domain"))
				laptop.setLaptopDomain((String)param);

			if (key.equals("field_status"))
				laptop.setStatus((String)param);

			if (key.equals("field_dev_type"))
				laptop.setDevType((String)param);

			if (key.equals("field_library_notification"))
				laptop.setLibraryNotification((String)param);

			if (key.equals("field_checkedout_location"))
				laptop.setCheckedoutLocation((String)param);

			if (key.equals("field_notes"))
				laptop.setNotes((String)param);

			//TODO TO TEST
//			if (key.equals("field_date_received"))
//				laptop.setDateReceived(stringToDate((String)param));
//			
//			if (key.equals("field_date_delivered"))
//				laptop.setDateDelivered(stringToDate((String)param));
//			
//			if (key.equals("field_available_day"))
//				laptop.setAvailableDay(stringToDate((String)param));
//			
//			if (key.equals("field_date_recycled"))
//				laptop.setDateRecycled(stringToDate((String)param));
			
			
			
		}
		return laptop;
	}
	
	private static Object hashValueToParam(Object drupalParam) throws APIException {
			if (drupalParam instanceof Object[] && ((Object[])drupalParam).length >0) {
				 Object arrayParam = ((Object[])drupalParam)[0];
				if (arrayParam instanceof Map && ((Map)arrayParam).get("value") != null) {
					return ((Map)arrayParam).get("value");
				}
				
			}

		return drupalParam;
	}
	
	private static Object getUIDParam (Object param) {
		Object[] arrayParam = (Object[])param;
		Map hashParam = (Map)arrayParam[0];
		return hashParam.get("uid");
		
	}

	
	
	



	@Override
	public String toString() {
		return "Laptop [status=" + status + ", howDidYouLearn="
				+ howDidYouLearn + ", currentManager=" + currentManager
				+ ", devType=" + devType + ", model=" + model + ", cpu=" + cpu
				+ ", cpuType=" + cpuType + ", memory=" + memory
				+ ", hardDrive=" + hardDrive + ", currentOS=" + currentOS
				+ ", destination=" + destination + ", a501c3Recip="
				+ a501c3Recip + ", laptopDomain=" + laptopDomain
				+ ", libraryNotification=" + libraryNotification
				+ ", checkedoutLocation=" + checkedoutLocation + ", notes="
				+ notes + ", nid=" + nid + ", location="
				+ location + "]";
	}
	 

	

	


}
