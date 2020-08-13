package com.todotresde.interbanking.stockoption.strategy;

abstract class StrategyRule implements StrategyRuleInterface{
    private Boolean sellAction;

    public StrategyRule setSellAction(Boolean sellAction){
        this.sellAction = sellAction;
        return this;
    }
    public Boolean getSellAction(){
        return this.sellAction;
    }

}
