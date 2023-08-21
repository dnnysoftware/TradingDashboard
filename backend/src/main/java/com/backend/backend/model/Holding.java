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
    private String accountId;

    public Holding(String accountId) {
        this.accountId = accountId;
    }

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public void buyShares(int newShares, float newPrice) {
        float currentPrice = this.avgPrice * this.shares;
        int totalShares = this.shares + newShares;
        float totalPrice = newPrice + currentPrice;
        float newAvg = totalPrice / totalShares;
        this.avgPrice = newAvg;
        this.shares = totalShares;
    }


    public float sellShares(int removeShares, float currPrice) {
        if (removeShares < this.shares) {
            int newShares = this.shares - removeShares;
            float profitLoss = removeShares * currPrice;
            this.shares = newShares;
            return profitLoss;
        }
        return 0;
    }


}
