package com.teamraft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamraft.services.EntityService;

import java.util.List;

public class DBLoadMain {
    public static void main(String... args) throws JsonProcessingException {
        EntityService entityService = new EntityService();

        System.out.println("Loading the Trino database");

        List<String> entities = entityService.getEntities("5000-entities.xml");

        if (entityService.persist(entities)) {

            System.out.println("Stored XML in database as fielded stuff");
        } else {
            System.out.println("Error storing XML in teh database");
        }
    }
}
