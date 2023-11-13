package com.teamraft.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamraft.model.Federation;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FederationService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
//    static final String DB_URL = "jdbc:trino://my-trino:8080/entity_location/comms_broker?user=admin";
    static final String DB_URL = "jdbc:trino://my-trino:8080/entity/comms_broker?user=admin";
    private XmlHelper xmlHelper = new XmlHelper();
    private KafkaProducer<String, String> producer;
    private String topicName;

    public FederationService() {
    }

    public List<Federation> getFederation() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs;

        List<Federation>list = new ArrayList<>();

        StringBuilder sql = new StringBuilder();

        /**
         * SELECT e.id AS, e.tail_number, e.updated_at, e.call_sign, e.track_number,
         * geo.latitude, geo.longitude from
         * entity.comms_broker.entity as e,
         * pinot.default.entitylocation as geo
         * where e.id = geo.id
         * order by e.id;
         */

        sql.append("SELECT \"e.id, e.tail_number, e.identity_timestamp, e.call_sign, e.track_number, ");
        sql.append("geo.latitude, geo.location from");
        sql.append("entity.comms_broker.entity as e, pinot.default.entityLocation as geo ");
        sql.append("where e.id = geo.id order by e.id\"");
        sql.append(" limit 100");
        rs = stmt.executeQuery(sql.toString());

        /*
           String id, String tailNumber, String callSign, String trackNumber, double latitude, double longitude

         */

        if (rs.next()) {
            list.add(new Federation(rs.getString("id"),
                    rs.getString("tail_number"),
                    rs.getString("call_sign"),
                    rs.getString("track_number"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"),
                    rs.getTimestamp("identity_timestamp")));
        }
        stmt.close();
        conn.close();

        return list;
    }

}
