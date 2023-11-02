package com.teamraft.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamraft.csv.CitiBike;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.List;
import java.util.Properties;

public class KafkaService {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private KafkaProducer<String, String>producer;
    private String topicName;

    public KafkaService(String bootstrapServers, String topicName) {
        this.topicName = topicName;
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "EdwinTesting");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024);
        producer = new KafkaProducer<>(props);
    }
    public void sendIt(List<CitiBike> bikes) throws JsonProcessingException {
        bikes.forEach(bike -> {
            try {
                String json = objectMapper.writeValueAsString(bike);

                ProducerRecord<String, String>record =
                        new ProducerRecord<>(topicName, bike.getRideId(), json);
                producer.send(record);
            } catch (JsonProcessingException e) {

            }
        });

        producer.flush();

    }
}
