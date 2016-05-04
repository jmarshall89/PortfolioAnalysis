package com.portfolio.builder;

import com.portfolio.builder.holders.CorrelationResult;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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

    public LocalDate getPortfolioEnd() {
        return stocks.values().stream().max(Stock.END_COMP()).get().getEnd();
    }

    public LocalDate getPortfolioEarliestDate() {
        return stocks.values().stream().max(Stock.EARLIEST_START()).get().getStart();
    }

    private void rebalance() {
        double count = stockWeights.size();
        double equality = 1d / count;
        for (Map.Entry<String, Double> entry : stockWeights.entrySet()) {
            stockWeights.put(entry.getKey(), equality);
        }
    }

    public Double calcReturn(LocalDate start, LocalDate end) {
        if (getPortfolioEarliestDate().isBefore(start)) {
            return null; //todo filter out stocks that dont' fit this!,
        }
        Double val = 0d;
        for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
            String ticker = entry.getKey();
            Stock stock = entry.getValue();
            double weight = stockWeights.get(ticker);
            double totReturn = stock.getTotalReturn(start, end);
            val = val + (weight * totReturn);
        }
        return val;
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
//        result.getCorr()
    }

    public void buildReturnMatrix(LocalDate start, LocalDate end, CorrelationResult result) {
        Double[][] returns = new Double[stocks.size()][];
        int i = 0;
        for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
            result.addStock(entry.getKey(), i);
            Stock stock = entry.getValue();
            List<Double> vals = stock.getReturns().getSubset(start, end);
            Double[] valsArray = vals.toArray(new Double[vals.size()]);
            returns[i++] = valsArray;
        }
        result.setReturnMatrix(returns);
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

    public Stock getMarket() {
        return market;
    }

    public void setBeta(Double beta) {
        this.beta = Optional.of(beta);
    }

}
