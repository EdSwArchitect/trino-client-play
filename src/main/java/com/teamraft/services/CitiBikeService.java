package com.teamraft.services;

import com.teamraft.csv.CitiBike;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CitiBikeService {
    public List<CitiBike> getBikes(String fileName) {

        try (Reader inputReader = new InputStreamReader(Objects.requireNonNull( CitiBikeService.class.getClassLoader().getResourceAsStream(fileName)))) {

	    System.out.println("******* GOT RESOURCE FROM JAR");

            BeanListProcessor<CitiBike> rowProcessor = new BeanListProcessor<>(CitiBike.class);
            CsvParserSettings settings = new CsvParserSettings();
            settings.setHeaderExtractionEnabled(true);
            settings.setProcessor(rowProcessor);
            CsvParser parser = new CsvParser(settings);
            parser.parse(inputReader);

            return rowProcessor.getBeans();
        } catch (IOException e) {
            e.printStackTrace();
	    System.out.println("Returning an empty array");
            return new ArrayList<>();
            // handle exception
        }
    }

}
