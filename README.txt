author: Carlos Báez Ruiz 
README.TXT

BUGS AND FEATURES FOR NEXT VERSIONS
===================================

[] -> check types in forms... (number of copies)


NEXT FEATURES
=============


[] -> Include other node types f.e:  hubs, trips...
[] -> Add list Laptop operation
[] -> Create Javadoc
[] -> current manager xml response returns a user id, not an username

   
OVERVIEW
========

The Labdoo API is made up of different modules which provide a common framework for developers. Its aim is make easy

the utilization of the Labdoo project features (references: www.labdoo.org). 

The Labdoo API framework includes:

-> labdoo_nodes. The core module, It includes a set of functions in order to provide basic operations to manage web elements of the Labdoo web.

-> form_labdoo. This module set up a form to tag and manage laptops.

-> xmlrpc-server. A Drupal module to provides xmlrpc operations for Labdoo. In this release, the xmlrpc framework has:
 - remoteHello.hello. Operation to test the framework
 - labdoo.addLaptop. To tag a new Labdoo laptop.
 - labdoo.delLaptop. To delete a tag from a laptop
 - labdoo.updateLaptop. Modify laptop parameters.
 
-> form_labdoo_apikey. All the xmlrpc operations which the xmlrpc-server publish needs an authentication process. The form_apikey provides this 
feature. Labdoo API supply this an api key to each user that with these keys, the xmlrpc service will verify the access and permit to use its operations.

The project contain these files:

	- When you execute "ant test", it is generated a file with all results.
		./bin/junitreport:
			TEST-ClientTest.xml
	
	- Java API.
		./client:
		./client/api:
			APIException.java  LabdooClient.java  resources
		./client/api/resources:
			Laptop.java

	- PHP form to register new laptops.
		./form_labdoo:
			form_labdoo.inc  form_labdoo.info  form_labdoo.module
	
	- PHP form to manage API keys. It registers API keys for new user who want to use the Labdoo API key.
		./form_labdoo_apikey:
			form_labdoo_apikey.info  form_labdoo_apikey.module
	
	- Drupal module with basic operations to manage the labdoo drupal server.
		./labdoo_nodes:
			labdoo.inc  labdoo_nodes.info  labdoo_nodes.module
			
	- Jar dependencies to compile the java project
		./libs:
			commons-logging-1.1.jar  jsch-0.1.47.jar  junit-4.10.jar  ws-commons-util-1.0.2.jar  xmlrpc-client-3.1.3.jar  xmlrpc-common-3.1.3.jar  xmlrpc-server-3.1.3.jar
			
	- Necessary script to create the database table to manage api keys. 
		./resources:
			create_table_apikey.sh
			
	- Folder with java junit tests.
		./tests:
			ClientTest.java
			
	- XMLRPC Client. It includes an alpha version to call via php the XMLRPC Server.
		./xmlrpc-client:
			curl.class.php  labdoo-client.php  xmlrpc-client.php
			
	- XMLRPC Server with the available xmlrpc operations.
		./xmlrpc-server:
			xmlrpc.info  xmlrpc.module  xmlrpc.test

INSTALLATION
============

SERVER INSTALLATION
===================

The API includes a script (It is ant script and the tool and its dependencies should be installed) to automate the installation process.
The script can be located in the project and It is named build.xml. The operations which includes are:

clean. To delete .class in the java client api 
compile. To compile java client api

remote-clean. It connects to the Labdoo web page and cleans the xmlrpc module
remote-deploy. It deploys in the Labdoo web page a new version for the xmlrpc-server module 

remote-deploy-form_api. It deploys in the Labdoo web page a new version for the form_labdoo_apikey module
remote-clean-form_api. It connects to the Labdoo web page and cleans the form_labdoo_apikey module

remote-deploy-form_labdoo It deploys in the Labdoo web page a new version for the form labdoo module
remote-clean-form_labdoo. It connects to the Labdoo web page and cleans the form labdoo module

remote-deploy-nodes. It deploys main modules to use the API. It includes operations to manage laptops and parse their laptops
remote-clean-nodes. It connects to the Labdoo web page and cleans the nodes labdoo module

remote-stop. It stops Labdoo remote server.                 
remote-restart. It restart Labdoo remote server.  
remote-deploy. It deploys Labdoo remote server.

test. Test the java Labdoo API.    
junitreport. It creates a report with the results  get from the "ant test" command.

NOTES:
- To use the remote operations, the ant tool needs a .jar file to execute the ssh tag (*). This is the tool's link:
	[link: http://www.jcraft.com/jsch]
Download the JCraft .jar file and "move it" to the ant classpath"
- Furthemore, you will need a .pem key and the server address to connect via SSH|SCP and will be able to upload all the  Labdoo API
modules.
For this reason, inside the build.xml, you have to modify server.name and client.key parameters.


 
CONFIGURATION
=============
 [Add permission configuration]
 [Restore API KEY]

GETTING STARTED
===============

The Labdoo API has two possible clients, a PHP or Java Client.

- Java Client. The JAVA API. It uses a set of classes to abstract the connection with the Laptop. 

	1.- Initialize the Java XMLRPC client: 
		LabdooClient client = new LabdooClient();
		 
	2.- Prepare and fill out the Laptop class
		Laptop laptop = new Laptop();
		laptop.setStatus(Laptop.TAGGED_S0);
		laptop.setA501c3Recip("");
		laptop.setCpu(1);
		laptop.setCpuType(Laptop.CPU_FIVE);
		laptop.setCurrentManager("carlos.baez");
		laptop.setCurrentOS("");

	3.- Create it and get its nid. With this nid you will be able to manage the created laptop.
		String nid = client.addLaptop(laptop);
		
	4.- If you want to delete the laptop or get your information, you use this identifier like:
			boolean result = client.deleteLaptop(nid);
		or 
			Laptop newLaptop = client.getLaptop(nid);
	
  

- PHP Client. A first alpha version is implemented. It is a dummy version which it permits to call the XMLRPC server 
			with these operations inside the labdoo-client.php:
			
		 public function addLaptop($params);
			$params -> Array with all the necessary parameters to build a new laptop.
		 public function delLaptop($nid); 
		 	$nid -> String nid identifier.
		 public function getLaptop($nid);
		 	 $nid -> String nid identifier.
		 public function updateLaptop($params);
			$params -> Array with all the necessary parameters to build a new laptop.

