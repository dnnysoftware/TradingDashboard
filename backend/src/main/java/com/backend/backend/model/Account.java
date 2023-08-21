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


    public Account() { 
    }

    
    public Account(String name, float balance, float cash) {
        this.name = name;
        this.balance = balance;
        this.cash = cash;
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

    
}
