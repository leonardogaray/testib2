package com.todotresde.interbanking.stockoption.strategy;

import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.model.Strategy;

public interface StrategyRuleInterface {
    public StockOption rule( Strategy strategy, StockOption previousStockOption,  StockOption currentStockOption );

    public Boolean getSellAction();

    public StrategyRule setSellAction(Boolean sellAction);
}
