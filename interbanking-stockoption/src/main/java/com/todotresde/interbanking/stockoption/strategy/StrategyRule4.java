package com.todotresde.interbanking.stockoption.strategy;

import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.model.StockOptionStrategy;
import com.todotresde.interbanking.stockoption.model.Strategy;
import com.todotresde.interbanking.stockoption.service.StockOptionStrategyService;
import com.todotresde.interbanking.stockoption.service.StockOptionStrategyServiceImpl;

import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * The type Strategy rule 4.
 */
public class StrategyRule4 extends StrategyRule {

    @Override
    public StockOption rule(Strategy strategy, StockOption previousStockOption, StockOption currentStockOption){
        StockOptionStrategyService stockOptionStrategyService = new StockOptionStrategyServiceImpl();
        List<StockOptionStrategy> stockOptionStrategies = stockOptionStrategyService.findByBrandIsInToSell(strategy.getStockOptionStrategies(), currentStockOption.getBrand());

        if(!stockOptionStrategies.isEmpty()){
            StockOptionStrategy stockOptionStrategyToSell = stockOptionStrategies.get(0);
            if (DAYS.between(stockOptionStrategyToSell.getBuyDate(), currentStockOption.getDate()) >= this.getValue()) {
                return currentStockOption;
            }
        }

        return null;
    }
}
