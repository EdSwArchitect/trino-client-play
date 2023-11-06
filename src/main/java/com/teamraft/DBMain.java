package com.teamraft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teamraft.model.CitiBikeModel;
import com.teamraft.services.CitiBikeService;

import java.sql.SQLException;
import java.util.List;

public class DBMain {
    public static void main(String... args) throws JsonProcessingException {
        CitiBikeService citiBikeService = new CitiBikeService();

        System.out.println("Querying the Trino database");

        try {
            List<CitiBikeModel> bikes = citiBikeService.search();

            System.out.println("The bikes:");
            bikes.forEach(bike -> System.out.format("%s\n-----\n", bike));
            
        } catch (SQLException | ClassNotFoundException e ) {

            System.err.format("Exception caught from search: %s\n", e.getMessage());
        }

    }
}
