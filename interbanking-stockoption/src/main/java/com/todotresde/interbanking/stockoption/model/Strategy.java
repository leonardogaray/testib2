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
    private Integer strategyId;
    private String userId;
    private Float cash;
    private List<StockOptionStrategy> stockOptionStrategies;
    private List<StrategyRuleInterface> strategyRules;

    /**
     * Instantiates a new Strategy.
     */
    public Strategy (Integer strategyId, Float userCash){
        this.strategyId = strategyId;
        this.strategyRules = new ArrayList<StrategyRuleInterface>();
        this.stockOptionStrategies = new ArrayList<>();
        this.cash = new Float(userCash);
    }

    /**
     * Add strategy rule.
     *
     * @param strategyRule the strategy rule
     */
    public void addStrategyRule(StrategyRuleInterface strategyRule){
        this.strategyRules.add(strategyRule);
    }

    /**
     * Sort stock option strategies.
     */
    public void sortStockOptionStrategies(){
        Collections.sort(this.stockOptionStrategies);
    }
}
