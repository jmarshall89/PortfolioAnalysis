package com.portfolio.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jmarshall on 4/19/16.
 * Contains a list of stocks
 */
public class Portfolio {

    Map<String, Stock> stocks = new HashMap<>();
    Map<String, Double> stockWeights = new HashMap<>();
    Stock market;
    Optional<Double> beta = Optional.empty();

    public void addStock(Stock stock) {
        if (stock instanceof Market) {
            this.market = stock;
        } else {
            this.stocks.put(stock.getTicker(), stock);
            this.stockWeights.put(stock.getTicker(), 1d);
        }
        rebalance();
    }

    private void rebalance() {
        double count = stockWeights.size();
        double equality = 1d / count;
        for (Map.Entry<String, Double> entry : stockWeights.entrySet()) {
            stockWeights.put(entry.getKey(), equality);
        }
    }

    //todo: first make returns more solid
//    public Double calcTotalReturn() {
//        Double val = 0d;
//        for (Map.Entry<String, com.portfolio.builder.Stock> entry : stocks.entrySet()) {
//            String ticker = entry.getKey();
//            com.portfolio.builder.Stock stock = entry.getValue();
//            double weight = stockWeights.get(ticker);
//            val = val + (stock.get)
//        }
//    }





//    //getters and setters
//    public Double getorCalcBeta() {
//        if (beta.isPresent()) {
//            return beta.get();
//        } else {
//
//        }
//    }


    public Stock getMarket() {
        return market;
    }

    public void setBeta(Double beta) {
        this.beta = Optional.of(beta);
    }
}
