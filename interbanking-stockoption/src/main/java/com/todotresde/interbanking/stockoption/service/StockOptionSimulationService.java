package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.model.Strategy;

import java.util.List;

/**
 * The interface Stock option simulation service.
 */
public interface StockOptionSimulationService {
    /**
     * Simulate list.
     *
     * @param filename the filename
     * @return the list
     */
    public List<Strategy> simulate(String filename, Float userCash);

    /**
     * Read file list.
     *
     * @param filename the filename
     * @return the list
     */
    public List<StockOption> readFile(String filename);
}
