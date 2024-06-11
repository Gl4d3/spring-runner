package com.runnertrials.runnerstrials.h2model;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

//@Component //Comment it back to make the program run.
public class h2RunJsonDataLogger implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(h2RunJsonDataLogger.class);

//    Dependency Injection of h2RunRepository and objectMapper
    private final h2RunRepository h2RunRepository;
    private final ObjectMapper objectMapper; //used to read json data and transform it into objects

    public h2RunJsonDataLogger(h2RunRepository h2RunRepository, ObjectMapper objectMapper) {
        this.h2RunRepository = h2RunRepository;
        this.objectMapper = objectMapper;
        this.objectMapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
    }



    @Override
    public void run(String... args) throws Exception {

        if(h2RunRepository.count() == 0){
            try(InputStream inputStream = getClass().getResourceAsStream("/data/runs.json")){
                h2RunRecordList allRuns = objectMapper.readValue(inputStream, h2RunRecordList.class);
                log.info("Loaded {} runs from json file and loaded into an in-memory database", allRuns.runs().size());
                h2RunRepository.createFromJSON(allRuns.runs());
            } catch( IOException e){
                throw new RuntimeException("Failed to read json data", e);
            }
        }

    }
}
