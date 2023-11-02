package com.teamraft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamraft.csv.CitiBike;
import com.teamraft.services.CitiBikeService;
import com.teamraft.services.KafkaService;

import java.util.List;

public class Main {
    public static void main(String... args) throws JsonProcessingException {
        CitiBikeService citiBikeService = new CitiBikeService();

        List<CitiBike> bikes = citiBikeService.getBikes(args[0]);

        System.out.printf("List size is %d%n", bikes.size());

        try {
            Thread.sleep(1000L * 30);
        } catch (InterruptedException e) {

        }

        System.out.println("Sending data now....");

        KafkaService kafkaService = new KafkaService(args[1], "citibike");

        kafkaService.sendIt(bikes);

    }
}
