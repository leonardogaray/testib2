package com.todotresde.interbanking.stockoption.controller;

import com.todotresde.interbanking.stockoption.model.Strategy;
import com.todotresde.interbanking.stockoption.service.StockOptionSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    @GetMapping("/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        List<Strategy> strategies = stockOptionSimulationService.simulate(filename);
        return ResponseEntity.ok(strategies);
    }
}
