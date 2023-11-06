FROM maven:3.8.3-openjdk-17

COPY entrypoint.sh /entrypoint.sh

#RUN mkdir /source

#COPY src/ /source/
#COPY pom.xml /source/pom.xml

#WORKDIR "/source"

COPY target/trino.java-1.0-SNAPSHOT-jar-with-dependencies.jar /trino-java.jar
#COPY target/classes/big-citibike-tripdata.csv big-citibike.csv
#COPY target/classes/citibike.csv citibike.csv

#RUN mvn -DskipTests package

#RUN cp target/trino.java-1.0-SNAPSHOT-jar-with-dependencies.jar /trino-java.jar

RUN ls -l /
#CMD ["java", "-cp", "/trino-java.jar", "com.teamraft.Main", "citibike.csv", "host.docker.internal:9092"]

ENTRYPOINT ["/entrypoint.sh"]
