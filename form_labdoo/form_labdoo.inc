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

module_load_include('inc', 'content', 'includes/content.node_form');
module_load_include('inc', 'labdoo_nodes', 'labdoo');
 
/**
 * Adds logic to our form builder to give it two pages. It checks a
 * value in $form_state['storage'] to determine if it should display page 2.
 */
function form_laptop($form_state) {

/**TODO move fields in order to create a sorted form **/
  $form['description'] = array(
    '#type' => 'item',
    '#title' => t('Labdoo API - Laptop form'),
  );


    $form['copy'] = array(
    '#type' => 'fieldset',
    '#title' => t('Copy parameters'),
  );
  
    $form['copy']['copies'] = array(
    '#type' => 'textfield',
    '#title' => 'Number of laptops to tag',
     '#size' => 3, // added
    '#maxlength' => 4, // added      
  );	
  
  
    $form['parameters'] = array(
    '#type' => 'fieldset',
    '#title' => t('Parameters'),
    '#collapsible' => TRUE,
    '#collapsed' => FALSE,
    
  );
  
  	/** Obligatory parameters **/ 
  	$field = content_fields('field_current_manager');
	$form['#basic']['field_current_manager'] = $field;
	$form['parameters'] += (array) content_field_form($form, $form_state, $field);
	
    $form['parameters']['field_current_manager'] = array(
    '#type' => 'textfield',
    '#required' => TRUE,
    '#title' => 'Current laptop manager',
     '#size' => 64, // added
    '#maxlength' => 64, // added
	'#weight' => 1,
    '#description' => "Write here the user name of the person who is currently in charge of the laptop. 
    					If you are creating a new laptop, the system will assign by default your user name and you should not change that. 
    					If you are modifying this laptop and now you will be managing it, then you should overwrite this value with your user name.",
       
  );	
  

	
  	$form['parameters']['field_laptop_domain'] = array(
    '#type' => 'select',
    '#title' => t('Domain'),
    '#required' => TRUE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => get_Domain_list(),    
	'#weight' => 2,  	
  );
	
	
	
	$field = content_fields('field_current_manager');
	$form['#basic']['field_current_manager'] = $field;
	$form['parameters']+= (array) content_field_form($form, $form_state, $field);
	
	
	/** laptop parameters  **/ 					
	
	 $form['parameters']['field_cpu_type'] = array(
    '#type' => 'select',
    '#title' => t('Number of CPUs'),
    '#required' => FALSE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => get_CPU_Type_list(),
	'#weight' => 4,  	
  	);
	

	/** labdoo parameters  **/ 

	$field = content_fields('field_model');
	$form['#basic']['field_model'] = $field;
	$form['parameters']+= (array) content_field_form($form, $form_state, $field);		
		
	$field = content_fields('field_memory');
	$form['#basic']['field_memory'] = $field;
	$form['parameters']+= (array) content_field_form($form, $form_state, $field);	

	$field = content_fields('field_cpu');
	$form['#basic']['field_cpu'] = $field;
	$form['parameters'] += (array) content_field_form($form, $form_state, $field);
	
	
  	$form['parameters']['field_status'] = array(
    '#type' => 'select',
    '#title' => t('Status'),
    '#required' => TRUE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => get_Status_list(),
	'#weight' => 3,  	
  );
	

	$field = content_fields('field_hard_drive');
	$form['#basic']['field_hard_drive'] = $field;
	$form['parameters'] += (array) content_field_form($form, $form_state, $field);
	
	

  $form['parameters']['date'] = array(	
      '#type' => 'date', 
      '#title' => check_plain("Date"), 
      '#description' => "Date available",
      '#required' => TRUE,
		'#weight' => 5,       
     );	
	
	/** location **/
	
  	$form['parameters']['field_dev_type'] = array(
    '#type' => 'select',
    '#title' => t('Device type'),
    '#required' => TRUE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => get_Device_Type_list(),
 	'#weight' => 6,  	
  );	
	
		
	$form['parameters']['city'] = array(
    '#type' => 'textfield',
    '#title' => t('City'),
    '#required' => TRUE,
   # '#default_value' => "First name", // added default value.
    '#size' => 64, // added
    '#maxlength' => 64, // added
	'#weight' => 7, 
      );

  	$form['parameters']['country'] = array(
    '#type' => 'select',
    '#title' => t('Country'),
    '#required' => TRUE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => location_get_iso3166_list(),    
	'#weight' => 8, 	
  );
  
  $form['buttons'] = array(
    '#type' => 'fieldset',
    '#title' => t('Buttons'),
    '#collapsible' => TRUE,
    '#collapsed' => FALSE,
 	'#weight' => 20, 	   
  );
  
  
    $form['buttons']['submit'] = array(
    '#type' => 'submit',
    '#value' => 'Submit',
  
  );

  return $form;
}

function get_Status_list() {
	$statusList = array(
        'Tagged with a Labdoo ID (S0)' => t('Tagged with a Labdoo ID (S0)'), 
        'Donated, in quality assurance process (S1)' => t('Donated, in quality assurance process (S1)'), 
        'Passed quality assurance (S2)' => t('Passed quality assurance (S2)'),
        'Assigned to a child, waiting to be shipped (S3)' => t('Assigned to a child, waiting to be shipped (S3)'),
        'Delivered and being used (S4)' => t('Delivered and being used (S4)'),
        'Waiting to be recycled (S5)' => t('Waiting to be recycled (S5)'),       
        'Recycled (S6)' => t('Recycled (S6)'),
        '(Library) Checked out (S7)' => t('(Library) Checked out (S7)'),
        '(Library) Available (S8)' => t('(Library) Available (S8)'),                   
      );
	  return $statusList;
}

function get_Domain_list() {
	$domainList = array(
        'Individual volunteer (grassroots)' => t('Individual volunteer (grassroots)'), 
        'Casasito, Guatemala' => t('Casasito, Guatemala'), 
        'Engineers Without Borders @ UCI' => t('Engineers Without Borders @ UCI'),
        'Engineers Without Borders @ Western Massachusetts' => t('Engineers Without Borders @ Western Massachusetts'),
        'Foxconn' => t('Foxconn'),
        'I2CAT, Barcelona' => t('I2CAT, Barcelona'),       
        'MerciMed, New York' => t('MerciMed, New York'),
        'SpringSoft' => t('SpringSoft'),
        'University of California, Irvine' => t('University of California, Irvine'),
        'University of California, Los Angeles' => t('University of California, Los Angeles'),      
        'World Computer Exchange, Boston' => t('(World Computer Exchange, Boston'),                   
      );
	  return $domainList;
}

function get_CPU_Type_list() {
	$cpuTypeList = array(
        '1 (Single core)' => t('1 (Single core)'), 
        '2 (Duo core)' => t('2 (Duo core)'), 
        '3' => t('3'),
        '4' => t('4'),
        '5' => t('5'),
        '6' => t('6'),       
        '7' => t('7'),
        '8' => t('8'),
      );
	  return $cpuTypeList;
}


function get_Device_Type_list() {
	$deviceTypeList = array(
        'Laptop' => t('Laptop'), 
        'Google phone' => t('Google phone'), 
        'iPhone' => t('iPhone'),
        'Internet tablet' => t('Internet tablet'),
        'iPad' => t('iPad'),
        'Desktop' => t('Desktop')
      );
	  return $deviceTypeList;
}

function form_laptop_submit($form, &$form_state) {

/**	    drupal_set_message(t('The form has been submitted. name="@first @last", year of birth=@year_of_birth',
      array('@first' => $page_one_values['first'], '@last' => $page_one_values['last'], '@year_of_birth' => $page_one_values['year_of_birth'])));
**/	
	drupal_set_message(t('The form has been submitted'));
	$params = $form_state['values'];
	
	
	/**TODO validate which is an integer **/
	$numberOfCopies = $params['copies'];



	$params['date']=normalize_date($params['date']);

	
	drupal_set_message(t('date :@date',array('@date' => $date)));

	
	unset($params['copies']);
	unset($params['form_id']);
	unset($params['form_token']);
	unset($params['form_build_id']);	
	unset($params['submit']);
	unset($params['op']);
	/** TODO parse dates and locations **/
	unset($params['city']);
	unset($params['country']);
	
	
	$arrayNIDs = array();
	
	drupal_set_message(t('List of form\'s titles '));
	foreach (range(1, $numberOfCopies) as $number) {
		$nid = save_laptop($form_state['values']);
		$node = get_laptop($nid);
		drupal_set_message(t('laptop @number -> @title',array('@number' => $number,'@title' => $node['title'])));
		
	}
	
}

function form_laptop_validate($form, &$form_state) {
  if (!is_int($form_state['values']['copy']['copies'])) {
      form_set_error('copies', t('Please enter an integer number.'));
  }

}
function normalize_date($date) {
	$day=$date['day'];
	$month=$date['month'];
	$year=$date['year'];
	$date=$year."-".$month."-".$year."T00:00:00";
	return $date;
	
}
