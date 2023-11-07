#!/bin/sh

#/usr/bin/java -cp /trino-java.jar "com.teamraft.DBMain" citibike.csv my-cluster-kafka-bootstrap:9092

/usr/bin/java -cp /trino-java.jar "com.teamraft.DBMain" citibike.csv my-cluster-kafka-bootstrap:9092 entity
