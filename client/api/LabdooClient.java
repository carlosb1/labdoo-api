package api;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import api.resources.Laptop;


public class LabdooClient {
	
	XmlRpcClient client;
	//labdoo operations
	final String ADDLAPTOP="labdoo.addLaptop";
	final String DELETELAPTOP="labdoo.delLaptop";
	final String UPDATELAPTOP="labdoo.updateLaptop";
	final String GETLAPTOP="labdoo.getLaptop";
	//test operations
	final String HELLO = "remoteHello.hello";
//	system.multicall', 'system.methodSignature', 'system.getCapabilities',
//	'system.listMethods', 'system.methodHelp', 'remoteHello.hello



	final String md5Key = "5461de53468f45a435b99ae97c930f46";
	final static String URL_LABDOO = "http://ec2-50-17-80-217.compute-1.amazonaws.com/labdoo/xmlrpc.php";	
	final Log log = LogFactory.getLog(LabdooClient.class);
	
	
	
	
	public LabdooClient () throws APIException {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	    client = new XmlRpcClient();
	    try {
			config.setServerURL(new URL(URL_LABDOO));
		} catch (MalformedURLException e) {
			log.error(e.getMessage());			
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;			
		}
	    client.setConfig(config);

		
	}
	/**
	 * Method to test server
	 * @throws APIException. It returns this exception if we find some error
	 */
	public String sayHello()  throws APIException {
		Object[]params = {"carlos"};
		try{
		 return (String)client.execute(HELLO,params);
		} catch (XmlRpcException e) {
			log.error(e.getMessage(),e.getCause());
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;	
		}
		
	}
	
	
	public String addLaptop (Laptop laptop) throws APIException {
		try{
	        
		return (String)client.execute(ADDLAPTOP, new Object [] {laptop.toMap()});
			
		} catch (XmlRpcException e) {
			log.error(e.getMessage(),e.getCause());			
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;	
		} 
		
	}
	
	public boolean deleteLaptop (String nid) throws APIException  {
		try{
			return ((Boolean)client.execute(DELETELAPTOP,new Object [] {nid})).booleanValue();
		} catch (XmlRpcException e) {
			log.error(e.getMessage(),e.getCause());					
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;	
		}
		
	}
	
	public boolean updateLaptop (Laptop newLaptop) throws APIException  {
		try{
			return ((Boolean)client.execute(UPDATELAPTOP,new Object [] {newLaptop.toMap()})).booleanValue();
		} catch (XmlRpcException e) {
			log.error(e.getMessage(),e.getCause());			
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;
		}
		
	}
	//TODO TO FINISH!
	public Laptop getLaptop (String nid) throws APIException {
		try{
		 return Laptop.newInstance((Map)client.execute(GETLAPTOP,new Object [] {nid}));	
		} catch (XmlRpcException e) {
				log.error(e.getMessage(),e.getCause());					
				APIException apiException = new APIException();
				apiException.initCause(e.getCause());
				throw apiException;	
		}

		
		
	}
	
//	
//	public List<String> listIDLaptops()  throws APÎException {
//		
//		
//		
//		
//	} 
//	
	


}

