package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.model.*;
import com.todotresde.interbanking.stockoption.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Strategy service.
 */
@Service
public class StrategyServiceImpl implements StrategyService{
    @Autowired
    private StockOptionService stockOptionService;
    @Autowired
    private com.todotresde.interbanking.stockoption.service.StockOptionStrategyService stockOptionStrategyService;

    @Override
    public void simulate(Strategy strategy, List<StockOption> previousStockOptions, List<StockOption> currentStockOptions) {
        List<StockOption> possibleStockOptionsToBuy = new ArrayList<StockOption>();
        List<StockOption> possibleStockOptionsToSell = new ArrayList<StockOption>();

        for (StockOption previousStockOption : previousStockOptions) {
            StockOption currentStockOption = stockOptionService.findByBrandIsIn(currentStockOptions, previousStockOption.getBrand());

            if (currentStockOption != null) {
                strategy.getStrategyRules().forEach(strategyRule -> {
                    if(!strategyRule.getSellAction()){
                        possibleStockOptionsToBuy.add(strategyRule.rule(strategy, previousStockOption, currentStockOption));
                    }else{
                        possibleStockOptionsToSell.add(strategyRule.rule(strategy, previousStockOption, currentStockOption));
                    }
                });
            }
        }

        buyStockOptions(strategy, possibleStockOptionsToBuy);
        sellStockOptions(strategy, possibleStockOptionsToSell, false);
    }

    @Override
    public void buyStockOptions(Strategy strategy, List<StockOption> stockOptions){
        for(StockOption stockOption : stockOptions) {
            buyStockOption(strategy, stockOption);
        }
    }

    @Override
    public void sellStockOptions(Strategy strategy, List<StockOption> stockOptions, Boolean forceCell){
        for(StockOption stockOption : stockOptions) {
            sellStockOption(strategy, stockOption, forceCell);
        }
    }

    private void buyStockOption(Strategy strategy, StockOption stockOption){
        if(stockOption != null){
            List <StockOptionStrategy> stockOptionStrategies = stockOptionStrategyService.findByBrandIsInToSell(strategy.getStockOptionStrategies(), stockOption.getBrand());

            if(stockOptionStrategies.isEmpty()) {
                Integer numberOfStockOptions = (int) Math.floor(1000 / stockOption.getPrice());
                strategy.setCash(strategy.getCash() - (numberOfStockOptions * stockOption.getPrice()));
                strategy.getStockOptionStrategies().add(new StockOptionStrategy(numberOfStockOptions, stockOption));

            }
        }
    }

    private void sellStockOption(Strategy strategy, StockOption stockOption, Boolean forceSell){
        if(stockOption != null){
            strategy.getStockOptionStrategies().forEach(stockOptionStrategy -> {
                Boolean mandatoryToSell = forceSell && stockOption.getBrand().equals(stockOptionStrategy.getBrand())
                        && stockOptionStrategy.getSellDate() == null;
                Boolean canSell = stockOption.getBrand().equals(stockOptionStrategy.getBrand()) && stockOptionStrategy.getSellDate() == null
                        && stockOption.getDate().isAfter(stockOptionStrategy.getBuyDate());

                if(mandatoryToSell || canSell){
                    stockOptionStrategy.setSellDate(stockOption.getDate());
                    stockOptionStrategy.setSellPrice(stockOption.getPrice());
                    strategy.setCash(strategy.getCash() + (stockOptionStrategy.getCount() * stockOptionStrategy.getSellPrice()));
                }
            });
        }
    }

    public List<Strategy> generateStrategies(){
        List<Strategy> strategies = new ArrayList<Strategy>();
        StrategyRuleInterface strategyRule = null;
        Strategy strategy = null;

        //Strategy 1
        strategy = new Strategy();
        strategyRule = new StrategyRule1();
        strategy.addStrategyRule(strategyRule.setSellAction(false));
        strategyRule = new StrategyRule2();
        strategy.addStrategyRule(strategyRule.setSellAction(true));
        strategies.add(strategy);

        //Strategy 2
        strategy = new Strategy();
        strategyRule = new StrategyRule3();
        strategy.addStrategyRule(strategyRule.setSellAction(false));
        strategyRule = new StrategyRule4();
        strategy.addStrategyRule(strategyRule.setSellAction(true));
        strategies.add(strategy);

        //Strategy 3
        strategy = new Strategy();
        strategyRule = new StrategyRule1();
        strategy.addStrategyRule(strategyRule.setSellAction(false));
        strategyRule = new StrategyRule2();
        strategy.addStrategyRule(strategyRule.setSellAction(true));
        strategyRule = new StrategyRule3();
        strategy.addStrategyRule(strategyRule.setSellAction(false));
        strategyRule = new StrategyRule4();
        strategy.addStrategyRule(strategyRule.setSellAction(true));
        strategies.add(strategy);

        return strategies;
    }
}
