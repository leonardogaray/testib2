package com.todotresde.interbanking.stockoption.model;

import lombok.Data;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The type Stock option.
 */
@Data
public class StockOption implements Comparable<StockOption>{
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/y");

    private String brand;
    private Float price;
    private LocalDate date;

    /**
     * Instantiates a new Stock option.
     *
     * @param brand the brand
     * @param date  the date
     * @param price the price
     */
    public StockOption(String brand, String date, String price){
        this.brand = brand;
        this.date = LocalDate.parse(date, FORMATTER);

        try {
            this.price = getFormat().parse(price.replace("$", "")).floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(com.todotresde.interbanking.stockoption.model.StockOption stockOption) {
        return getDate().compareTo(stockOption.getDate());
    }

    public static Boolean IsValid(String brand, String date, String price){
        try{
            LocalDate.parse(date, FORMATTER);
        }catch(Exception exception){
            return false;
        }

        try {
            getFormat().parse(price.replace("$", "")).floatValue();
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private static DecimalFormat getFormat(){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');

        DecimalFormat format = new DecimalFormat("0.#");
        format.setDecimalFormatSymbols(symbols);

        return format;
    }
}
