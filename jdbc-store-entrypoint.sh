#!/bin/sh

/usr/bin/java -cp /trino-java.jar "com.teamraft.DBLoadMain" my-cluster-kafka-bootstrap:9092 5000-entities.xml
