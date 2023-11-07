package com.teamraft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamraft.services.EntityService;

import java.util.List;

public class DBLoadMain {
    public static void main(String... args) throws JsonProcessingException {
        // String bootstrapServers, String topicName

        System.out.format("Using kafka bootstrap-server: '%s' for topic: '%s'\n", args[0], args[1]);

        EntityService entityService = new EntityService(args[0], args[1]);

        System.out.println("Loading the Trino kafka");

        List<String> entities = entityService.getEntities("5000-entities.xml");

        System.out.printf("About to send %d XML documents%n", entities.size());

        entityService.sendIt(entities);

        //        if (entityService.persist(entities)) {
//
//            System.out.println("Stored XML in database as fielded stuff");
//        } else {
//            System.out.println("Error storing XML in teh database");
//        }
    }
}
