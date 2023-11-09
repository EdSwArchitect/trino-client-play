package com.teamraft.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.teamraft.services.XmlHelper;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;

public class Entity {
    private static ObjectMapper objectMapper = new ObjectMapper();
    String id;
    String message;
    String descriptiveLabel;
    String mode1;
    String mode2;
    String mode3;
    String mode5;
    String tailNumber;
    String callSign;
    long updatedAt;
    String trackNumber;
    long identityTimestamp;
    double latitude;
    double longitude;

    public Entity() {}

    public Entity(String id, String message, String descriptiveLabel) {
        this.id = id;
        this.message = message;
        this.descriptiveLabel = descriptiveLabel;
        this.updatedAt = System.currentTimeMillis();
        this.identityTimestamp = System.currentTimeMillis();
    }

    public Entity(String xml, XmlHelper xmlHelper) throws IOException, SAXException, XPathExpressionException, ParseException {
        Document document = xmlHelper.convertToDocument(xml);
        this.id = xmlHelper.getValues(EntityXPathConfig.id, document);
        this.message = xml;
        this.descriptiveLabel = xmlHelper.getField(EntityXPathConfig.descriptiveLabelPath, document);
        this.mode1 = xmlHelper.getValues(EntityXPathConfig.mode1, document);
        this.mode2 = xmlHelper.getValues(EntityXPathConfig.mode2, document);
        this.mode3 = xmlHelper.getValues(EntityXPathConfig.mode3, document);
        this.mode5 = xmlHelper.getValues(EntityXPathConfig.mode5, document);
        this.tailNumber = xmlHelper.getValues(EntityXPathConfig.tailNumber, document);
        this.callSign = xmlHelper.getValues(EntityXPathConfig.callSign, document);
        String val = xmlHelper.getValues(EntityXPathConfig.identityTimestamp, document);
        this.identityTimestamp = Instant.parse(val).toEpochMilli();
        this.trackNumber = xmlHelper.getValues(EntityXPathConfig.trackNumber, document);
        this.updatedAt = System.currentTimeMillis();
        this.latitude = Double.parseDouble(xmlHelper.getValues(EntityXPathConfig.latitude, document));
        this.longitude = Double.parseDouble(xmlHelper.getValues(EntityXPathConfig.longitude, document));
    }


    public Entity(String id, String message, String descriptiveLabel, String mode1, String mode2, String mode3,
                  String mode5, String tailNumber, String callSign, long updatedAt, long identityTimestamp,
                  String trackNumber, double latitude, double longitude) {
        this.id = id;
        this.message = message;
        this.descriptiveLabel = descriptiveLabel;
        this.mode1 = mode1;
        this.mode2 = mode2;
        this.mode3 = mode3;
        this.mode5 = mode5;
        this.tailNumber = tailNumber;
        this.callSign = callSign;
        this.updatedAt = updatedAt;
        this.identityTimestamp = identityTimestamp;
        this.trackNumber = trackNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescriptiveLabel() {
        return descriptiveLabel;
    }

    public void setDescriptiveLabel(String descriptiveLabel) {
        this.descriptiveLabel = descriptiveLabel;
    }

    public String getMode1() {
        return mode1;
    }

    public void setMode1(String mode1) {
        this.mode1 = mode1;
    }

    public String getMode2() {
        return mode2;
    }

    public void setMode2(String mode2) {
        this.mode2 = mode2;
    }

    public String getMode3() {
        return mode3;
    }

    public void setMode3(String mode3) {
        this.mode3 = mode3;
    }

    public String getMode5() {
        return mode5;
    }

    public void setMode5(String mode5) {
        this.mode5 = mode5;
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

    public void setCallSign(String callSign) {
        this.callSign = callSign;
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

    public String toJson() throws JsonProcessingException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return  objectMapper.writeValueAsString(this);
    }
}
