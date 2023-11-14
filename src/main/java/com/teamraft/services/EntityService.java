package com.teamraft.services;

import com.teamraft.model.Entity;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EntityService {
//    static final String DB_URL = "jdbc:trino://my-trino:8080/entity/comms_broker?user=admin";
    static final String DB_URL = "jdbc:postgres://postgres-postgresql:5432/postgres?user=edwin&password=edwin";
    private XmlHelper xmlHelper = new XmlHelper();
    private KafkaProducer<String, String> producer;
    private String topicName;

    public EntityService() {
    }

    public EntityService(String bootstrapServers, String topicName) {
        this.topicName = topicName;
        Properties props = new Properties();

        System.err.printf("Bootstrap servers: '%s'\n", bootstrapServers);

        props.put(ProducerConfig.CLIENT_ID_CONFIG, "EntityTesting");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        producer = new KafkaProducer<>(props);
    }

    public void sendIt(List<String> entities) {
        System.out.printf("The list of records is %d in size%n", entities.size());

        entities.forEach(entity -> {

            try {
                Entity e = new Entity(entity, xmlHelper);

                ProducerRecord<String, String> record =
                        new ProducerRecord<>(topicName, UUID.randomUUID().toString(), e.toJson());
                Future<RecordMetadata> future = producer.send(record);

                future.get().hasOffset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        });

        producer.flush();
    }

    public List<String> getEntities(String filePath) {
        List<String> entities = new ArrayList<>();

        try (Reader inputReader =
                     new InputStreamReader(Objects.requireNonNull(EntityService.class.getClassLoader().getResourceAsStream(filePath)));
             BufferedReader bufferedReader = new BufferedReader(inputReader)) {

            String xml;

            while ((xml = bufferedReader.readLine()) != null) {
                entities.add(xml);
            }

            return entities;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Returning an empty array");
            return new ArrayList<>();
        }
    }


    public List<String> getDirEntities(String filePath) {
        List<String> entities = new ArrayList<>();

        try (Reader inputReader = new InputStreamReader(new FileInputStream(filePath));
             BufferedReader bufferedReader = new BufferedReader(inputReader)) {

            String xml;

            while ((xml = bufferedReader.readLine()) != null) {
                entities.add(xml);
            }

            return entities;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Returning an empty array");
            return new ArrayList<>();
        }
    }

    public Optional<Entity> findById(String uuid) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs;

        Optional<Entity> entity = Optional.empty();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ID, MESSAGE, DESCRIPTIVE_LABEL, MODE1, MODE2, MODE3, ");
        sql.append("MODE5, TAIL_NUMBER, CALL_SIGN, UPDATED_AT, IDENTITY_TIMESTAMP, ");
        sql.append("TRACK_NUMBER, LATITUDE, LONGITUDE FROM ENTITY WHERE ID = '").append(uuid).append("'");

        rs = stmt.executeQuery(sql.toString());

        /*
        String id, String message, String descriptiveLabel, String mode1, String mode2,
        String mode3, String mode5, String tailnumber, String callsign,
        Instant updatedAt, Instant identityTimestamp,
                  String trackNumber
         */

        if (rs.next()) {
            entity = Optional.of(new Entity(rs.getString("id"),
                    rs.getString("message"),
                    rs.getString("descriptive_label"),
                    rs.getString("mode1"),
                    rs.getString("mode2"),
                    rs.getString("mode3"),
                    rs.getString("mode5"),
                    rs.getString("tail_number"),
                    rs.getString("call_sign"),
                    rs.getLong("updated_at"),
                    rs.getLong("identity_timestamp"),
                    rs.getString("track_number"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude")));

        }
        stmt.close();
        conn.close();

        return entity;
    }

    public List<Entity> findByUuidList(List<String> uuids) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs;

        List<Entity> results = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ID, MESSAGE, DESCRIPTIVE_LABEL, MODE1, MODE2, MODE3, ");
        sql.append("MODE5, TAIL_NUMBER, CALL_SIGN, UPDATED_AT, IDENTITY_TIMESTAMP, ");
        sql.append("TRACK_NUMBER, LATITUDE, LONGITUDE FROM ENTITY WHERE ");
        sql.append("ID  IN (");
        sql.append(String.join("', '", uuids)).append("')");

        rs = stmt.executeQuery(sql.toString());

        /*
        String id, String message, String descriptiveLabel, String mode1, String mode2,
        String mode3, String mode5, String tailnumber, String callsign,
        Instant updatedAt, Instant identityTimestamp,
                  String trackNumber
         */

        while (rs.next()) {
            results.add(new Entity(rs.getString("id"),
                    rs.getString("message"),
                    rs.getString("descriptive_label"),
                    rs.getString("mode1"),
                    rs.getString("mode2"),
                    rs.getString("mode3"),
                    rs.getString("mode5"),
                    rs.getString("tail_number"),
                    rs.getString("call_sign"),
                    rs.getLong("updated_at"),
                    rs.getLong("identity_timestamp"),
                    rs.getString("track_number"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude")));
        }

        stmt.close();
        conn.close();

        return results;
    }

    public Optional<Entity> findByDescriptiveLabel(String descriptiveLabel) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs;

        Optional<Entity> entity = Optional.empty();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ID, MESSAGE, DESCRIPTIVE_LABEL, MODE1, MODE2, MODE3, ");
        sql.append("MODE5, TAIL_NUMBER, CALL_SIGN, UPDATED_AT, IDENTITY_TIMESTAMP, ");
        sql.append("TRACK_NUMBER, LATITUDE, LONGITUDE FROM ENTITY WHERE DESCRIPTIVE_LABEL = '");
        sql.append(descriptiveLabel).append("'");

        rs = stmt.executeQuery(sql.toString());

        /*
        String id, String message, String descriptiveLabel, String mode1, String mode2,
        String mode3, String mode5, String tailnumber, String callsign,
        Instant updatedAt, Instant identityTimestamp,
                  String trackNumber
         */

        if (rs.next()) {
            entity = Optional.of(new Entity(rs.getString("id"),
                    rs.getString("message"),
                    rs.getString("descriptive_label"),
                    rs.getString("mode1"),
                    rs.getString("mode2"),
                    rs.getString("mode3"),
                    rs.getString("mode5"),
                    rs.getString("tail_number"),
                    rs.getString("call_sign"),
                    rs.getLong("updated_at"),
                    rs.getLong("identity_timestamp"),
                    rs.getString("track_number"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude")));
        }
        stmt.close();
        conn.close();

        return entity;
    }

    public boolean persist(List<String> messages) {

        try {
            bulkInsert(messages);
        } catch (Exception e) {
            System.err.format("Error processing message, rolling back transaction: %s\n", e.getMessage());
            return false;
        }
        return true;
    }

    private void bulkInsert(List<String> entities) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

//        Connection conn = DriverManager.getConnection(DB_URL);

        // DB_HOST=postgres-postgresql DB_PORT=5432
        Connection conn = DriverManager.getConnection("jdbc:postgresql://postgres-postgresql:5432/postgres", "postgres", "4VhCiU9hdI");

        conn.setAutoCommit(false);

        Map<String, Entity> entityMap = new HashMap<>();

        List<Entity>listOfEntities = new ArrayList<>();

        entities.forEach(message -> {
            try {
                listOfEntities.add(new Entity(message, xmlHelper));
            } catch (XPathExpressionException | IOException | SAXException | ParseException e) {
                System.err.println(e.getMessage());
            }
        });
        StringBuilder sql = new StringBuilder("INSERT INTO entity (id,message, descriptive_label, mode1, mode2, mode3, mode5, tail_number, call_sign, updated_at, identity_timestamp, track_number, latitude, longitude) VALUES");
        sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement stmt = conn.prepareStatement(sql.toString());
        int i = 0;
        for (Entity entity : listOfEntities) {
            stmt.setString(1, entity.getId());
            stmt.setString(2, entity.getMessage());
            stmt.setString(3, entity.getDescriptiveLabel());
            stmt.setString(4, entity.getMode1());
            stmt.setString(5, entity.getMode2());
            stmt.setString(6, entity.getMode3());
            stmt.setString(7, entity.getMode5());
            stmt.setString(8, entity.getTailNumber());
            stmt.setString(9, entity.getCallSign());
            stmt.setTimestamp(10, Timestamp.from(Instant.now()));
            stmt.setTimestamp(11, Timestamp.from(Instant.ofEpochMilli(entity.getIdentityTimestamp())));
            stmt.setString(12, entity.getTrackNumber());
            stmt.setDouble(13, entity.getLatitude());
            stmt.setDouble(14, entity.getLongitude());
            stmt.addBatch();

            if (++i % 100 == 0) {
                stmt.executeBatch();
                conn.commit();
            }
        }

        stmt.executeBatch();
        conn.commit();
        stmt.close();
        conn.close();
    }

}
