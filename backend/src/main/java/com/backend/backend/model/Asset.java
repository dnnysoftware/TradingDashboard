package com.backend.backend.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "asset")
public class Asset {
    @Id
    private String id;
    private String name;
    private String ticker;
    private String marketSector;
    private String assetType;
    private String stockType;


    public Asset() {
    }


    public Asset(String name, String ticker, String marketSector, String assetType, String stockType) {
        this.name = name;
        this.ticker = ticker;
        this.marketSector = marketSector;
        this.assetType = assetType;
        this.stockType = stockType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getMarketSector() {
        return marketSector;
    }

    public void setMarketSector(String markerSector) {
        this.marketSector = markerSector;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }


}