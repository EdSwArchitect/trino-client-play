package com.teamraft.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityLocation {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private String id;
    long updatedAt;
    String trackNumber;
    long identityTimestamp;

    private double latitude;
    private double longitude;

    public EntityLocation(Entity entity) {
        this.id = entity.id;
        this.latitude = entity.latitude;
        this.longitude = entity.longitude;
        this.updatedAt = entity.updatedAt != 0 ? entity.updatedAt : System.currentTimeMillis();
        this.identityTimestamp = entity.identityTimestamp != 0 ? entity.identityTimestamp : System.currentTimeMillis();
        this.trackNumber = entity.trackNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public long getIdentityTimestamp() {
        return identityTimestamp;
    }

    public void setIdentityTimestamp(long identityTimestamp) {
        this.identityTimestamp = identityTimestamp;
    }

    public String toJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
}
