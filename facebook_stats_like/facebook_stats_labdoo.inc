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



function getStadistics () {

	$parser = new Services_JSON(SERVICES_JSON_LOOSE_TYPE);
	$url = "https://graph.facebook.com/Labdoo/posts?access_token=[token]";
	
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
				$ranking[$like["id"]]= array(1,$like["name"]);
			
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
	asort($ranking);
	
	return array_reverse($ranking);
}

?>