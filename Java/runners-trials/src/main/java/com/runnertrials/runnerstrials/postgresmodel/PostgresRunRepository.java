package com.runnertrials.runnerstrials.postgresmodel;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PostgresRunRepository extends ListCrudRepository<Run, Integer>{

    List<Run> findAllByLocation(String location);

}
