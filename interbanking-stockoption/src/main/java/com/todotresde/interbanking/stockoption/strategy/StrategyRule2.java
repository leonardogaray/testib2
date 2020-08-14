package com.todotresde.interbanking.stockoption.strategy;

import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.model.Strategy;

/**
 * The type Strategy rule 2.
 */
public class StrategyRule2 extends StrategyRule {

    @Override
    public StockOption rule(Strategy strategy, StockOption previousStockOption,  StockOption currentStockOption){
        Float priceDifference = previousStockOption.getPrice() - currentStockOption.getPrice();
        if (priceDifference < 0 && Math.abs(priceDifference) > previousStockOption.getPrice() * 0.02) {
            return currentStockOption;
        }
        return null;
    }
}
