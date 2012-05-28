<?php

module_load_include('inc', 'content', 'includes/content.node_form');


/**
 * @file
 * This is the Form API Tutorial from the handbook.
 * @see http://drupal.org/node/262422
 *
 * It goes through ten form examples in increasing complexity to demonstrate
 * Drupal 6 Form API.
 *
 * Links are provided inline for the related handbook pages.
 */

function form_example_tutorial() {
  return t('This is a set of ten form tutorials tied to the <a href="http://drupal.org/node/262422">Drupal handbook</a>.');
}




//////////////// Tutorial Example 10 //////////////////////

/**
 * Example 10: A simple multistep form.
 *
 * Handbook page: http://drupal.org/node/717750.
 *
 * For more extensive multistep forms,
 * @see http://pingvision.com/blog/ben-jeavons/2009/multi-step-forms-drupal-6-using-variable-functions
 *
 */
 
 
/**
 * Adds logic to our form builder to give it two pages. It checks a
 * value in $form_state['storage'] to determine if it should display page 2.
 */
function form_example_tutorial_10($form_state) {

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

  
    $form['basic'] = array(
    '#type' => 'fieldset',
    '#title' => t('Basic parameters'),
  );	
	
  
  	/** Obligatory parameters **/ 
  	$field = content_fields('field_current_manager');
	$form['#basic']['field_current_manager'] = $field;
	$form['basic'] += (array) content_field_form($form, $form_state, $field);


	
  	$form['basic']['domain_type'] = array(
    '#type' => 'select',
    '#title' => t('Domain'),
    '#required' => TRUE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => get_Domain_list(),    
  );
	
	
	
	$field = content_fields('field_current_manager');
	$form['#basic']['field_current_manager'] = $field;
	$form['basic'] += (array) content_field_form($form, $form_state, $field);
	
	
	/** location parameters **/ 
	
	
	/** laptop parameters  **/ 					
	
	 $form['basic']['cpu_type'] = array(
    '#type' => 'select',
    '#title' => t('Number of CPUs'),
    '#required' => FALSE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => get_CPU_Type_list(),    
  	);
	

	/** labdoo parameters  **/ 

	$field = content_fields('field_model');
	$form['#basic']['field_model'] = $field;
	$form['basic'] += (array) content_field_form($form, $form_state, $field);		
		
	$field = content_fields('field_memory');
	$form['#basic']['field_memory'] = $field;
	$form['basic'] += (array) content_field_form($form, $form_state, $field);	

	$field = content_fields('field_cpu');
	$form['#basic']['field_cpu'] = $field;
	$form['basic'] += (array) content_field_form($form, $form_state, $field);
	
	
  	$form['basic']['status_type'] = array(
    '#type' => 'select',
    '#title' => t('Status'),
    '#required' => TRUE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => get_Status_list(),    
  );
	

	$field = content_fields('field_hard_drive');
	$form['#basic']['field_hard_drive'] = $field;
	$form['basic'] += (array) content_field_form($form, $form_state, $field);
	
	

  $form['basic']['date'] = array(	
      '#type' => 'date', 
      '#title' => check_plain("Date"), 
      '#description' => "Date received",
      '#required' => TRUE,
     );	
	
	/** location **/
	
  	$form['basic']['device_type'] = array(
    '#type' => 'select',
    '#title' => t('Device type'),
    '#required' => TRUE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => get_Device_Type_list(),    
  );	
	
  $form['location'] = array(
    '#type' => 'fieldset',
    '#title' => t('Location'),
    '#collapsible' => TRUE,
    '#collapsed' => FALSE,    
  );
		
	$form['location']['city'] = array(
    '#type' => 'textfield',
    '#title' => t('City'),
    '#required' => TRUE,
   # '#default_value' => "First name", // added default value.
    '#size' => 64, // added
    '#maxlength' => 64, // added
  );

  	$form['location']['country'] = array(
    '#type' => 'select',
    '#title' => t('Country'),
    '#required' => TRUE,
    # '#default_value' => variable_get('feed_item_length','teaser'),
  	'#options' => location_get_iso3166_list(),    
	
  );
  
    $form['submit'] = array(
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




/**
 * Modifies this function so that it will respond appropriately based on
 * which page was submitted. If the first page is being submitted,
 * values in the 'storage' array are saved and the form gets
 * automatically reloaded.
 *
 * If page 2 was submitted, we display a message and redirect the
 * user to another page.
 */
function form_example_tutorial_10_submit($form, &$form_state) {
	
/**	    drupal_set_message(t('The form has been submitted. name="@first @last", year of birth=@year_of_birth',
      array('@first' => $page_one_values['first'], '@last' => $page_one_values['last'], '@year_of_birth' => $page_one_values['year_of_birth'])));
**/	
	drupal_set_message(t('The form has bee submitted'));

}
