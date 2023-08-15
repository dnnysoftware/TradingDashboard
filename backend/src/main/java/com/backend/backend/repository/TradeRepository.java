package com.backend.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.backend.backend.model.Trade;

public interface TradeRepository extends MongoRepository<Trade, String> {
    
    List<Trade> findByTimestampGreaterThan(int timestamp);

    List<Trade> findByActive(boolean active);

    @Query("{'active': true}")
    List<Trade> findActiveTrades();

    @Aggregation(pipeline = {
        "{ $match: { active: true } }",
        "{ $project: { _id: 0, value: { $multiply: ['$price', '$amountShares'] } } }",
        "{ $group: { _id: null, summedValue: { $sum: '$value' } } }"})
    Float calculateSummedValueForActiveTrades();

}
