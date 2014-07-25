#!/bin/sh

mkdir $CATALINA_HOME/webapps/practice

cp -r src/main/webapp/* $CATALINA_HOME/webapps/practice

cp -r target/classes $CATALINA_HOME/webapps/practice/WEB-INF

cp -r lib $CATALINA_HOME/webapps/practice/WEB-INF
