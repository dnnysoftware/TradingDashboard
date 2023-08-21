package com.backend.backend.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "holding")
public class Holding {
    @Id
    private String id;
    private Account account;
    private String ticker;
    private int shares;
    private float avgPrice;

    public Holding() {
    }
    
    public Holding(Account account, String ticker, int shares, float avgPrice) {
        this.account = account;
        this.ticker = ticker;
        this.shares = shares;
        this.avgPrice = avgPrice;
    }

    public String getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(float avgPrice) {
        this.avgPrice = avgPrice;
    }

}
