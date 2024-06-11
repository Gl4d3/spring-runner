package com.runnertrials.runnerstrials.h2model;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class h2RunRepository {

    private final Logger log = Logger.getLogger(h2RunRepository.class.getName());

//    INITIALIZING JDBC CLIENT (DEPENDENCY INJECTION)
    private final JdbcClient jdbcClient;

    public h2RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

//    REQUEST METHODS

//  GET
    public List<h2RunRecord> findAll(){
        var response = jdbcClient.sql("SELECT * FROM run")
                .query(h2RunRecord.class) //this method returns a list of Run objects.
                .list();
        log.info("All runs fetched from the database" + response.size() + "runs");
        return response;
    };
    public Optional<h2RunRecord> findById(int id){
        var response = jdbcClient.sql("SELECT * FROM run WHERE id = :id")
                .param("id", id)
                .query(h2RunRecord.class)
                .optional();
        log.info("Run fetched from the database" + response);
        return response;
    };
    public Optional<h2RunRecord> findByLocation(int id){
        var response = jdbcClient.sql("SELECT * FROM run WHERE id = :id")
                .param("id", id)
                .query(h2RunRecord.class)
                .optional();
        log.info("Run fetched from the database" + response);
        return response;
    };
    public int count() { return jdbcClient.sql("select * from run").query().listOfRows().size(); }

//  POST
    public void create(h2RunRecord run){
        var updated = jdbcClient.sql("INSERT INTO run (id, title, distance, time, pace, date, location) VALUES (?, ?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.distance(), run.time(), run.pace(), run.date(), run.location().toString()))
                .update();
        log.info("Run created in the database" + run);
        Assert.state(updated == 1, "Expected to insert 1 row" + run.title());
    };

    public void create(List<h2RunRecord> runs){
        try {
            runs.stream().forEach(this::create);
            /*
            for (h2RunRecord run : runs) {
                var updated = jdbcClient.sql("INSERT INTO run (id, title, distance, time, pace, date, location) VALUES (?, ?, ?, ?, ?, ?, ?)")
                        .params(List.of(run.id(), run.title(), run.distance(), run.time(), run.pace(), run.date(), run.location().toString()))
                        .update();
                log.info("Run created in the database" + run);
                Assert.state(updated == 1, "Expected to insert 1 row" + run.title());
            }
            */
        } catch (Exception e){
            log.info("Error creating run in the database: " + e.getMessage());
        }
    }

    public void createFromJSON(List<h2RunRecord> runs){
        try {
            runs.stream().forEach(this::create);
            /*
            for (h2RunRecord run : runs) {
                var updated = jdbcClient.sql("INSERT INTO run (id, title, distance, time, pace, date, location) VALUES (?, ?, ?, ?, ?, ?, ?)")
                        .params(List.of(run.id(), run.title(), run.distance(), run.time(), run.pace(), run.date(), run.location().toString()))
                        .update();
                log.info("Run created in the database" + run);
                Assert.state(updated == 1, "Expected to insert 1 row" + run.title());
            }
            */
        } catch (Exception e){
            log.info("Error creating run in the database: " + e.getMessage());
        }
    }

//  PUT
    public void update(h2RunRecord run, Integer id){
        try {
            var updated = jdbcClient.sql("UPDATE run SET title = ?, distance = ?, time = ?, pace = ?, date = ?, location = ? WHERE id = ?")
                    .params(List.of(run.title(), run.distance(), run.time(), run.pace(), run.date(), run.location(), id))
                    .update();
            log.info("Run updated in the database" + run);
        } catch (Exception e){
            log.info("Run not updated in the database" + run);
        }
    };

//  DELETE
    public void delete(int id){
        var updated = jdbcClient.sql("DELETE FROM run WHERE id = :id")
                .param("id", id)
                .update();
        log.info("Run deleted from the database" + id);
    };

}
