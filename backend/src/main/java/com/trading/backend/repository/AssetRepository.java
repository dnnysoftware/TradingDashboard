package com.trading.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trading.backend.model.Asset;

public interface AssetRepository extends MongoRepository<Asset, String> {
    
}
