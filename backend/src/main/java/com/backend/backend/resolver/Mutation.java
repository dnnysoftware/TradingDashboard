package com.backend.backend.resolver;

import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.backend.backend.model.Account;
import com.backend.backend.model.Asset;
import com.backend.backend.model.Holding;
import com.backend.backend.model.Trade;
import com.backend.backend.repository.AccountRepository;
import com.backend.backend.repository.AssetRepository;
import com.backend.backend.repository.HoldingRepository;
import com.backend.backend.repository.TradeRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;


@CrossOrigin
@Controller
public class Mutation implements GraphQLMutationResolver {
    

    private AssetRepository assetRepository;
    private AccountRepository accountRepository;
    private TradeRepository tradeRepository;
    private HoldingRepository holdingRepository;
    private AssetValidator assetValidator;
  
    public Mutation(AssetRepository assetRepository, TradeRepository tradeRepository, AssetValidator assetValidator, AccountRepository accountRepository, HoldingRepository holdingRepository) {
        this.assetRepository = assetRepository;
        this.tradeRepository = tradeRepository;
        this.assetValidator = assetValidator;
        this.accountRepository = accountRepository;
        this.holdingRepository = holdingRepository;
    }

    // Creation

    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Asset createAsset( @Argument String name, @Argument String ticker, @Argument String marketSector, @Argument String assetType, @Argument String stockType) {
        Asset asset = new Asset(name, ticker, marketSector, assetType, stockType);
        assetValidator.validate(asset, null); 
        return assetRepository.save(asset);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Trade createTrade(@Argument float price, @Argument int amountShares, @Argument String tradeType, @Argument String orderType, @Argument String asset, @Argument boolean active) {
        Trade temp = new Trade(asset);
        Asset obj = getAsset(temp);
        Trade trade = new Trade(price, amountShares, tradeType, orderType, obj, active);
        return tradeRepository.save(trade);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Account createAccount(@Argument String name) {
        float startingVal = Float.intBitsToFloat(0);
        Account account = new Account(name, startingVal, startingVal, startingVal);
        return accountRepository.save(account);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Holding createHolding(@Argument String account, @Argument String ticker, @Argument int shares, @Argument float avgPrice) {
        Holding temp = new Holding(account);
        Account obj = getAccount(temp);
        Holding holding = new Holding(obj, ticker, shares, avgPrice);
        return holdingRepository.save(holding);
    }


    // Updation

    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Account addCash(@Argument String id, @Argument float amount) {

        Optional<Account> accountOptional = accountRepository.findById(id);
    
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.addAmount(amount);
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account not found with ID: " + id);
        }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Account takeOutCash(@Argument String id, @Argument float amount) {

        Optional<Account> accountOptional = accountRepository.findById(id);
    
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.removeAmount(amount);
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account not found with ID: " + id);
        }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Account updateOnBuyTrade(@Argument String id, @Argument float amount) {

        Optional<Account> accountOptional = accountRepository.findById(id);
    
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.buyTrade(amount);
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account not found with ID: " + id);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Account updateOnSellTrade(@Argument String id, @Argument float amount) {

        Optional<Account> accountOptional = accountRepository.findById(id);
    
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.sellTrade(amount);
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account not found with ID: " + id);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Holding buySharesToHolding(@Argument String id, @Argument int shares, @Argument float price) {

        Optional<Holding> holdingOptional = holdingRepository.findById(id);

        if (holdingOptional.isPresent()) {
            Holding holding = holdingOptional.get();
            holding.buyShares(shares, price);
            Account acc = holding.getAccount();
            float amount = shares * price;
            acc.buyTrade(amount);
            accountRepository.save(acc);
            return holdingRepository.save(holding);
        } else {
            throw new IllegalArgumentException("Holding not found with ID: " + id);
        }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @MutationMapping
    public Holding sellSharesToHolding(@Argument String id, @Argument int shares, @Argument float price) {

        Optional<Holding> holdingOptional = holdingRepository.findById(id);

        if (holdingOptional.isPresent()) {
            Holding holding = holdingOptional.get();
            float profitLoss = holding.sellShares(shares, price);
            Account acc = holding.getAccount();
            acc.sellTrade(profitLoss);
            accountRepository.save(acc);
            return holdingRepository.save(holding);
        } else {
            throw new IllegalArgumentException("Holding not found with ID: " + id);
        }
    }



    public Asset getAsset(@Argument Trade trade) {
        return this.assetRepository.findById(trade.getAssetID()).orElseThrow(null);
    }

    public Account getAccount(@Argument Holding account) {
        return this.accountRepository.findById(account.getAccountId()).orElseThrow(null);
    }



}
