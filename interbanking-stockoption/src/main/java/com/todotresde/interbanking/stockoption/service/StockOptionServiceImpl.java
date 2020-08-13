package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.model.StockOption;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * The type Stock option service.
 */
@Service
public class StockOptionServiceImpl implements StockOptionService{
    @Override
    public StockOption findByBrandIsIn(Collection<StockOption> stockOptions, String brand) {
        return stockOptions.stream().filter(stockOption -> brand.equals(stockOption.getBrand())).findFirst().orElse(null);
    }
}
