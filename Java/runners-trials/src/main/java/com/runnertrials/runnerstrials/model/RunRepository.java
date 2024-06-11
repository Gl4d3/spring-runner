package com.runnertrials.runnerstrials.model;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SequencedCollection;

@Repository
public class RunRepository {

    // This is our fake database
    public final List<RunRecord> rundb = new ArrayList<>();

//    GET

//    Command to get all records
    public List<RunRecord> findAll(){
        return rundb;
    }

    public Optional<RunRecord> findById(int id){
        return rundb.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

//    POST
    public void create(RunRecord run){
        rundb.add(run);
    }

//    PUT
    public void update(RunRecord run, Integer id){
        Optional<RunRecord> existingRun = findById(id);
        existingRun.ifPresentOrElse(
                value -> rundb.set(rundb.indexOf(value), run),
                () -> {throw new RuntimeException();}
        );
    }

//    DELETE
    public void delete(int id){
        Optional<RunRecord> existingRun = findById(id);
        existingRun.ifPresent(rundb::remove);
    }


    @PostConstruct
//  Adding initial data to the empty db
    public void init() {
        rundb.add(new RunRecord("John", 10, 60, 6, 1, Location.OUTDOOR));
        rundb.add(new RunRecord("Jane", 5, 30, 6, 2, Location.INDOOR));
        rundb.add(new RunRecord("Doe", 15, 90, 6, 3, Location.BOTH));
    }

}
