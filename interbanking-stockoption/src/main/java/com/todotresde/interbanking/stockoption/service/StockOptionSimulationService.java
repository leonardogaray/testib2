package com.todotresde.interbanking.stockoption.service;

import com.todotresde.interbanking.stockoption.model.StockOption;
import com.todotresde.interbanking.stockoption.model.Strategy;

import java.nio.file.Path;
import java.util.List;

/**
 * The interface Stock option simulation service.
 */
public interface StockOptionSimulationService {
    /**
     * Simulate list.
     *
     * @param username the username
     * @return the list
     */
    public List<Strategy> simulate(String username, String filename, Float userCash, Float buyPercentage, Float sellPercentage, Float buyAverageValue, Float sellDaysNumber);

    /**
     * Read file list.
     *
     * @param filename the filename
     * @return the list
     */
    public List<StockOption> readFile(String username, String filename);

    public Path getRoot();

    public Path getRootForUser(String username);
}
