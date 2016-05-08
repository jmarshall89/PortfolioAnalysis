package com.portfolio.builder;

import com.portfolio.builder.holders.CorrelationResult;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jmarshall on 5/8/16.
 */
public final class PortfolioCalculator {

    public static int getMinSize(Map<String, Stock> stocks) {
        int count = 0;
        for (Stock stock : stocks.values()) {
            int numReturns = stock.getReturns().getValues().size();
            if (count == 0 || count > numReturns) {
                count = numReturns;
            }
        }
        return count;
    }

    public static Double calcReturn(Portfolio portfolio, LocalDate start, LocalDate end) {
        if (portfolio.getPortfolioEarliestDate().isBefore(start)) {
            return null; //todo filter out stocks that dont' fit this!,
        }
        Double val = 0d;
        for (Map.Entry<String, Stock> entry : portfolio.stocks.entrySet()) {
            String ticker = entry.getKey();
            Stock stock = entry.getValue();
            double weight = portfolio.stockWeights.get(ticker);
            double totReturn = stock.getTotalReturn(start, end);
            val = val + (weight * totReturn);
        }
        return val;
    }

    public static void buildReturnMatrix(Portfolio portfolio, LocalDate start, LocalDate end, CorrelationResult result) {
        double[][] returnArray = new double[portfolio.getMinSize()][];
        double[][] stocks = new double[portfolio.stocks.size()][];
        int index = 0;
        for(Stock stock : portfolio.stocks.values()) {
            addToArray(stocks, index++, stock, start, end, portfolio);
        }
        returnArray = Arrays.columnsToRows(returnArray, stocks);
        result.setReturnMatrix(returnArray);
        result.calcCovariance();
        Arrays.printArray(returnArray);
    }

    public static void addToArray(double[][] array, int index, Stock stock, LocalDate start, LocalDate end, Portfolio portfolio) {
        if (!stock.validateDate(start)) {
            //to prevent a null, remove one spot from the array and copy everything over
            double[][] temp = new double[array.length - 1][]; // prevents a null
            Arrays.copy(temp, array);
            array = temp;
            return;
        }
        List<Double> returns = stock.getReturns().getSubset(start, end);
        double[] vals = new double[returns.size()];
        for (int i = 0; i < returns.size(); i++) {
            vals[i] = returns.get(i);
        }
        array[index] = vals;
        portfolio.addToStockOrder(stock.getTicker());
    }

    private static Map<String, Double> buildRiskMap(Portfolio portfolio, LocalDate start, LocalDate end) {
        Map<String, Double> riskMap = new HashMap<>();
        for (Map.Entry<String, Stock> entry : portfolio.stocks.entrySet()) {
            List<Double> returns = entry.getValue().getReturns().getSubset(start, end);
            Double risk = MathHelper.stdDeviation(returns);
            riskMap.put(entry.getKey(), risk);
        }
        return riskMap;
    }

    public static double calcRisk(Portfolio portfolio, CorrelationResult result, LocalDate start, LocalDate end) {
        buildReturnMatrix(portfolio, start, end, result);
        double[][] correlationMatrix = result.getCorr().getCorrelationMatrix().getData();
        Map<String, Double> riskMap = buildRiskMap(portfolio, start, end);
        Map<String, Double> stockWeights = portfolio.stockWeights;
        List<String> stockOrder = portfolio.stockArrayOrder;
        double risk = 0d;
        for (int i = 0; i < stockOrder.size(); i++) {
            String currTicker = stockOrder.get(i);
            for (int j = 0; j < stockOrder.size(); j++) {
                String nextTicker = stockOrder.get(j);
                double currRisk = riskMap.get(currTicker);
                double nextRisk = riskMap.get(nextTicker);
                double currWeight = stockWeights.get(currTicker);
                double nextWeight = stockWeights.get(nextTicker);
                risk = risk
                        + currWeight
                        * nextWeight
                        * currRisk
                        * nextRisk
                        * correlationMatrix[i][j];
            }
        }
        return risk;
    }





}
