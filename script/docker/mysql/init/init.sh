#!/bin/bash

mysql -uroot <<EOF
source /usr/local/sql/init.sql;
use nacos_config;
source /usr/local/sql/nacos-mysql.sql;
use tutor;
source /usr/local/sql/tutor.sql;
EOF