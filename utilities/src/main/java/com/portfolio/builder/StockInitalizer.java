package com.portfolio.builder;

import com.portfolio.builder.tseries.AbstractTSeries;
import com.portfolio.builder.tseries.BaseTSeries;

import java.time.LocalDate;

/**
 * Created by jmarshall on 4/17/16.
 *
 * This is a stateless class used to initialize a stock.
 * Initializing a stock refers to parsing the prices
 * either from a CSV or (todo from an api).
 *
 * There is also functunality for calculating returns.
 */
public final class StockInitalizer {

    /**
     * initalizes the stock by populating the prices.
     * Either parses the CSV or pulls from an api (future state)
     * @param stock
     */
    public static void populatePrices(Stock stock) {
        AbstractTSeries prices;
        prices = CSVReader.getAdjClose(stock.getTicker(), Constants.STOCK_DIRECTORY);
        stock.setPrices(prices);
    }

    public static void populateReturns(Stock stock) {
        if (stock.getPrices().isEmpty()) {
            populatePrices(stock);
        }
        BaseTSeries returns = new BaseTSeries();
        //Dates are in asending order
        Double prevDate = null;
        Double thisDate;
        for (LocalDate date : stock.getPrices().dates()) {
            if (prevDate == null) {
                prevDate = stock.getPrices().getValue(date);
                continue;
            }
            thisDate = stock.getPrices().getValue(date);
            Double percent = Math.log(thisDate / prevDate);
            returns.add(date, percent);
            prevDate = thisDate;
        }
        stock.setReturns(returns);
    }

}
