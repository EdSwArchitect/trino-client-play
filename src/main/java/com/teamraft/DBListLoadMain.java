package com.teamraft;

import com.teamraft.services.EntityLocationService;
import com.teamraft.services.EntityService;

import java.io.File;
import java.util.List;

public class DBListLoadMain {
    public static void main(String... args) {
        // String bootstrapServers, String topicName
        File dir = new File(args[2]);
        final EntityService entityService = new EntityService(args[0], args[1]);
        final EntityLocationService entityLocationService = new EntityLocationService(args[0], "entity_location");

        for (File file : dir.listFiles()) {
            System.out.printf("Processing file %s%n", file.getAbsolutePath());
            List<String> entities = entityService.getDirEntities(file.getAbsolutePath());
            entityService.sendIt(entities);
            entityLocationService.sendIt(entities);
        }

    }

}
