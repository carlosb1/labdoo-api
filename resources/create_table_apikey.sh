#!/bin/sh

query1="use trackla_labdoo;"
query2="CREATE TABLE api_key ( key_generated VARCHAR(60), name VARCHAR(16), email VARCHAR(40), uid INT, PRIMARY KEY (email));"
SQL="${query1}${query2}"
  
mysql -uroot -p -e "$SQL"
