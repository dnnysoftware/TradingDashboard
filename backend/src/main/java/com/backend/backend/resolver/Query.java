package com.backend.backend.resolver;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.backend.backend.model.Asset;
import com.backend.backend.model.Trade;
import com.backend.backend.repository.AssetRepository;
import com.backend.backend.repository.TradeRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;




@Controller
public class Query implements GraphQLQueryResolver{


    private AssetRepository assetRepository;
    private TradeRepository tradeRepository;
  
    @Autowired
    public Query(AssetRepository assetRepository, TradeRepository tradeRepository) {
      this.assetRepository = assetRepository;
      this.tradeRepository = tradeRepository;
    }

    @QueryMapping
    public List<Asset> assetsByName(@Argument String name) {
        return assetRepository.findByNameRegex(name);
    }

    @QueryMapping
    public List<Asset> assetsByTicker(@Argument String ticker) {
        return assetRepository.findByNameRegex(ticker);
    }

    @QueryMapping
    public Optional<Asset> assetById(@Argument String id) {
        return assetRepository.findById(id);
    }

    @QueryMapping
    public List<Trade> tradesAll() {
        return tradeRepository.findAll();
    }

    @QueryMapping
    public List<Trade> tradesYear() {
        return tradeRepository.findAll();
    }

    @QueryMapping
    public List<Trade> tradesMonth() {
        return tradeRepository.findAll();
    }

    @QueryMapping
    public List<Trade> tradesWeek() {
        return tradeRepository.findAll();
    }

    @QueryMapping
    public List<Trade> tradesDay() {
        return tradeRepository.findAll();
    }

    @QueryMapping
    public List<Trade> tradesByActive() {
        return tradeRepository.findAll();
    }

    @QueryMapping
    public List<Trade> tradesByActiveCalculate() {
        return tradeRepository.findAll();
    }

    



}
