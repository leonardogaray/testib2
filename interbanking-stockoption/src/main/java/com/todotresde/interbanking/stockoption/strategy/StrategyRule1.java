package com.todotresde.interbanking.stockoption.strategy;

import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.model.Strategy;

/**
 * The type Strategy rule 1.
 */
public class StrategyRule1 extends StrategyRule {
    @Override
    public StockOption rule(Strategy strategy, StockOption previousStockOption, StockOption currentStockOption) {
        Float priceDifference = previousStockOption.getPrice() - currentStockOption.getPrice();
        if (priceDifference > 0 && priceDifference > previousStockOption.getPrice() * (this.getValue()/100)) {
            return currentStockOption;
        }
        return null;
    }
}
