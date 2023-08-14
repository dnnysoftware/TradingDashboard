package com.backend.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.backend.backend.model.Asset;

public interface AssetRepository extends MongoRepository<Asset, String> {

    @Query("{ 'name' : { $regex: '^.*?0.*$', $options: 'i' } }")
    List<Asset> findByNameRegex(String name);

    @Query("{ 'ticker' : { $regex: '^.*?0.*$', $options: 'i' } }")
    List<Asset> findByTickerRegex(String ticker);

}
