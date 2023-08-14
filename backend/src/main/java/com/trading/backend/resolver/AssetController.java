package com.trading.backend.resolver;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.trading.backend.model.Asset;
import com.trading.backend.repository.AssetRepository;


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
