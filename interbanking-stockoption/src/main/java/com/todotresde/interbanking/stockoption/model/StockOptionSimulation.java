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
    private Long userId;
    private List<com.todotresde.interbanking.stockoption.model.StockOption> stockOptions;

    /**
     * Instantiates a new Stock option simulation.
     *
     * @param userId       the user id
     * @param stockOptions the stock options
     */
    public StockOptionSimulation(Long userId, List<com.todotresde.interbanking.stockoption.model.StockOption> stockOptions){
        this.setDate(new Date());
        this.setUserId(userId);
        this.setStockOptions(stockOptions);
    }
}
