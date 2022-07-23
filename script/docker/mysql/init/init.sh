#!/bin/bash

mysql -uroot <<EOF
source /usr/local/sql/init.sql;
use tutor;
source /usr/local/sql/tutor-mysql.sql;
use xxl_job;
source /usr/local/sql/tables_xxl_job.sql;
use nacos_config;
source /usr/local/sql/nacos-mysql.sql;
EOF