<?php
function save_laptop ($args) {
  	if (!$args) { return xmlrpc_error(1, t('I cannot create a laptop if you do not provide its parameters.')); }
	//TODO Is it necessary to put these checkers? The signature specification provides the same feature
	//if ($args && !is_array($args)) { $args = array($args);}
	$node = arrayToObject($args);
 	$node->uid = 1; 	
	$node->type = "laptop"; // Or any other content type you want
	node_save($node); // After this call we'll get a nid	
	return $node->nid;
}



function load_laptop($nid) {
 	$update_node = node_load($nid);
	return objectToArray($update_node);
}

function update_laptop($args) {
	 $update_node = node_load($nid);
      foreach ($args as $name=>$value) {
			$arrayValue= array();
			$arrayValue[0]['value'] = $value;
			$update_node->$name = $arrayValue;
      }
 	node_save($update_node);
}

function delete_laptop ($nid) {
	node_delete($nid);
}

function print_laptop ($args) {
	foreach ($args as $name=>$value) {
		drupal_set_message(t('name=@name value=@value',array('@name' => $name, '@value' => $value)));
	}
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


function objectToArray($obj) {
        $_arr = is_object($obj) ? get_object_vars($obj) : $obj;
        foreach ($_arr as $key => $val) {
		//check not null
		$val = (is_array($val) && !is_null($var[0]) && is_array($var[0])) ? $val[0]['value'] : $val;
           //     $val = (is_array($val) || is_object($val)) ? objectToArray($val) : $val;
                $arr[$key] = $val;
        }
        return $arr;
}
