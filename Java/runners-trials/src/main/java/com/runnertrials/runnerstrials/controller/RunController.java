package com.runnertrials.runnerstrials.controller;

import com.runnertrials.runnerstrials.exception.RunNotFoundException;
import com.runnertrials.runnerstrials.postgresmodel.PostgresRunRepository;
import com.runnertrials.runnerstrials.postgresmodel.Run;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs/")
public class RunController {

//    USING THE ARRAY REPO INSTANCE

/*

//  Tunataka kutumia RunRepo yenye tushatengeneza, tunaireference tu kwa controller.
//  Instead of making it again from scratch within the rest controller.
    private final RunRepository repoInstance;
    public RunController(RunRepository repoInstance) {
        this.repoInstance = repoInstance;
    }

//REQUESTS
    //GET
    @GetMapping("/")
    List<RunRecord> findAll(int id){
        return repoInstance.findAll();
    }

    @GetMapping("/{id}")
    RunRecord FindById(@PathVariable int id){
        Optional<RunRecord> run = repoInstance.findById(id);
        if(run.isPresent()){
            return run.get();
        } else {
            throw new RunNotFoundException(); //Custom error Message for when the run is not found
        }
    }

    //POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    void create(@RequestBody RunRecord run){
        repoInstance.create(run);
    }

    //PUT
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    void update(@RequestBody RunRecord run, @PathVariable int id){
        Optional<RunRecord> runs = repoInstance.findById(id);
        runs.ifPresentOrElse(
                value -> repoInstance.update(run, id),
                () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        );
    }

    //DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        Optional<RunRecord> runs = repoInstance.findById(id);
        runs.ifPresentOrElse(
                value -> repoInstance.delete(id),
                () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        );
    }

*/

//    USING THE H2 REPO INSTANCE

/*

//  Dependency Injection
    private final h2RunRepository repoInstance;

    public RunController(h2RunRepository repoInstance) {
        this.repoInstance = repoInstance;
    }

//  REQUESTS

//    GET
    @GetMapping("/")
    List<h2RunRecord> findAll(){
        return repoInstance.findAll();
    }

    @GetMapping("/{id}")
    h2RunRecord FindById(@PathVariable int id){
        Optional<h2RunRecord> run = repoInstance.findById(id);
        if(run.isPresent()){
            return run.get();
        } else {
            throw new RunNotFoundException(); //Custom error Message for when the run is not found
        }
    }

    @GetMapping("/location/{location}")
    h2RunRecord findByLocation(@PathVariable int id){
        Optional<h2RunRecord> run = repoInstance.findByLocation(id);
        if(run.isPresent()){
            return run.get();
        } else {
            throw new RunNotFoundException(); //Custom error Message for when the run is not found
        }
    }

    //POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    void create(@RequestBody h2RunRecord run){
        repoInstance.create(run);
    }

    //PUT
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    void update(@RequestBody h2RunRecord run, @PathVariable int id){
        Optional<h2RunRecord> runs = repoInstance.findById(id);
        runs.ifPresentOrElse(
                value -> repoInstance.update(run, id),
                () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        );
    }

    //DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        Optional<h2RunRecord> runs = repoInstance.findById(id);
        runs.ifPresentOrElse(
                value -> repoInstance.delete(id),
                () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        );
    }

*/

//    USING THE JDBC-POSTGRES REPO INSTANCE

//    Dependency Injection
    private final PostgresRunRepository repoInstance;

    public RunController(PostgresRunRepository repoInstance) {
        this.repoInstance = repoInstance;
    }


//    REQUESTS

//  GET

    @GetMapping("")
    List<Run> findAll(){
        return repoInstance.findAll();
    }

    @GetMapping("/{id}")
    Object findById(@PathVariable int id){
        Optional <Run> run = repoInstance.findById(id);
        if(run.isPresent()){
            return run.get();
        } else {
            throw new RunNotFoundException(); //Custom error Message for when the run is not found
        }
    }

    @GetMapping("/location/{location}")
    Object findByLocation(@PathVariable String location){
        List<Run> run = repoInstance.findAllByLocation(location);
        if(run.isEmpty()){
            return run;
        } else {
            throw new RunNotFoundException(); //Custom error Message for when the run is not found
        }
    }

//    POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    void create(@RequestBody Run run){
        repoInstance.save(run);
    }

//    PUT
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    void update(@RequestBody Run run, @PathVariable int id){
        Optional<Run> runs = repoInstance.findById(id);
        runs.ifPresentOrElse(
                value -> repoInstance.save(run), //This is the update method in the PostgresRunRepository
                () -> {throw new RunNotFoundException();} //Custom error Message for when the run is not found
        );
    }

//    DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable int id){
        Optional<Run> runs = repoInstance.findById(id);
        runs.ifPresentOrElse(
                repoInstance::delete,
                () -> {throw new RunNotFoundException();} //Custom error Message for when the run is not found
        );
    }

}
