package com.portfolio.builder;

import com.portfolio.builder.holders.CorrelationResult;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by jmarshall on 4/19/16.
 * Contains a list of stocks
 */
public class Portfolio {

    Map<String, Stock> stocks = new HashMap<>();
    Map<String, Double> stockWeights = new HashMap<>();
    Stock market;
    Optional<Double> beta = Optional.empty();
    List<String> stockArrayOrder = new ArrayList<>();

    public void addStock(Stock stock) {
        if (stock instanceof Market) {
            this.market = stock;
        } else {
            this.stocks.put(stock.getTicker(), stock);
            this.stockWeights.put(stock.getTicker(), 1d);
        }
        rebalance();
    }

    public LocalDate getPortfolioEnd() {
        return stocks.values().stream().max(Stock.END_COMP()).get().getEnd();
    }

    public LocalDate getPortfolioEarliestDate() {
        return stocks.values().stream().max(Stock.EARLIEST_START()).get().getStart();
    }

    public int getMinSize() {
        return PortfolioCalculator.getMinSize(stocks);
    }

    private void rebalance() {
        double count = stockWeights.size();
        double equality = 1d / count;
        for (Map.Entry<String, Double> entry : stockWeights.entrySet()) {
            stockWeights.put(entry.getKey(), equality);
        }
    }

    public Double calcReturn(LocalDate start, LocalDate end) {
        return PortfolioCalculator.calcReturn(this, start, end);
    }

    public Double calcLastYearReturn() {
        return calcReturn(getPortfolioEnd().minusYears(1), getPortfolioEnd());
    }

//    public Double calcRisk(LocalDate start, LocalDate end) {
//        Double
//    }

//    public double[] calcCorrelationMatrix() {
//
//    }

    public void setupTest() {
        CorrelationResult result = new CorrelationResult();
        buildReturnMatrixLastYear(result);
        result.calcCovariance();
        result.getCorr();
        double totalRisk = PortfolioCalculator.calcRisk(this, result, getPortfolioEnd().minusYears(1), getPortfolioEnd());
    }

    public void buildReturnMatrix(LocalDate start, LocalDate end, CorrelationResult result) {
        PortfolioCalculator.buildReturnMatrix(this, start, end, result);
    }

    public void buildReturnMatrixLastYear(CorrelationResult result) {
        buildReturnMatrix(getPortfolioEnd().minusYears(1), getPortfolioEnd(), result);
    }


//    //getters and setters
//    public Double getorCalcBeta() {
//        if (beta.isPresent()) {
//            return beta.get();
//        } else {
//
//        }
//    }

    public void addToStockOrder(String ticker) {
        this.stockArrayOrder.add(ticker);
    }

    public Stock getMarket() {
        return market;
    }

    public void setBeta(Double beta) {
        this.beta = Optional.of(beta);
    }

}
