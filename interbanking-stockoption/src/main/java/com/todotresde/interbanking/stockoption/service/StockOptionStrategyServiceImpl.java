package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.model.StockOptionStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Stock option strategy service.
 */
@Service
public class StockOptionStrategyServiceImpl implements com.todotresde.interbanking.stockoption.service.StockOptionStrategyService {
    @Override
    public List<StockOptionStrategy> findByBrandIsInToSell(Collection<StockOptionStrategy> StockOptionStrategies, String brand) {
        return StockOptionStrategies.stream().filter(stockOptionStrategy -> {
            return (brand.equals(stockOptionStrategy.getBrand()) && stockOptionStrategy.getSellDate() == null);
        }).collect(Collectors
                .toCollection(ArrayList::new));
    }
}
