package com.udaan.test.repository;

import com.udaan.test.model.CovidResult;
import org.springframework.data.repository.CrudRepository;

public interface CovidRepository extends CrudRepository<CovidResult, Integer> {
}
