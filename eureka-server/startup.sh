#!/bin/sh

CLASSPATH="eureka-server-1.0-SNAPSHOT.jar"

java -XX:-PrintGC -XX:+PrintGCDetails -verbose:gc -Xloggc:gc.log -jar  $CLASSPATH
