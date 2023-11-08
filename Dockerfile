FROM maven:3.8.3-openjdk-17

COPY entrypoint.sh /entrypoint.sh
COPY jdbc-entrypoint.sh /jdbc-entrypoint.sh
COPY jdbc-store-entrypoint.sh /jdbc-store-entrypoint.sh
COPY db-load-list-entrypoint.sh-entrypoint.sh /db-load-list-entrypoint.sh
COPY target/trino.java-1.0-SNAPSHOT-jar-with-dependencies.jar /trino-java.jar

RUN mkdir /data
COPY src/data/entities_* /data/

ENTRYPOINT ["/entrypoint.sh"]
