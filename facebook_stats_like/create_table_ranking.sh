#!/bin/sh

query1="use trackla_labdoo;"
query2="CREATE TABLE facebook_ranking ( id VARCHAR(60) NOT NULL,name VARCHAR(60), hits INT, PRIMARY KEY (id));"
SQL="${query1}${query2}"
  
mysql -uroot -p -e "$SQL"
