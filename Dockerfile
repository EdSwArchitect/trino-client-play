FROM maven:3.8.3-openjdk-17

COPY entrypoint.sh /entrypoint.sh
COPY jdbc-entrypoint.sh /jdbc-entrypoint.sh

COPY target/trino.java-1.0-SNAPSHOT-jar-with-dependencies.jar /trino-java.jar

ENTRYPOINT ["/entrypoint.sh"]
