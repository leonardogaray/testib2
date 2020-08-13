package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.model.StockOption;

import java.util.Collection;

/**
 * The interface Stock option service.
 */
public interface StockOptionService {
    /**
     * Find by brand is in stock option.
     *
     * @param stockOptions the stock options
     * @param brand        the brand
     * @return the stock option
     */
    public StockOption findByBrandIsIn(Collection<StockOption> stockOptions, String brand);

}
