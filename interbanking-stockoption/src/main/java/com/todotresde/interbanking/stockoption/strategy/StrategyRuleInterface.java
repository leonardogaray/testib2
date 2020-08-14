package com.todotresde.interbanking.stockoption.strategy;

import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.model.Strategy;

/**
 * The interface Strategy rule interface.
 */
public interface StrategyRuleInterface {
    /**
     * Rule stock option.
     *
     * @param strategy            the strategy
     * @param previousStockOption the previous stock option
     * @param currentStockOption  the current stock option
     * @return the stock option
     */
    public StockOption rule( Strategy strategy, StockOption previousStockOption,  StockOption currentStockOption );

    /**
     * Gets sell action.
     *
     * @return the sell action
     */
    public Boolean getSellAction();

    /**
     * Sets sell action.
     *
     * @param sellAction the sell action
     * @return the sell action
     */
    public StrategyRule setSellAction(Boolean sellAction);
}
