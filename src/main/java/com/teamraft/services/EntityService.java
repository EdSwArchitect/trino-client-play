package com.teamraft.services;

import com.teamraft.model.Entity;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
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
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EntityService {
    static final String DB_URL = "jdbc:trino://my-trino:8080/entity/comms-broker?user=admin";
    private XmlHelper xmlHelper = new XmlHelper();

    public List<String>getEntities(String filePath) {
        List<String>entities = new ArrayList<>();

        try (Reader inputReader =
             new InputStreamReader(Objects.requireNonNull( EntityService.class.getClassLoader().getResourceAsStream(filePath)));
            BufferedReader bufferedReader = new BufferedReader(inputReader)
        ) {

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
        sql.append("TRACK_NUMBER FROM ENTITY WHERE ID = '").append(uuid).append("'");

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
                    rs.getTimestamp("updated_at").toInstant(),
                    rs.getTimestamp("identity_timestamp").toInstant(),
                    rs.getString("track_number")));
        }
        stmt.close();

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
        sql.append("TRACK_NUMBER FROM ENTITY WHERE ");
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
                    rs.getTimestamp("updated_at").toInstant(),
                    rs.getTimestamp("identity_timestamp").toInstant(),
                    rs.getString("track_number")));
        }

        stmt.close();

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
        sql.append("TRACK_NUMBER FROM ENTITY WHERE DESCRIPTIVE_LABEL = '");
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
                    rs.getTimestamp("updated_at").toInstant(),
                    rs.getTimestamp("identity_timestamp").toInstant(),
                    rs.getString("track_number")));
        }
        stmt.close();

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

    private void bulkInsert(List<String> entities) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Map<String, Entity> entityMap = new HashMap<>();

        entities.forEach(message -> {
            Entity entity;
            try {
                entity = new Entity(message, xmlHelper);
                entityMap.put(entity.getId(), entity);
            } catch (XPathExpressionException | IOException | SAXException e) {
                System.err.println(e.getMessage());
            }
        });
        StringBuilder sql = new StringBuilder("INSERT INTO entity (id,message, descriptive_label, mode1, mode2, mode3, mode5, tail_number, call_sign, updated_at, identity_timestamp, track_number) VALUES");
        sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        PreparedStatement stmt = conn.prepareStatement(sql.toString());

        for (Entity entity : entityMap.values()) {
            stmt.setString(1, entity.getId());
            stmt.setString(2, entity.getMessage());
            stmt.setString(3, entity.getDescriptiveLabel());
            stmt.setString(4, entity.getMode1());
            stmt.setString(5, entity.getMode2());
            stmt.setString(6, entity.getMode3());
            stmt.setString(7, entity.getMode5());
            stmt.setString(8, entity.getTailnumber());
            stmt.setString(9, entity.getCallsign());
            stmt.setTimestamp(10, Timestamp.from(Instant.now()));
            stmt.setTimestamp(11, Timestamp.from(entity.getIdentityTimestamp()));
            stmt.setString(12, entity.getTrackNumber());

            stmt.addBatch();
        }

        stmt.executeBatch();
    }

}
