package com.backend.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.backend.model.Trade;

public interface TradeRepository extends MongoRepository<Trade, String> {
    


}
