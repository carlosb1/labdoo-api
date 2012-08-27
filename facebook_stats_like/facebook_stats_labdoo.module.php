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

require_once('facebook_stats_labdoo.inc');

function facebook_stats_menu() {
  // This is the minimum information you can provide for a menu item. This menu
  // item will be created in the default menu (Navigation).
  $items['labdoo_api/ranking'] = array(
    'title' => 'Labdoo - Ranking',
    'page callback' => 'facebook_stats_simple',
	'access callback' => TRUE,
	'type' => MENU_CALLBACK,
  );

  return $items;
}

function facebook_stats_simple() {
  $ranking = getStadistics ();
  output = "<table border=\"1\">";
  output.="<tr><th>Likes</th><th>Names</th><th>IDs</th></tr>";
  foreach($ranking as $key => $value)
  {
  	output.="<tr><th>".value[0]."</th><th>".value[1]."</th><th>".$key."</th></tr><p/>";
  }
  output.="</table>";
  return '<p>' .output . '</p>';
}


