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

class xmlrpc_client {
    private $url;
    function __construct($url, $autoload=true) {
        $this->url = $url;
        $this->connection = new curl;
        $this->methods = array();
        if ($autoload) {
            $resp = $this->call('system.listMethods', null);
            $this->methods = $resp;
        }
    }
    public function call($method, $params = null) {
        $post = xmlrpc_encode_request($method, $params);
        return xmlrpc_decode($this->connection->post($this->url, $post));
    }
}

/**header('Content-Type: text/plain');
$rpc = "http://10.0.0.10/api.php";
$client = new xmlrpc_client($rpc, true);
$resp = $client->call('methodname', array());
 * **/