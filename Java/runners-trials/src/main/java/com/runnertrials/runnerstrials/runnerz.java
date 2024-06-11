package com.runnertrials.runnerstrials;

import com.runnertrials.runnerstrials.model.Location;
import com.runnertrials.runnerstrials.model.RunRecord;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class runnerz {

    private static final Logger logger = LoggerFactory.getLogger(runnerz.class);

    public static void main(String[] args) {

        SpringApplication.run(runnerz.class, args);
        logger.info("App Imeanza Fiti");

    }



}
