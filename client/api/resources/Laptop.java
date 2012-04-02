package api.resources;

import java.util.HashMap;
import java.util.Map;

//TODO IMPLEMENT EQUALS AND TOSTRING!!
public class Laptop {
	//TODO AND FIXME Check which possible status are!!

	public final static String NULL = "null";
	public final static String TAGGED_S0 = "Tagged with a Labdoo ID (S0)";
	public final static String DONATED_S1 = "Donated, in quality assurance process (S1)";
	public final static String PASSED_QUALITY_ASSURANCE_S2 = "Passed quality assurance (S2)";
	public final static String ASSIGNED_S3 = "Assigned to a child, waiting to be shipped (S3)";
	public final static String DELIVERED_AND_BEING_USED_S4 = "Delivered and being used (S4)";
	public final static String WAITING_TO_BE_RECYCLED_S5 = "Waiting to be recycled (S5)";
	public final static String RECYCLED_S6 = "Recycled (S6)";
	public final static String CHECKED_OUT_S7 = "(Library) Checked out (S7)";
	public final static String AVAILABLE_S8 = "(Library) Available (S8)";
	


// 	$newnode = new stdClass();
// 	$newnode->title = ''; 
// 	$newnode->uid = 1; 	//TODO TO TEST ALL THESE TYPES
// 	$newnode->type = 'laptop'; 
// 	$newnode->field_how_did_you_learn = NULL;
// 	//$newnode->field_current_manager-> 1
// 	$newnode->field_dev_type = $dev_type;
// 	//$newnode->field_date_received->date
// 	//$newnode->field_date_delivered->date
// 	//$newnode->field_date_recycled->date
// 	$newnode->field_model = $model;
// 	$newnode->field_cpu->$cpu;
// 	$newnode->field_cpu_type = $cpu_type;
// 	$newnode->field_memory->$memory;
// 	$newnode->field_hard_drive->$hard_drive;
// 	$newnode->field_current_os = $os;
// 	$newnode->field_destination = $destination;
// 	$newnode->field_501c3_recipient = $recipient;
// 	$newnode->field_laptop_domain = $laptop_domain;
// 	$newnode->field_status = $status;
// 	//$newnode->field_available_day->date
// 	$newnode->field_library_notification = NULL;
// 	$newnode->field_checkedout_location = NULL;
// 	//$newnode->field_picture->NULL;
// 	//$newnode->field_pic_deployed->NULL;
// 	$newnode->field_notes = '';
	
	
	//get
	private String uid = "";

	//reference to current manager
	private String currentManager = "";
	private String model = "";
	private String cpu = "";
	private String cpuType = "";
	private String memory = "";
	private String hardDrive = "";
	private String currentOS = "";
	private String destination  = "";
	private String a501c3Recip = "";
	private String laptopDomain = "";
	private String status = "";
	
	
	public static Laptop newSimpleLaptop () {
		Laptop laptop = new Laptop ();
		laptop.uid = "1";
		laptop.status = TAGGED_S0;
		return laptop;
		
	}


	
	//EMPTY LAPTOP
	final static public Laptop EMPTYLAPTOP = new Laptop();
	
	public String getUid() {
		return uid;
	}




	public void setUid(String uid) {
		this.uid = uid;
	}




	public String getCurrentManager() {
		return currentManager;
	}




	public void setCurrentManager(String currentManager) {
		this.currentManager = currentManager;
	}




	public String getModel() {
		return model;
	}




	public void setModel(String model) {
		this.model = model;
	}




	public String getCpu() {
		return cpu;
	}




	public void setCpu(String cpu) {
		this.cpu = cpu;
	}




	public String getCpuType() {
		return cpuType;
	}




	public void setCpuType(String cpuType) {
		this.cpuType = cpuType;
	}




	public String getMemory() {
		return memory;
	}




	public void setMemory(String memory) {
		this.memory = memory;
	}




	public String getHardDrive() {
		return hardDrive;
	}




	public void setHardDrive(String hardDrive) {
		this.hardDrive = hardDrive;
	}




	public String getCurrentOS() {
		return currentOS;
	}




	public void setCurrentOS(String currentOS) {
		this.currentOS = currentOS;
	}




	public String getDestination() {
		return destination;
	}




	public void setDestination(String destination) {
		this.destination = destination;
	}




	public String getA501c3Recip() {
		return a501c3Recip;
	}




	public void setA501c3Recip(String a501c3Recip) {
		this.a501c3Recip = a501c3Recip;
	}




	public String getLaptopDomain() {
		return laptopDomain;
	}




	public void setLaptopDomain(String laptopDomain) {
		this.laptopDomain = laptopDomain;
	}




	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	

	
	
	public Map<String,String> toMap() {
		Map<String,String> params = new HashMap<String,String>();
		
		if (!uid.equals("")) params.put("uid",uid);
		if (!currentManager.equals("")) params.put("currentManager",currentManager);
		if (!model.equals("")) params.put("model",model);
		if (!cpu.equals("")) params.put("cpu",cpu);
		if (!cpuType.equals("")) params.put("cpuType",cpuType);
		if (!memory.equals("")) params.put("memory",memory);
		if (!hardDrive.equals("")) params.put("hardDrive",hardDrive);
		if (!currentOS.equals("")) params.put("currentOS",currentOS);
		if (!destination.equals("")) params.put("destination",destination);
		if (!a501c3Recip.equals("")) params.put("a501c3Recip",a501c3Recip);
		if (!laptopDomain.equals("")) params.put("laptopDomain",laptopDomain);
		if (!status.equals("")) params.put("status",status);
		
		
		return params;
		
		
	}
	

	public static Laptop newInstance(Map params) {
		// TODO to finish
		return Laptop.newSimpleLaptop();
	}
	 

	

	


}
