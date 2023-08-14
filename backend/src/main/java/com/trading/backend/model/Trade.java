package com.trading.backend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.GeneratedValue;


enum TradeType {
    LOW,
    MEDIUM,
    HIGH
}

enum OrderType {
    LOW,
    MEDIUM,
    HIGH
}



@Entity
public class Trade {
    @Id
    @GeneratedValue
    private Long id;

    private float price;

    private int amountShares;

    private TradeType tradeType;

    private OrderType orderType;

    @OneToMany(fetch = FetchType.LAZY)
    private Asset asset;

    private boolean active;

    public Trade(Long id, float price, int amountShares, TradeType tradeType, 
    OrderType orderType, Asset asset, boolean active) {
        this.id = id;
        this.price = price;
        this.amountShares = amountShares;
        this.tradeType = tradeType;
        this.orderType = orderType;
        this.asset = asset;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public int getAmountShares() {
        return amountShares;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public Asset getAsset() {
        return asset;
    }

    public boolean isActive() {
        return active;
    }

    

}
