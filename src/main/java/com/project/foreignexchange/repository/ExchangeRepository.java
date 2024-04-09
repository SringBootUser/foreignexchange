package com.project.foreignexchange.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.foreignexchange.domain.ExchangeResponse;

@Repository
public interface ExchangeRepository extends CrudRepository<ExchangeResponse, Long> {

}

