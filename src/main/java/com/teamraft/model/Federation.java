package com.teamraft.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.sql.Timestamp;

public class Federation {
    private static ObjectMapper objectMapper = new ObjectMapper();
    String id;
    String tailNumber;
    String callSign;
    String trackNumber;
    double latitude;
    double longitude;
    Timestamp identityTimestamp;

    public Federation() {}

    public Federation(String id) {
        this.id = id;
    }

    public Federation(String id, String tailNumber, String callSign, String trackNumber, double latitude, double longitude, Timestamp identityTimestamp) {
        this.id = id;
        this.tailNumber = tailNumber;
        this.callSign = callSign;
        this.trackNumber = trackNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.identityTimestamp = identityTimestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public String getCallSign() {
        return callSign;
    }


    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public Timestamp getIdentityTimestamp() {
        return identityTimestamp;
    }

    public void setIdentityTimestamp(Timestamp identityTimestamp) {
        this.identityTimestamp = identityTimestamp;
    }

    public String toJson() throws JsonProcessingException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return  objectMapper.writeValueAsString(this);
    }

    @Override
    public String toString() {
        try {
            return toJson();
        } catch (JsonProcessingException e) {
            final StringBuffer sb = new StringBuffer("Federation{");
            sb.append("id='").append(id).append('\'');
            sb.append(", tailNumber='").append(tailNumber).append('\'');
            sb.append(", callSign='").append(callSign).append('\'');
            sb.append(", trackNumber='").append(trackNumber).append('\'');
            sb.append(", latitude=").append(latitude);
            sb.append(", longitude=").append(longitude);
            sb.append(", identityTimestamp=").append(identityTimestamp);
            sb.append('}');
            return sb.toString();
        }
    }
}
