#!/bin/sh

/usr/bin/java -cp /trino-java.jar "com.teamraft.DBLoadMain" citibike.csv my-cluster-kafka-bootstrap:9092
