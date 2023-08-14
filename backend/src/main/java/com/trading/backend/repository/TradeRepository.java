package com.trading.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trading.backend.model.Trade;

public interface TradeRepository extends MongoRepository<Trade, String> {
    
}
