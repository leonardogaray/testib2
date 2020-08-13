package com.todotresde.interbanking.stockoption.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * The type Stock option strategy.
 */
@Data
public class StockOptionStrategy implements Comparable<StockOptionStrategy>{
    private Integer count;
    private String brand;
    private Float previousBuyPrice;
    private Float buyPrice;
    private Float previousSellPrice;
    private Float sellPrice;
    private LocalDate previousBuyDate;
    private LocalDate buyDate;
    private LocalDate previousSellDate;
    private LocalDate sellDate;

    /**
     * Instantiates a new Stock option strategy.
     *
     * @param count       the count
     * @param stockOption the stock option
     */
    public StockOptionStrategy(Integer count, com.todotresde.interbanking.stockoption.model.StockOption stockOption){
        this.count = count;
        this.brand = stockOption.getBrand();
        this.buyPrice = stockOption.getPrice();
        this.buyDate = stockOption.getDate();
    }
    @Override
    public int compareTo(com.todotresde.interbanking.stockoption.model.StockOptionStrategy stockOptionStrategy) {
        return getBuyDate().compareTo(stockOptionStrategy.getBuyDate());
    }

}
