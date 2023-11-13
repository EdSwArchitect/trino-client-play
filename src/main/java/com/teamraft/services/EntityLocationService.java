package com.teamraft.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamraft.model.Entity;
import com.teamraft.model.EntityLocation;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EntityLocationService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    static final String DB_URL = "jdbc:trino://my-trino:8080/entity_location/comms_broker?user=admin";
    private XmlHelper xmlHelper = new XmlHelper();
    private KafkaProducer<String, String> producer;
    private String topicName;

    public EntityLocationService() {
    }

    public EntityLocationService(String bootstrapServers, String topicName) {
        this.topicName = topicName;
        Properties props = new Properties();

        System.err.printf("Bootstrap servers: '%s'\n", bootstrapServers);

        props.put(ProducerConfig.CLIENT_ID_CONFIG, "EntityTesting");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        producer = new KafkaProducer<>(props);
    }

    public void sendIt(List<String> entities) {
        System.out.printf("The list of records is %d in size%n for entityLocation", entities.size());

        entities.forEach(entity -> {

            try {
                EntityLocation l = new EntityLocation(new Entity(entity, xmlHelper));

                ProducerRecord<String, String> record =
                        new ProducerRecord<>(topicName, l.getId(), l.toJson());
                Future<RecordMetadata> future = producer.send(record);

                future.get().hasOffset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        });

        producer.flush();
    }

}
