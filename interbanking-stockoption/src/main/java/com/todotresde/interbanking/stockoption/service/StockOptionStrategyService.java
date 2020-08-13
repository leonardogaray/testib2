package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.model.StockOptionStrategy;

import java.util.Collection;
import java.util.List;

/**
 * The interface Stock option strategy service.
 */
public interface StockOptionStrategyService {
    /**
     * Find by brand is in list.
     *
     * @param stockOptions the stock options
     * @param brand        the brand
     * @return the list
     */
    public List<StockOptionStrategy> findByBrandIsInToSell(Collection<StockOptionStrategy> stockOptions, String brand);

}
