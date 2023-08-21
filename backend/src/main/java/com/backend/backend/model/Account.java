package com.backend.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {
    @Id
    private String id;
    private String name;
    private float balance;
    private float cash;
    private float stock;


    public Account() { 
    }

    
    public Account(String name, float balance, float cash, float stock) {
        this.name = name;
        this.balance = balance;
        this.cash = cash;
        this.stock = stock;
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


    public float getBalance() {
        return balance;
    }


    public void setBalance(float balance) {
        this.balance = balance;
    }


    public float getCash() {
        return cash;
    }


    public void setCash(float cash) {
        this.cash = cash;
    }


    public float getStock() {
        return stock;
    }


    public void setStock(float stock) {
        this.stock = stock;
    }

    public void removeAmount(float amount) {
        if (this.cash - amount > 0) {
            this.balance -= amount;
            this.cash -= amount;
        }  else {
            float tempCash = this.cash;
            this.cash = 0;
            this.balance -= tempCash;
        }
    }

    public void addAmount(float amount) {
        this.balance += amount;
        this.cash += amount;
    }

    public void buyTrade(float amount) {
        if (amount < this.cash) {
            this.cash -= amount;
            this.stock += amount;
            this.balance = this.cash + this.stock;
        }
    }

    public void sellTrade(float amount) {
        if (amount < this.stock) {
            this.cash += amount;
            this.stock -= amount;
            this.balance = this.cash + this.stock;
        }
    }

    
}
