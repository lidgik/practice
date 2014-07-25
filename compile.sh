#!/bin/sh

mkdir target/classes

javac -d target/classes -classpath $CATALINA_HOME/lib/servlet-api.jar src/main/java/com/bodejidi/*.java

