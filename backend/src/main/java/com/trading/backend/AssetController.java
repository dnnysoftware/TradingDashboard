package com.trading.backend;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import graphql.schema.DataFetcher;

@Controller
public class AssetController {

    private final AssetRepository assetRepository;

    
    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    @QueryMapping
    Iterable<Asset> assetsByName(String name) {
        return assetRepository.findAll();
    }
    
}
