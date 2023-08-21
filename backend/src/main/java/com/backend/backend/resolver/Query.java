package com.backend.backend.resolver;

import java.util.List;
import java.util.Optional;
import java.time.Instant;
import com.backend.backend.model.TimeRange;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.backend.backend.model.Asset;
import com.backend.backend.model.Holding;
import com.backend.backend.model.Trade;
import com.backend.backend.model.Account;
import com.backend.backend.repository.AccountRepository;
import com.backend.backend.repository.AssetRepository;
import com.backend.backend.repository.HoldingRepository;
import com.backend.backend.repository.TradeRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;


@CrossOrigin
@Controller
public class Query implements GraphQLQueryResolver{


    private AssetRepository assetRepository;
    private TradeRepository tradeRepository;
    private AccountRepository accountRepository;
    private HoldingRepository holdingRepository;
    private int currEpochTime;
  
    public Query(AssetRepository assetRepository, TradeRepository tradeRepository, AccountRepository accountRepository, HoldingRepository holdingRepository) {
        this.accountRepository = accountRepository;
        this.assetRepository = assetRepository;
        this.tradeRepository = tradeRepository;
        this.holdingRepository = holdingRepository;
        this.currEpochTime = (int) Instant.now().getEpochSecond();
    }

    private void updateEpoch() {
        this.currEpochTime = (int) Instant.now().getEpochSecond();
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public List<Asset> assetsByName(@Argument String name) {
        return assetRepository.findByNameRegex(name);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public List<Asset> assetsByTicker(@Argument String ticker) {
        return assetRepository.findByNameRegex(ticker);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public Optional<Asset> assetById(@Argument String id) {
        return assetRepository.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public List<Trade> tradesAll() {
        return tradeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public List<Trade> tradesYear() {
        updateEpoch();
        int yearAgo = this.currEpochTime - TimeRange.YEAR.getSeconds();
        return tradeRepository.findByTimestampGreaterThan(yearAgo);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public List<Trade> tradesMonth() {
        updateEpoch();
        int monthAgo = this.currEpochTime - TimeRange.MONTH.getSeconds();
        return tradeRepository.findByTimestampGreaterThan(monthAgo);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public List<Trade> tradesWeek() {
        updateEpoch();
        int weekAgo = this.currEpochTime - TimeRange.WEEK.getSeconds();
        return tradeRepository.findByTimestampGreaterThan(weekAgo);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public List<Trade> tradesDay() {
        updateEpoch();
        int dayAgo = this.currEpochTime - TimeRange.DAY.getSeconds();
        return tradeRepository.findByTimestampGreaterThan(dayAgo);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public List<Trade> tradesByActive() {
        return tradeRepository.findByActive(true);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public Float tradesByActiveCalculate() {
        return tradeRepository.calculateSummedValueForActiveTrades();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public Account accountByName(@Argument String name) {
        return accountRepository.findAccountByName(name);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public Optional<Account> accountById(@Argument String id) {
        return accountRepository.findById(id);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public Optional<Holding> holdingById(@Argument String id) {
        return holdingRepository.findById(id);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @QueryMapping
    public List<Holding> holdingByTicker(@Argument String ticker) {
        return holdingRepository.findByTickerRegex(ticker);
    }


}
