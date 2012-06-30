
<?php
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
function save_laptop ($args) {
	//watchdog('labdoo', 'Saving laptop!');
  	if (!$args) { return xmlrpc_error(1, t('I cannot create a laptop if you do not provide its parameters.')); }
	//TODO Is it necessary to put these checkers? The signature specification provides the same feature
	//if ($args && !is_array($args)) { $args = array($args);}

	$node = arrayToObject($args);
 	$node->uid = 1; 	
	$node->type = "laptop"; // Or any other content type you want
	node_save($node); // After this call we'll get a nid
	
	//watchdog('labdoo', 'laptop created with value=%value',array('%value' => $node->title, WATCHDOG_ERROR));
	if ("$node->title" == "") { return xmlrpc_error(2, t('I could not add the laptop, check sent parameters...')); }
	return $node->nid;
}



function load_laptop($nid) {
 	$update_node = node_load($nid);
	if ("$update_node->title" == "") { return xmlrpc_error(2, t('I could not add the laptop, check sent parameters...')); }
	return objectToArray($update_node);
}

function update_laptop($args) {
	if (!$args) { return xmlrpc_error(1, t('I cannot create a laptop if you do not provide its parameters.')); }	
	 $update_node = node_load($nid);
      foreach ($args as $name=>$value) {
			$arrayValue= array();
			$arrayValue[0]['value'] = $value;
			$update_node->$name = $arrayValue;
      }
 	node_save($update_node);
	return TRUE;
}

function delete_laptop ($nid) {
  	if (!$nid) { return xmlrpc_error(1, t('I cannot delete a laptop if you do not  provide one.')); }	
	node_delete($nid);
	return TRUE;
}

function print_laptop ($args) {
	foreach ($args as $name=>$value) {
		watchdog('labdoo', 'name=%name value=%value',array('%name' => $name, '%value' => $value, WATCHDOG_ERROR));
		//watchdog('workflow', 'Request %request', array('%request' =>  $exploded["APIKEY"], WATCHDOG_ERROR));
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
