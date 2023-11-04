#!/bin/sh

echo "The java command"
ls -l /usr/bin/java

/usr/bin/java -version

ls -l /trino-java.jar

jar tvf /trino-java.jar | grep Main


#/usr/bin/java -cp /trino-java.jar "com.teamraft.Main" citibike.csv ed-kafka.ed-testing.svc.cluster.local:9092
/usr/bin/java -cp /trino-java.jar "com.teamraft.Main" citibike.csv my-cluster-kafka-bootstrap:9092
