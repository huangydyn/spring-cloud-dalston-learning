#!/bin/sh

CLASSPATH="eureka-consumer-1.0-SNAPSHOT.jar"

MAIN_CLASS="org.springframework.boot.loader.PropertiesLauncher"
JAVA_CMD=java
echo $CLASSPATH

$JAVA_CMD -classpath "$CLASSPATH" $MAIN_CLASS