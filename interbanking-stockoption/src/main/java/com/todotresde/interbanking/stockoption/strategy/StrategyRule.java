package com.todotresde.interbanking.stockoption.strategy;

/**
 * The type Strategy rule.
 */
abstract class StrategyRule implements StrategyRuleInterface{
    private Boolean sellAction;
    private Float value;

    public StrategyRule setValue(Float value){
        this.value = value;
        return this;
    }

    public Float getValue(){
        return this.value;
    }

    public StrategyRule setSellAction(Boolean sellAction){
        this.sellAction = sellAction;
        return this;
    }

    public Boolean getSellAction(){
        return this.sellAction;
    }

}
