OVERVIEW
========

It includes a set of modules:
- JAVA Client. It is a Java API to have available the Labdoo project. 
-> Explicación del objetivo de la librería
-> Explicación de los diferentes módulos
-> Funcionamiento de la librería. Ejemplos de uso: Librería Java y  cómo funciona el protocolo de XML 
-> FEATURES
-> Añadir cosas pendientes por hacer

BUGS
====

[] -> Include wiki
[] -> Include dependency generator
[] -> Include license
[] -> Include other type of hubs...
[] -> PHP client
[] -> Improve and refactorize form
[] -> Add list Laptop
[] -> Add ant build
[X] -> Correct parsing of dates and location for the form_labdoo_apikey
[] -> Check if it is necessary to use checker for xmlrpc operations, test it 
[] -> Add unitary tests with fail cases
[] -> Create Javadoc
[] -> Test email submit for xmlrpc-client
[] -> Fix problem to parse date and locations in form_labdoo_apikey module
[] -> Include roadmap
[] -> current manager xml response returns a user id, not an username
[] -> check types in forms... 
[] -> Add tests for date operations
[] -> Manual PHP


GETTING STARTED
===============

-> Presentation of API
-> Labdoo API
-> Different modules (definition)
-> Features
-> Steps
-> 1.- Installation
-> 2.- Configuration () Module permissions, 
-> 3.- Diagram to explain workflow for form API and form the Labdoo API
-> 4.- How to implement a JAVA client...
   - dependencies
   
OVERVIEW
========

The Labdoo API is formed "for" different modules which provide a common framework for developers. Its "objective" is make easy

the "utilización" of the Labdoo project (more references: www.labdoo.org). 

The Labdoo API framework includes:

-> labdoo_nodes. The core module, It includes a set of functions in order to provide basic operations to manage web elements of the Labdoo web
-> form_labdoo. This modul
e set up a form to tag and manage laptops
-> xmlrpc-server. A Drupal module to provides xmlrpc operations for Labdoo. In this release, the xmlrpc framework has:

 - remoteHello.hello. Operation to test the framework
 - labdoo.addLaptop. To tag a new Labdoo laptop.
 - labdoo.delLaptop. To delete a tag from a laptop
 - labdoo.updateLaptop. Modify parameterd in a Labdoo laptop
 
 
-> form_labdoo_apikey. All the xmlrpc operations which the xmlrpc-server publish "needs" an authentication process. The form_apikey provides a
process. Labdoo API "provides sinónimo, supply" this "using" an api key to each user. With these keys, the xmlrpc service will verify the access and
permit to use its operations.

INSTALLATION
============

SERVER INSTALLATION
===================
The API includes a script (It is ant script which It should be installed this tool and its dependencies) to automate the installation process.
The script can be located in the project and It is named build.xml. The operations which includes are:

clean. To delete .class in the java client api 
compile. To compile java client api

remote-clean. It connects to the Labdoo web page and cleans the xmlrpc module
remote-deploy. It deploys in the Labdoo web page a new version for the xmlrpc-server module 

remote-deploy-form_api. It deploys in the Labdoo web page a new version for the form_labdoo_apikey module
remote-clean-form_api. It connects to the Labdoo web page and cleans the form_labdoo_apikey module

remote-deploy-form_labdoo It deploys in the Labdoo web page a new version for the form labdoo module
remote-clean-form_labdoo. It connects to the Labdoo web page and cleans the form labdoo module

(*) AÑADIR MÁS OPERACIONES 
NOTES:
- To use the remote operations, the ant tool needs a .jar file to execute the ssh tag (*). ThiS is the tool's link:
	[link: http://www.jcraft.com/jsch]
Download the JCraft .jar file and "move it" to the ant classpath"
- Furthemore, you will need a .pem key and the server address to connect via SSH|SCP and can upload all the  Labdoo API
modules.
For this, inside the build.xml, you have to modify server.name, client.key parameters.


 
CONFIGURATION
=============
 [Add permission configuration]


GETTING STARTED
===============

The Labdoo API has two possible clients, a PHP or Java Client.

- Java Client. The JAVA API. It "recoge, ordena" a set of classes to abstract the connection with the Laptop 

- PHP Client.


author: Carlos Báez Ruiz 