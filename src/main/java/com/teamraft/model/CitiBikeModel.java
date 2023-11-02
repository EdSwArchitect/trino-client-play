package com.teamraft.model;

import java.sql.Timestamp;

public class CitiBikeModel {
    private String rideId;
    private String rideableType;
    private Timestamp startedAt;
    private Timestamp endedAt;
    private String startStationName;
    private String startStationId;
    private String endStationName;
    private String endStationId;
    private double startLat;
    private double startLon;
    private double endLat;
    private double endLon;
    private String memberCasual;


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

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public Timestamp getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Timestamp endedAt) {
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n{");
        sb.append("\"rideId\" : \"").append(rideId).append("\", ");
        sb.append("\"rideableType\" : \"").append(rideableType).append("\", ");
        sb.append("\"startedAt\" : \"").append(startedAt).append("\", ");
        sb.append("\"endedAt\" : \"").append(endedAt).append("\", ");
        sb.append("\"startStationName\" : \"").append(startStationName).append("\", ");
        sb.append("\"startStationId\" : \"").append(startStationId).append("\", ");
        sb.append("\"endStationName\" : \"").append(endStationName).append("\", ");
        sb.append("\"endStationId\" : \"").append(endStationId).append("\", ");
        sb.append("\"startLat\" : ").append(startLat).append(",");
        sb.append("\"startLon\" : ").append(startLon).append(",");
        sb.append("\"endLat\" : ").append(endLat).append(",");
        sb.append("\"endLon\" : ").append(endLon).append(",");
        sb.append("\"memberCasual\" : \"").append(memberCasual).append("\"}");
        return sb.toString();
    }
}