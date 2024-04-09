package com.project.foreignexchange.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.foreignexchange.domain.ConversionResponse;

@Repository
public interface ConversionRepository extends CrudRepository<ConversionResponse, Long> {

}

