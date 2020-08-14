package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.model.Strategy;

import java.util.List;

/**
 * The interface Strategy service.
 */
public interface StrategyService {
    /**
     * Simulate.
     *
     * @param strategy             the strategy
     * @param previousStockOptions the previous stock options
     * @param currentStockOptions  the current stock options
     */
    public void simulate(Strategy strategy, List<StockOption> previousStockOptions, List<StockOption> currentStockOptions);

    /**
     * Buy stock options.
     *
     * @param strategy     the strategy
     * @param stockOptions the stock options
     */
    public void buyStockOptions(Strategy strategy, List<StockOption> stockOptions);

    /**
     * Sell stock options.
     *
     * @param strategy     the strategy
     * @param stockOptions the stock options
     * @param forceCell    the force cell
     */
    public void sellStockOptions(Strategy strategy, List<StockOption> stockOptions, Boolean forceCell);

    /**
     * Generate strategies list.
     *
     * @return the list
     */
    public List<Strategy> generateStrategies(Float userCash);
}
