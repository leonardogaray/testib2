package com.todotresde.interbanking.stockoption.strategy;

import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.model.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Strategy rule 3.
 */
public class StrategyRule3 extends StrategyRule{
    private Map<String, List<Float>> lastStockOptions = new HashMap<String, List<Float>>();

    @Override
    public StockOption rule(Strategy strategy, StockOption previousStockOption, StockOption currentStockOption){
        if(lastStockOptions.get(currentStockOption.getBrand()) == null){
            List<Float> arrayList = new ArrayList<Float>();
            arrayList.add(0f);//Used for Total
            arrayList.add(0f);//Used for Total Ammount
            lastStockOptions.put(currentStockOption.getBrand(), arrayList);
        }
        Float total = lastStockOptions.get(currentStockOption.getBrand()).get(0) + 1;
        Float totalValues = lastStockOptions.get(currentStockOption.getBrand()).get(1) + currentStockOption.getPrice();

        lastStockOptions.get(currentStockOption.getBrand()).set(0, total);
        lastStockOptions.get(currentStockOption.getBrand()).set(1, totalValues );

        if(currentStockOption.getPrice() > (totalValues/total) * 2 ){
            return currentStockOption;
        }
        return null;
    }
}
