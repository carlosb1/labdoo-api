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
	
	private XmlRpcClient client;
	private final String ADDLAPTOP="labdoo.addLaptop";
	private final String DELETELAPTOP="labdoo.delLaptop";
	private final String UPDATELAPTOP="labdoo.updateLaptop";
	private final String GETLAPTOP="labdoo.getLaptop";
	private final String HELLO = "remoteHello.hello";

	private final Log log = LogFactory.getLog(LabdooClient.class);	
	
	public LabdooClient (String url, String apikey) throws APIException {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	    client = new XmlRpcClient();
	    try {
			config.setServerURL(new URL(url+"?APIKEY="+apikey));
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
			String value = (String)client.execute(ADDLAPTOP, new Object [] {laptop.toMap()});
			laptop.setNid(value);
		return value;
			
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
	

}

