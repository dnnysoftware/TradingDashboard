package com.trading.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

enum MarketSector {
    LOW,
    MEDIUM,
    HIGH
}

enum AssetType {
    LOW,
    MEDIUM,
    HIGH
}

enum StockType {
    LOW,
    MEDIUM,
    HIGH
}

@Entity
public class Asset {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String ticker;

    private MarketSector marketSector;

    private AssetType assetType;

    private StockType stockType;

    public Asset() {

    }

    public Asset(String name, String ticker, MarketSector marketSector, AssetType assetType, StockType stockType) {
        this.name = name;
        this.ticker = ticker;
        this.marketSector = marketSector;
        this.assetType = assetType;
        this.stockType = stockType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public MarketSector getMarketSector() {
        return marketSector;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public StockType getStockType() {
        return stockType;
    }



}