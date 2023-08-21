package com.backend.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.backend.backend.model.Holding;

public interface HoldingRepository extends MongoRepository<Holding, String> {

    @Query("{ 'ticker' : { $regex: '^.*?0.*$', $options: 'i' } }")
    List<Holding> findByTickerRegex(String ticker);

}
