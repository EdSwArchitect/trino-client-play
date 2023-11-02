#!/bin/sh

echo "The java command"
ls -l /usr/bin/java

/usr/bin/java -version

/usr/bin/java -cp /trino-java.jar com.teamraft.Main citibike.csv ed-kafka.ed-testing.svc.cluster.local:9092
