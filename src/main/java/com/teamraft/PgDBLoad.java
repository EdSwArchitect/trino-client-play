package com.teamraft;

import com.teamraft.model.Person;
import com.teamraft.services.EntityService;
import com.teamraft.services.KafkaService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PgDBLoad {
    public static void main(String... args) throws ClassNotFoundException {
        // String bootstrapServers, String topicName
        File dir = new File(args[0]);

        // String bootstrapServers, String topicName
        final KafkaService kafkaService = new KafkaService("my-cluster-kafka-bootstrap:9092",
                "update_test");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person(1, System.currentTimeMillis(), "Edwin", "Brown", 40));
        personList.add(new Person(2, System.currentTimeMillis(), "Sabrina", "Brown", 36));
        personList.add(new Person(3, System.currentTimeMillis(), "Jayden", "Brown", 18));
        personList.add(new Person(4, System.currentTimeMillis(), "Sierra", "Brown", 20));
        personList.add(new Person(5, System.currentTimeMillis(), "Janet", "Jackson", 50));
        personList.add(new Person(6, System.currentTimeMillis(), "Beyonce", "Knoweles-Carter", 40));
        personList.add(new Person(7, System.currentTimeMillis(), "A.J.", "Johnson", 38));

        kafkaService.sendPerson(personList);

        final EntityService entityService = new EntityService();

        for (File file : dir.listFiles()) {
            System.out.printf("Processing file %s%n", file.getAbsolutePath());
            List<String> entities = entityService.getDirEntities(file.getAbsolutePath());
            System.out.printf("\tpersisting %d items.%n", entities.size());
            entityService.persist(entities);
        }

        personList.clear();
        personList.add(new Person(1, System.currentTimeMillis(), "Edwin", "Brown", 60));
        personList.add(new Person(2, System.currentTimeMillis(), "Sabrina", "Brown", 60));
        personList.add(new Person(3, System.currentTimeMillis(), "Jayden", "Brown", 60));
        personList.add(new Person(4, System.currentTimeMillis(), "Sierra", "Cary-Brown", 20));
        personList.add(new Person(5, System.currentTimeMillis(), "Janet", "Jackson", 60));
        personList.add(new Person(6, System.currentTimeMillis(), "Beyonce", "Knoweles-Carter", 60));
        personList.add(new Person(7, System.currentTimeMillis(), "A.J.", "Johnson", 60));
        kafkaService.sendPerson(personList);

    }

}
