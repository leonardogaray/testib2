package com.todotresde.interbanking.stockoption.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * The type Stock option simulation.
 */
@Data
public class StockOptionSimulation {
    private Date date;
    private String username;
    private List<com.todotresde.interbanking.stockoption.model.StockOption> stockOptions;

    /**
     * Instantiates a new Stock option simulation.
     *
     * @param username       the username
     * @param stockOptions the stock options
     */
    public StockOptionSimulation(String username, List<com.todotresde.interbanking.stockoption.model.StockOption> stockOptions){
        this.setDate(new Date());
        this.setUsername(username);
        this.setStockOptions(stockOptions);
    }
}
