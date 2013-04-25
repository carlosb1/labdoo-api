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

package api.v2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcClientException;
import org.apache.xmlrpc.client.XmlRpcSunHttpTransport;
import org.apache.xmlrpc.client.XmlRpcSunHttpTransportFactory;
import org.apache.xmlrpc.client.XmlRpcTransport;
import org.apache.xmlrpc.client.XmlRpcTransportFactory;

import api.APIException;
import api.resources.Laptop;
import api.resources.User;

public class LabdooClient {

	private String cookie;
	private XmlRpcClient client;
	private final String ADD_LAPTOP = "labdoo.addLaptop";
	private final String DELETE_LAPTOP = "labdoo.delLaptop";
	private final String UPDATE_LAPTOP = "labdoo.updateLaptop";
	private final String GET_LAPTOP = "labdoo.getLaptop";
	private final String HELLO = "remoteHello.hello";

	public static final String METHOD_SYSTEM_CONNECT = "system.connect";
	public static final String METHOD_USER_LOGOUT = "user.logout";
	public static final String METHOD_USER_LOGIN = "user.login";
	public static final String METHOD_USER_CREATE = "user.create";
	public static final String METHOD_FILE_SAVE = "file.save";

	public static final String METHOD_NODE_RETRIEVE = "node.retrieve";
	public static final String METHOD_NODE_DELETE = "node.delete";
	public static final String METHOD_NODE_CREATE = "node.create";
	public static final String METHOD_NODE_UPDATE = "node.update";

	/* mock operations */
	public static final String METHOD_NODE_LIST = "node.list";

	private final Log log = LogFactory.getLog(LabdooClient.class);

	private final String url;

	public LabdooClient(String url) throws APIException {
		this.url = url;

		try {
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			if (!url.startsWith("mock:")) {
				config.setServerURL(new URL(url));
				client = new XmlRpcClient();
				client.setConfig(config);
			} else {
				client = new MockXmlRpcClient();
			}
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;
		}

	}

	/**
	 * Call user.login
	 * 
	 * @param username
	 *            user name
	 * @param password
	 *            password
	 * @return Map with details of login user (response.get("user")) and login
	 *         session-name and session-id that are used internally to construct
	 *         the Cookie used by following calls.
	 * @throws Exception
	 *             if operation fail
	 */
	// TODO clean comments
	public Map login(String username, String password) throws Exception {
		Vector<Object> params = new Vector<Object>();
		params.add(username);
		params.add(password);
		Map response = (Map) client.execute(METHOD_USER_LOGIN, params);

		cookie = response.get("session_name") + "=" + response.get("sessid");
		XmlRpcTransportFactory factory = new XmlRpcSunHttpTransportFactory(client) {
			public XmlRpcTransport getTransport() {
				return new XmlRpcSunHttpTransport(client) {
					@Override
					protected void initHttpHeaders(XmlRpcRequest request) throws XmlRpcClientException {
						super.initHttpHeaders(request);
						setRequestHeader("Cookie", cookie);
					}
				};
			}
		};
		client.setTransportFactory(factory);

		return response;
	}

	/**
	 * Call user.logout
	 * 
	 * @throws Exception
	 *             if operation fail
	 */
	public void logout() throws Exception {
		Vector<Object> params = new Vector<Object>();
		client.execute(METHOD_USER_LOGOUT, params);
		// log.info("Logout Sucessfull");
	}

	/**
	 * Method to test server
	 * 
	 * @throws APIException. It
	 *             returns this exception if we find some error
	 */
	public String sayHello() throws APIException {
		return "";

	}

	public String addLaptop(Laptop laptop) throws APIException {
		Vector<Object> params = new Vector<Object>();
		params.add(laptop.toMap());
		Map<String, Object> response;
		try {
			response = (Map<String, Object>) client.execute(METHOD_NODE_CREATE, params);
		} catch (XmlRpcException e) {
			log.error(e.getMessage(), e.getCause());
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;
		}

		return (String) response.get("nid");

	}

	public boolean deleteLaptop(String nid) throws APIException {
		HashMap<String, Object> node = new HashMap<String, Object>();
		node.put("nid", "" + nid);
		Vector<Object> params = new Vector<Object>();
		params.add(node);
		Map<String, Object> response;
		try {
			client.execute(METHOD_NODE_DELETE, params);
		} catch (XmlRpcException e) {
			log.error(e.getMessage(), e.getCause());
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;
		}

		return true;
	}

	public boolean updateLaptop(Laptop newLaptop) throws APIException {
		Vector<Object> params = new Vector<Object>();
		params.add(newLaptop.toMap());
		Map<String, Object> response;
		try {
			client.execute(METHOD_NODE_UPDATE, params);
		} catch (XmlRpcException e) {
			log.error(e.getMessage(), e.getCause());
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;
		}

		return true;
	}

	public Laptop getLaptop(String nid) throws APIException {
		HashMap<String, Object> node = new HashMap<String, Object>();
		node.put("nid", "" + nid);
		Vector<Object> params = new Vector<Object>();
		params.add(node);
		Map<String, Object> response;
		try {
			response = (Map<String, Object>) client.execute(METHOD_NODE_RETRIEVE, params);
		} catch (XmlRpcException e) {
			log.error(e.getMessage(), e.getCause());
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;
		}
		return Laptop.newInstance(response);
	}

	public List<Laptop> listLaptops(Map<String, String> filters) throws APIException {

		return listLaptops(filters, 1, 10);

	}

	public List<Laptop> listLaptops(Map<String, String> filters, int numIndex, int size) throws APIException {

		Vector<Object> params = new Vector<Object>();
		params.add(filters);
		Map<String, Object> response;
		List<Laptop> laptops = new ArrayList<Laptop>();
		try {
			response = (Map<String, Object>) client.execute(METHOD_NODE_LIST, params);
			Set<String> keys = response.keySet();
			for (String key : keys) {
				Map<String, Object> value = (Map<String, Object>) response.get(key);
				laptops.add(Laptop.newInstance(value));
			}

		} catch (XmlRpcException e) {
			log.error(e.getMessage(), e.getCause());
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;
		}
		return laptops;
	}

	public String createNewUser(User user) throws APIException {
		Vector<Object> params = new Vector<Object>();
		params.add(user.toMap());
		Map<String, Object> response;
		try {
			response = (Map<String, Object>) client.execute(METHOD_USER_CREATE, params);
		} catch (XmlRpcException e) {
			log.error(e.getMessage(), e.getCause());
			APIException apiException = new APIException();
			apiException.initCause(e.getCause());
			throw apiException;
		}

		return (String) response.get("nid");

	}

	public Object getFieldData(Map<String, Object> response, String fieldName) {
		Object[] obj = (Object[]) ((Map<String, Object>) response.get(fieldName)).get("und");
		return ((Map<String, Object>) obj[0]).get("value");
	}

}
