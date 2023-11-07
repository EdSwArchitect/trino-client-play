package com.teamraft.services;

import com.teamraft.csv.CitiBike;
import com.teamraft.model.CitiBikeModel;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CitiBikeService {
    static final String DB_URL = "jdbc:trino://my-trino:8080/kafka/citi?user=admin";
    public List<CitiBike> getBikes(String fileName) {

        try (Reader inputReader = new InputStreamReader(Objects.requireNonNull( CitiBikeService.class.getClassLoader().getResourceAsStream(fileName)))) {

	    System.out.println("******* GOT RESOURCE FROM JAR");

            BeanListProcessor<CitiBike> rowProcessor = new BeanListProcessor<>(CitiBike.class);
            CsvParserSettings settings = new CsvParserSettings();
            settings.setHeaderExtractionEnabled(true);
            settings.setProcessor(rowProcessor);
            CsvParser parser = new CsvParser(settings);
            parser.parse(inputReader);

            return rowProcessor.getBeans();
        } catch (IOException e) {
            e.printStackTrace();
	    System.out.println("Returning an empty array");
            return new ArrayList<>();
            // handle exception
        }
    }

    public List<CitiBikeModel>search() throws SQLException, ClassNotFoundException {
        List<CitiBikeModel>bikes = new ArrayList<>();

        Class.forName("io.trino.jdbc.TrinoDriver");

        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        ResultSet rs;

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ride_id, rideable_type, started_at, ");
        sql.append("ended_at, start_station_name, start_station_id, ");
        sql.append("end_station_name, ");
        sql.append("end_station_id, start_lat, start_lng, end_lat, ");
        sql.append("end_lng, member_casual from citibike order by started_at, ended_at DESC ");
        sql.append("FETCH FIRST 100 ROWS ONLY");

        rs = stmt.executeQuery(sql.toString());
        while ( rs.next() ) {
            CitiBikeModel citiBikeModel = new CitiBikeModel();
            citiBikeModel.setRideId(rs.getString("ride_id"));
            citiBikeModel.setRideableType(rs.getString("rideable_type"));
            citiBikeModel.setStartedAt(rs.getTimestamp("started_at"));
            citiBikeModel.setEndedAt(rs.getTimestamp("ended_at"));
            citiBikeModel.setStartStationName(rs.getString("start_station_name"));
            citiBikeModel.setStartStationId(rs.getString("start_station_id"));
            citiBikeModel.setEndStationName(rs.getString("end_station_name"));
            citiBikeModel.setEndStationId(rs.getString("end_station_id"));
            citiBikeModel.setStartLat(rs.getDouble("start_lat"));
            citiBikeModel.setStartLon(rs.getDouble("start_lng"));
            citiBikeModel.setEndLat(rs.getDouble("end_lat"));
            citiBikeModel.setEndLon(rs.getDouble("end_lng"));
            citiBikeModel.setMemberCasual(rs.getString("member_casual"));

            bikes.add(citiBikeModel);

        }
        conn.close();

        return bikes;
    }


}
