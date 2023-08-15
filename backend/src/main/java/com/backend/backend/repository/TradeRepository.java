package com.backend.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.backend.backend.model.Trade;

public interface TradeRepository extends MongoRepository<Trade, String> {
    
    List<Trade> findByTimestampGreaterThan(int timestamp);

    List<Trade> findByActive(boolean active);

    @Query("{'active': true}")
    List<Trade> findActiveTrades();

    @Query(value = "{'active': true}", fields = "{ '_id': 0, 'price': 1, 'amountShares': 1 }")
    List<Trade> findActiveTradesWithFields();

}
