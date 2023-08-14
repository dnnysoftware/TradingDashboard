package com.trading.backend;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Trade {
    @Id
    @GeneratedValue
    private Long id;

    

}
