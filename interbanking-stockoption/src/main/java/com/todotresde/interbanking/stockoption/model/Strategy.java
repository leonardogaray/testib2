package com.todotresde.interbanking.stockoption.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.todotresde.interbanking.stockoption.strategy.StrategyRuleInterface;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Strategy.
 */
@Data
@JsonIgnoreProperties("strategyRule")
public class Strategy {
    private String userId;
    private Float cash;
    private List<StockOptionStrategy> stockOptionStrategies;
    private List<StrategyRuleInterface> strategyRules;

    public Strategy (){
        this.strategyRules = new ArrayList<StrategyRuleInterface>();
        this.stockOptionStrategies = new ArrayList<>();
        this.cash = new Float(100000);
    }

    public void addStrategyRule(StrategyRuleInterface strategyRule){
        this.strategyRules.add(strategyRule);
    }
    public void sortStockOptionStrategies(){
        Collections.sort(this.stockOptionStrategies);
    }
}
