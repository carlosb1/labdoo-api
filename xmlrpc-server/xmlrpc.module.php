<?php
/**
 * @file
 * Example of XML-RPC.
 */

/**
 * Implementation of hook_xmlrpc().
 * Maps external names of XML-RPC methods to callback functions.
 */





function labdoo_xmlrpc() {
 $methods[] = array(
  'remoteHello.hello',
  // External method name.
  'xmls_remotehello_hello', // PHP function to run.
  array('string', 'string'), // The return value's type,
  // then any parameter types.
  t('Greets XML-RPC clients by name.') // Description.
  );

  $methods[] = array(
    'labdoo.addLaptop',
  // External method name.
    'xmls_laptop_add', // PHP function to run.
  array('string', 'struct'), // The function input and output params
  // then any parameter types.
  t('Adding new laptop.') // Description.
  );

  $methods[] = array(
    'labdoo.delLaptop',
  // External method name.
    'xmls_laptop_delete', // PHP function to run.
  array('boolean', 'string'), // The function input and output params
  // then any parameter types.
  t('Delete a laptop.') // Description.
  );
  


  $methods[] = array(
    'labdoo.updateLaptop',
  // External method name.
    'xmls_laptop_update', // PHP function to run.
  array('boolean', 'struct'), // The function input and output params
  // then any parameter types.
  t('Update a laptop.') // Description.
  );



  return $methods;
  }
  
  /**
   * Greet a user.
   */
  function xmls_remotehello_hello($name) {
  	if (!$name) {
  		return xmlrpc_error(1, t('I cannot greet you by name if you do not provide one.'));
  	}
  	return t('Hello, @name!', array('@name' => $name));
  }
  
  //$dev_type,$model,$cpu,$cpu_type,$memory,$hard_drive,$os,$destination,$recipient,$laptop_domain,$status,$manager
  function xmls_laptop_add($args) {
  	if (!$args) { return xmlrpc_error(1, t('I cannot create a laptop if you do not provide its parameters.')); }
	//TODO Is it necessary to put these checkers? The signature specification provides the same feature
	//if ($args && !is_array($args)) { $args = array($args);}

  	$message = "";
  	foreach($args as $key=>$value)
  	{
  		$message.=$key.": ".$value;
  	}
	$node = arrayToObject($args);
 	$node->uid = 1; 	
	$node->type = "laptop"; // Or any other content type you want
	//$node->field_status[0]['value'] = "Tagged with a Labdoo ID (S0)";

	//node_object_prepare($node); // Set some default values.
	//$node = node_submit($node); // Prepare node for a submit
	node_save($node); // After this call we'll get a nid

  	return "{$node->nid}";
  }

function arrayToObject($array) {
    if(!is_array($array)) {
        return $array;
    }
    
    $object = new stdClass();
    if (is_array($array) && count($array) > 0) {
      foreach ($array as $name=>$value) {
         //$name = strtolower(trim($name));
         if (!empty($name)) {
	    //We will not use iterative arrays
            //$object->$name = arrayToObject($value);
	$arrayValue= array();
	$arrayValue[0]['value'] = $value;
	$object->$name = $arrayValue;
         }
      }
      return $object; 
    }
    else {
      return FALSE;
    }
}

 function xmls_laptop_delete($nid) {
  	if (!$nid) { return xmlrpc_error(1, t('I cannot delete a laptop if you do not  provide one.')); }
 	node_delete($nid);
	return TRUE;
  }

 /*
 * Add functionalities. It has to update all possible parameters 
 */
 function xmls_laptop_update($args) {
  	if (!$args) { return xmlrpc_error(1, t('I cannot create a laptop if you do not provide its parameters.')); }
	//TODO Is it necessary to put these checkers? The signature specification provides the same feature
	//if ($args && !is_array($args)) { $args = array($args);}

  	$message = "";
  	foreach($args as $key=>$value)
  	{
  		$message.=$key.": ".$value;
  	}
 //	$update_node = node_load($nid);
 //	$update_node->title = 'update title';
 //	node_save($update_node);
	return TRUE;
}

 /*
 * Add functionalities. It has to update all possible parameters 
 */
// function xmls_laptop_get($nid) {
//	return 

// }

  







