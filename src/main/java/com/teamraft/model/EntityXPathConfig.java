package com.teamraft.model;

public class EntityXPathConfig {
    public static final String objectState = "/Entity/ObjectState";
    public static final String id = "/Entity/MessageData/EntityID/UUID";
    public static final String descriptiveLabelPath = "/Entity/MessageData/EntityID/DescriptiveLabel";
    public static final String mode1 = "/Entity/MessageData/Identity/SpecificVehicle/IFF/Mode1/Code/text()";
    public static final String mode2 = "/Entity/MessageData/Identity/SpecificVehicle/IFF/Mode2/Code/text()";
    public static final String mode3 = "/Entity/MessageData/Identity/SpecificVehicle/IFF/Mode3A/Code/text()";
    public static final String mode5 = "/Entity/MessageData/Identity/SpecificVehicle/IFF/Mode5/Mode5Indicator/text()";
    public static final String tailNumber = "/Entity/MessageData/Identity/SpecificVehicle/IFF/ModeS/AircraftIdentifier/text()";
    public static final String callSign = "/Entity/MessageData/Identity/SpecificVehicle/CallSign/Key/text()";
    public static final String identityTimestamp = "/Entity/MessageData/Identity/IdentityTimestamp/text()";
    public static final String trackNumber = "/Entity/MessageData/Identity/SpecificVehicle/DataLinkIdentifier/TrackIdentifier/TrackNumber/text()";

}
