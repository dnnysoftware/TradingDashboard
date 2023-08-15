package com.backend.backend.resolver;

import java.util.List;
import java.util.Optional;
import java.time.Instant;

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


    public enum TimeRange {
    YEAR(31536000),
    MONTH(2628288),
    WEEK(604800),
    DAY(86400);

    private final int seconds;

    TimeRange(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}


    private AssetRepository assetRepository;
    private TradeRepository tradeRepository;
    private int currEpochTime;
  
    @Autowired
    public Query(AssetRepository assetRepository, TradeRepository tradeRepository) {
        this.assetRepository = assetRepository;
        this.tradeRepository = tradeRepository;
        this.currEpochTime = (int) Instant.now().getEpochSecond();
    }

    private void updateEpoch() {
        this.currEpochTime = (int) Instant.now().getEpochSecond();
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
        updateEpoch();
        int yearAgo = this.currEpochTime - TimeRange.YEAR.getSeconds();
        return tradeRepository.findByTimestampGreaterThan(yearAgo);
    }

    @QueryMapping
    public List<Trade> tradesMonth() {
        updateEpoch();
        int monthAgo = this.currEpochTime - TimeRange.MONTH.getSeconds();
        return tradeRepository.findByTimestampGreaterThan(monthAgo);
    }

    @QueryMapping
    public List<Trade> tradesWeek() {
        updateEpoch();
        int weekAgo = this.currEpochTime - TimeRange.WEEK.getSeconds();
        return tradeRepository.findByTimestampGreaterThan(weekAgo);
    }

    @QueryMapping
    public List<Trade> tradesDay() {
        updateEpoch();
        int dayAgo = this.currEpochTime - TimeRange.DAY.getSeconds();
        return tradeRepository.findByTimestampGreaterThan(dayAgo);
    }

    @QueryMapping
    public List<Trade> tradesByActive() {
        return tradeRepository.findByActive(true);
    }

    @QueryMapping
    public float tradesByActiveCalculate() {
        List<Trade> activeTrades = tradeRepository.findActiveTradesWithFields();
        float summedValue = 0;
        for (Trade trade : activeTrades) {
            summedValue += trade.getPrice() * trade.getAmountShares();
        }
        return summedValue;
    }

    



}
