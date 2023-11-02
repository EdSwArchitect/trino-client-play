package com.teamraft.csv;

import com.univocity.parsers.annotations.Parsed;

public class CitiBike {
    @Parsed(field="ride_id")
    private String rideId;
    @Parsed(field="rideable_type")
    private String rideableType;
    @Parsed(field="started_at")
    private String startedAt;
    @Parsed(field="ended_at")
    private String endedAt;
    @Parsed(field="start_station_name")
    private String startStationName;
    @Parsed(field="start_station_id")
    private String startStationId;
    @Parsed(field="end_station_name")
    private String endStationName;
    @Parsed(field="end_station_id")
    private String endStationId;
    @Parsed(field="start_lat")
    private double startLat;
    @Parsed(field="start_lng")
    private double startLon;
    @Parsed(field="end_lat")
    private double endLat;
    @Parsed(field="end_lng")
    private double endLon;
    @Parsed(field="member_casual")
    private String memberCasual;

    public CitiBike() {
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getRideableType() {
        return rideableType;
    }

    public void setRideableType(String rideableType) {
        this.rideableType = rideableType;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(String endedAt) {
        this.endedAt = endedAt;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(String startStationId) {
        this.startStationId = startStationId;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public String getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(String endStationId) {
        this.endStationId = endStationId;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLon() {
        return startLon;
    }

    public void setStartLon(double startLon) {
        this.startLon = startLon;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public double getEndLon() {
        return endLon;
    }

    public void setEndLon(double endLon) {
        this.endLon = endLon;
    }

    public String getMemberCasual() {
        return memberCasual;
    }

    public void setMemberCasual(String memberCasual) {
        this.memberCasual = memberCasual;
    }

    public String toJson() {
        return "json";
    }
}
