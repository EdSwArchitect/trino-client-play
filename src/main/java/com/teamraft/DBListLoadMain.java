package com.teamraft;

import com.teamraft.services.EntityService;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class DBListLoadMain {
    public static void main(String... args) throws IOException, URISyntaxException {
        // String bootstrapServers, String topicName
        File dir = new File("src/data");
        final EntityService entityService = new EntityService(args[0], args[1]);

        for (File file : dir.listFiles()) {
            System.out.printf("Processing file %s%n", file.getAbsolutePath());
            List<String> entities = entityService.getDirEntities(file.getAbsolutePath());
            entityService.sendIt(entities);
        }

    }

}
