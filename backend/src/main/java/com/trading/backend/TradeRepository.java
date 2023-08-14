package com.trading.backend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeRepository extends MongoRepository<Trade, String> {
    
}
