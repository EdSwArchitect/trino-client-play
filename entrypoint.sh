#!/bin/sh

#/usr/bin/java -cp /trino-java.jar "com.teamraft.Main" citibike.csv ed-kafka.ed-testing.svc.cluster.local:9092
/usr/bin/java -cp /trino-java.jar "com.teamraft.Main" citibike.csv my-cluster-kafka-bootstrap:9092
