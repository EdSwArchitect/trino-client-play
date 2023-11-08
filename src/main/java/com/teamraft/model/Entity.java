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
import java.time.Instant;
import java.util.UUID;

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
    Instant updatedAt;
    String trackNumber;

    public Entity() {
    }

    Instant identityTimestamp;

    public Entity(String id, String message, String descriptiveLabel) {
        this.id = id;
        this.message = message;
        this.descriptiveLabel = descriptiveLabel;
        this.updatedAt = Instant.now();
        this.identityTimestamp = Instant.now();
    }

    public Entity(String xml, XmlHelper xmlHelper) throws IOException, SAXException, XPathExpressionException {
        this.id = UUID.randomUUID().toString();
        this.message = xml;
        Document document = xmlHelper.convertToDocument(xml);
        this.descriptiveLabel = xmlHelper.getField(EntityXPathConfig.descriptiveLabelPath, document);
        this.mode1 = xmlHelper.getValues(EntityXPathConfig.mode1, document);
        this.mode2 = xmlHelper.getValues(EntityXPathConfig.mode2, document);
        this.mode3 = xmlHelper.getValues(EntityXPathConfig.mode3, document);
        this.mode5 = xmlHelper.getValues(EntityXPathConfig.mode5, document);
        this.tailNumber = xmlHelper.getValues(EntityXPathConfig.tailNumber, document);
        this.callSign = xmlHelper.getValues(EntityXPathConfig.callSign, document);
        this.identityTimestamp = xmlHelper.getInstant(EntityXPathConfig.identityTimestamp, document);
        this.trackNumber = xmlHelper.getValues(EntityXPathConfig.trackNumber, document);
    }


    public Entity(String id, String message, String descriptiveLabel, String mode1, String mode2, String mode3,
                  String mode5, String tailNumber, String callSign, Instant updatedAt, Instant identityTimestamp,
                  String trackNumber) {
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

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Instant getIdentityTimestamp() {
        return identityTimestamp;
    }

    public void setIdentityTimestamp(Instant identityTimestamp) {
        this.identityTimestamp = identityTimestamp;
    }

    public String toJson() throws JsonProcessingException {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        return  objectMapper.writeValueAsString(this);
    }
}
