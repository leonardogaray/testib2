package com.todotresde.interbanking.stockoption.controller;

import com.todotresde.interbanking.stockoption.model.Strategy;
import com.todotresde.interbanking.stockoption.service.StockOptionSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Stock option simulation controller.
 */
@RequestMapping("/stockoption/simulate/")
@RestController
public class StockOptionSimulationController {

    /**
     * The Stock option simulation service.
     */
    @Autowired
    StockOptionSimulationService stockOptionSimulationService;

    /**
     * Gets file.
     *
     * @param filename the filename
     * @return the file
     */
    @GetMapping("/{username}/{filename:.+}/{userCash}/{buyPercentage}/{sellPercentage}/{buyAverageValue}/{sellDaysNumber}")
    public ResponseEntity<?> getFile(@PathVariable String username, @PathVariable String filename, @PathVariable Float userCash,
                                     @PathVariable Float buyPercentage, @PathVariable Float sellPercentage, @PathVariable Float buyAverageValue, @PathVariable Float sellDaysNumber) {
        List<Strategy> strategies = stockOptionSimulationService.simulate(username, filename, userCash, buyPercentage, sellPercentage, buyAverageValue, sellDaysNumber);
        return ResponseEntity.ok(strategies);
    }
}
