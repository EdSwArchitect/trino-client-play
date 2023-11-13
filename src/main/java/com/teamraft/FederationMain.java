package com.teamraft;

import com.teamraft.model.Federation;
import com.teamraft.services.FederationService;

import java.sql.SQLException;
import java.util.List;

public class FederationMain {
    public static void main(String... args) throws SQLException {
        // String bootstrapServers, String topicName
        final FederationService federationService = new FederationService();

        List<Federation>results = federationService.getFederation();

        results.forEach(result -> System.out.printf("%s%n------%n", result));

        }

}
