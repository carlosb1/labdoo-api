package api;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class DrupalClient {


	XmlRpcClient client;
	private final String MULTICALL = "system.multicall";
	private final String LISTMETHODS = "system.listMethods";
	private final String METHODSIGNATURE = "system.methodSignature";
	private final String METHODHELP = "system.methodHelp";
	private final String GETCAPABILITIES = "system.getCapabilities";
	
	private String url;	
	final Log log = LogFactory.getLog(DrupalClient.class);
	
	
	
	
	public DrupalClient (String url) throws APIException {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	    client = new XmlRpcClient();
	    try {
	    	this.url = url;
			config.setServerURL(new URL(url));
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

}
