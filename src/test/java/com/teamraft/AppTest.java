package com.teamraft;


import com.teamraft.model.Entity;
import com.teamraft.services.XmlHelper;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;

/**
 * Unit test for simple App.
 */
class AppTest {
    @Test
    void testApp() throws XPathExpressionException, IOException, ParseException, SAXException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <Entity xmlns=\"https://www.vdl.afrl.af.mil/programs/oam\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"https://www.vdl.afrl.af.mil/programs/oam\"> <SecurityInformation> <Classification>U</Classification> <OwnerProducer> <GovernmentIdentifier>USA</GovernmentIdentifier> </OwnerProducer> </SecurityInformation> <MessageHeader> <SystemID> <UUID>4405E2F580FA4BEB893AA9230C62ABE7</UUID> <DescriptiveLabel>CTC</DescriptiveLabel> </SystemID> <Timestamp>2023-11-03T00:01:00.000000Z</Timestamp> <SchemaVersion>002.1</SchemaVersion> <Mode>LIVE</Mode> <ServiceID> <UUID>AC840245C1DB4B379B525D8229118D43</UUID> <DescriptiveLabel>CTC</DescriptiveLabel> </ServiceID> </MessageHeader> <ObjectState>NEW</ObjectState> <MessageData> <EntityID> <UUID>AAAAAAAAAAAAAAAAAAAAAAAAAAAAA000</UUID> <DescriptiveLabel>28767</DescriptiveLabel> </EntityID> <CreationTimestamp> <DateTime>2023-11-03T00:01:00.000000Z</DateTime> </CreationTimestamp> <Source> <SystemID> <UUID>4405E2F580FA4BEB893AA9230C62ABE7</UUID> <DescriptiveLabel>CTC</DescriptiveLabel> </SystemID> <SourceSpecificData> <TrackQuality>10</TrackQuality> </SourceSpecificData> <SourceType>FUSION_SYSTEM</SourceType> </Source> <EntityStatus>POTENTIAL</EntityStatus> <Identity> <Standard> <StandardIdentity>ASSUMED_FRIEND</StandardIdentity> <Confidence>100.0</Confidence> <ExerciseIdentityData> <ExerciseIdentity>ASSUMED_FRIEND</ExerciseIdentity> </ExerciseIdentityData> </Standard> <Environment> <Environment>AIR</Environment> <Confidence>100.0</Confidence> </Environment> <Platform> <PlatformType>0</PlatformType> <PlatformTypeCategory>AIR</PlatformTypeCategory> <Confidence>100.0</Confidence> </Platform> <Specific> <SpecificType>0</SpecificType> <SpecificTypeCategory>AIR</SpecificTypeCategory> <Confidence>100.0</Confidence> </Specific> <SpecificVehicle> <IFF> <Mode1> <Code>No Statement</Code> <Enabled>true</Enabled> </Mode1> <Mode2> <Code>No Statement</Code> <Enabled>true</Enabled> </Mode2> <Mode3A> <Code>No Statement</Code> <Enabled>true</Enabled> </Mode3A> <Mode4> <Mode4Indicator>NOT_INTERROGATED</Mode4Indicator> </Mode4> <Mode5> <NationalOrigin>0</NationalOrigin> <PIN>0</PIN> <Mode5Indicator>NOT_INTERROGATED</Mode5Indicator> </Mode5> <ModeC> <Code>235</Code> <Enabled>true</Enabled> </ModeC> </IFF> <CallSign> <Key>AAA000</Key> <SystemName>TBD</SystemName> </CallSign> <Confidence>20.0</Confidence> </SpecificVehicle> <SelfReportedIdentity>false</SelfReportedIdentity> <DifferenceIndicator>false</DifferenceIndicator> <IdentityTimestamp>2023-11-03T00:01:00.000000Z</IdentityTimestamp> </Identity> <Kinematics> <Position> <FixedPositionType> <FixedPoint> <Latitude>0.77771</Latitude> <Longitude>-1.92111</Longitude> <Altitude>11940.35188</Altitude> <Timestamp>2023-11-03T00:01:00.000000Z</Timestamp> </FixedPoint> </FixedPositionType> </Position> <Velocity> <NorthSpeed>106.4826</NorthSpeed> <EastSpeed>-181.8195</EastSpeed> </Velocity> </Kinematics> <Strength> <StrengthValue> <Minimum>9</Minimum> <Maximum>9</Maximum> </StrengthValue> </Strength> <ActivityBy> <Activity>0</Activity> <ActivityCategory>AIR</ActivityCategory> </ActivityBy> </MessageData> </Entity>";
        Entity entity = new Entity(xml, new XmlHelper());

        String json = entity.toJson();

        System.out.println(json);
    }

    @Test
    void parseTest() {
        String time = "2023-11-03T00:01:00.000000Z";
//        Instant.parse("yyyy-MM-dd'T'HH:mm:ssssssZ");
        Instant.parse(time);
    }

    @Test
    void parseLocations() throws XPathExpressionException, IOException, ParseException, SAXException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <Entity xmlns=\"https://www.vdl.afrl.af.mil/programs/oam\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"https://www.vdl.afrl.af.mil/programs/oam\"> <SecurityInformation> <Classification>U</Classification> <OwnerProducer> <GovernmentIdentifier>USA</GovernmentIdentifier> </OwnerProducer> </SecurityInformation> <MessageHeader> <SystemID> <UUID>4405E2F580FA4BEB893AA9230C62ABE7</UUID> <DescriptiveLabel>CTC</DescriptiveLabel> </SystemID> <Timestamp>2023-11-03T00:01:00.000000Z</Timestamp> <SchemaVersion>002.1</SchemaVersion> <Mode>LIVE</Mode> <ServiceID> <UUID>AC840245C1DB4B379B525D8229118D43</UUID> <DescriptiveLabel>CTC</DescriptiveLabel> </ServiceID> </MessageHeader> <ObjectState>NEW</ObjectState> <MessageData> <EntityID> <UUID>AAAAAAAAAAAAAAAAAAAAAAAAAAAAA000</UUID> <DescriptiveLabel>28767</DescriptiveLabel> </EntityID> <CreationTimestamp> <DateTime>2023-11-03T00:01:00.000000Z</DateTime> </CreationTimestamp> <Source> <SystemID> <UUID>4405E2F580FA4BEB893AA9230C62ABE7</UUID> <DescriptiveLabel>CTC</DescriptiveLabel> </SystemID> <SourceSpecificData> <TrackQuality>10</TrackQuality> </SourceSpecificData> <SourceType>FUSION_SYSTEM</SourceType> </Source> <EntityStatus>POTENTIAL</EntityStatus> <Identity> <Standard> <StandardIdentity>ASSUMED_FRIEND</StandardIdentity> <Confidence>100.0</Confidence> <ExerciseIdentityData> <ExerciseIdentity>ASSUMED_FRIEND</ExerciseIdentity> </ExerciseIdentityData> </Standard> <Environment> <Environment>AIR</Environment> <Confidence>100.0</Confidence> </Environment> <Platform> <PlatformType>0</PlatformType> <PlatformTypeCategory>AIR</PlatformTypeCategory> <Confidence>100.0</Confidence> </Platform> <Specific> <SpecificType>0</SpecificType> <SpecificTypeCategory>AIR</SpecificTypeCategory> <Confidence>100.0</Confidence> </Specific> <SpecificVehicle> <IFF> <Mode1> <Code>No Statement</Code> <Enabled>true</Enabled> </Mode1> <Mode2> <Code>No Statement</Code> <Enabled>true</Enabled> </Mode2> <Mode3A> <Code>No Statement</Code> <Enabled>true</Enabled> </Mode3A> <Mode4> <Mode4Indicator>NOT_INTERROGATED</Mode4Indicator> </Mode4> <Mode5> <NationalOrigin>0</NationalOrigin> <PIN>0</PIN> <Mode5Indicator>NOT_INTERROGATED</Mode5Indicator> </Mode5> <ModeC> <Code>235</Code> <Enabled>true</Enabled> </ModeC> </IFF> <CallSign> <Key>AAA000</Key> <SystemName>TBD</SystemName> </CallSign> <Confidence>20.0</Confidence> </SpecificVehicle> <SelfReportedIdentity>false</SelfReportedIdentity> <DifferenceIndicator>false</DifferenceIndicator> <IdentityTimestamp>2023-11-03T00:01:00.000000Z</IdentityTimestamp> </Identity> <Kinematics> <Position> <FixedPositionType> <FixedPoint> <Latitude>0.77771</Latitude> <Longitude>-1.92111</Longitude> <Altitude>11940.35188</Altitude> <Timestamp>2023-11-03T00:01:00.000000Z</Timestamp> </FixedPoint> </FixedPositionType> </Position> <Velocity> <NorthSpeed>106.4826</NorthSpeed> <EastSpeed>-181.8195</EastSpeed> </Velocity> </Kinematics> <Strength> <StrengthValue> <Minimum>9</Minimum> <Maximum>9</Maximum> </StrengthValue> </Strength> <ActivityBy> <Activity>0</Activity> <ActivityCategory>AIR</ActivityCategory> </ActivityBy> </MessageData> </Entity>";
        Entity entity = new Entity(xml, new XmlHelper());

        System.out.printf("Latitude: %f. Longitude: %f%n", entity.getLatitude(), entity.getLongitude());
        System.out.printf("ID: %s%n", entity.getId());
    }

}
