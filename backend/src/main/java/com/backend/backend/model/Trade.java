package com.backend.backend.model;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;




@Document(collection = "trade")
public class Trade {

    @Id
    private String id;
    private float price;
    private int timestamp;
    private int amountShares;
    private String tradeType;
    private String orderType;
    private String assetID;
    private Asset asset;
    private boolean active;


    public Trade(String assetID) {
        this.assetID = assetID;
    }

    public Trade(float price, int timestamp, int amountShares, String tradeType, String orderType, Asset asset, boolean active) {
        this.price = price;
        this.timestamp = timestamp;
        this.amountShares = amountShares;
        this.tradeType = tradeType;
        this.orderType = orderType;
        this.asset = asset;
        this.active = active;
    }

    public String getId() {
        return id;
    }
    
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmountShares() {
        return amountShares;
    }

    public void setAmountShares(int amountShares) {
        this.amountShares = amountShares;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Asset getAsset() {
        return asset;
    }

    public boolean isActive() {
        return active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public boolean deactivate() {
        if (this.active) {
            this.active = false;
            return true;
        }
        return false;
    }

}
