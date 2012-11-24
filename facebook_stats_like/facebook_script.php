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

require_once('json.php');
require_once('jsonpath.php');
require_once('facebook.php');


$config = parse_ini_file("config.ini");

$facebook = new Facebook(array(
  'appId'  => $config['appId'],
  'secret' => $config['secret'])
);
$access_token = $facebook->getAccessToken();

	
$parser = new Services_JSON(SERVICES_JSON_LOOSE_TYPE);
$url = "https://graph.facebook.com/Labdoo/posts?access_token=".$access_token;
	
echo "sending query ".$url."\n";	

$continue = true;
$ranking = array();
while ($continue) {
	$content = file_get_contents($url);

	$o = $parser->decode($content);
	$likes = jsonPath($o, "$..likes.data[-1:]");
	
	foreach ($likes as $like) {
		if (array_key_exists($like["id"],$ranking)) {
			$ranking[$like["id"]][0]++;
		}	else {
			$ranking[$like["id"]]= array(1,$like["name"],$like["id"]);
		
		}	
	}
	$next = jsonPath($o, "$..paging.next");
	if (sizeof($next)>0) {
		$url = $next[0];	
	} else {
		$continue = false;
	}
}
// // Sort the multidimensional array
#asort($ranking);
#$ranking =array_reverse($ranking);


$user_db=$config['user_db'];
$pass_db=$config['pass_db'];
$name_db=$config['name_db'];

$link = mysql_connect('127.0.0.1',$user_db, $pass_db) or die('I could not connect: ' . mysql_error());

echo 'Connected successfully';

mysql_select_db($name_db) or die('I could not use the database '.$name_db);
foreach($ranking as $key => $value)
{
	$query = 'REPLACE into facebook_ranking (id,name,hits) values (\''.$value[2].'\',\''.$value[1].'\','.$value[0].')';
	$result = mysql_query($query) or die('Failed query: ' . mysql_error());
}
// Close connection
mysql_close($link);

?>
