package com.teamraft;

import com.teamraft.services.EntityService;

import java.io.File;
import java.util.List;

public class PgDBLoad {
    public static void main(String... args) throws ClassNotFoundException {
        // String bootstrapServers, String topicName
        File dir = new File(args[0]);

        final EntityService entityService = new EntityService();

        for (File file : dir.listFiles()) {
            System.out.printf("Processing file %s%n", file.getAbsolutePath());
            List<String> entities = entityService.getDirEntities(file.getAbsolutePath());
            System.out.printf("\tpersisting %d items.%n", entities.size());
            entityService.persist(entities);

        }

    }

}
