package com.teamraft.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamraft.csv.CitiBike;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaService {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private KafkaProducer<String, String>producer;
    private String topicName;

    public KafkaService(String bootstrapServers, String topicName) {
        this.topicName = topicName;
        Properties props = new Properties();

	System.err.printf("Bootstrap servers: '%s'\n", bootstrapServers);

        props.put(ProducerConfig.CLIENT_ID_CONFIG, "EdwinTesting");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        //props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024);
        producer = new KafkaProducer<>(props);
    }
    public void sendIt(List<CitiBike> bikes) {
        bikes.forEach(bike -> {
            try {
                String json = objectMapper.writeValueAsString(bike);

//		System.err.println(json);

                ProducerRecord<String, String>record =
                        new ProducerRecord<>(topicName, bike.getRideId(), json);
                //producer.send(record);
                Future<RecordMetadata>future = producer.send(record);
//		System.out.printf("Future topic? %s%n", future.get().topic());
		
            } catch (JsonProcessingException e) {
		    System.out.printf("The error raised is: %s\n", e.getMessage());
		e.printStackTrace();
            } 
        });

        producer.flush();

    }
}
