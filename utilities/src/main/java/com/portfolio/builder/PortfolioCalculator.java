package com.portfolio.builder;

import com.portfolio.builder.holders.CorrelationResult;

import java.time.LocalDate;
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
        int i = 0;
        for(Stock stock : portfolio.stocks.values()) {
            if (!stock.validateDate(start)) {
                //to prevent a null, remove one spot from the array and copy everything over
                double[][] temp = new double[stocks.length - 1][]; // prevents a null
                Arrays.copy(temp, stocks);
                stocks = temp;
                continue;
            }
            List<Double> returns = stock.getReturns().getSubset(start, end);
            double[] vals = new double[returns.size()];
            for (int j = 0; j < returns.size(); j++) {
                vals[j] = returns.get(j);
            }
            stocks[i++] = vals;
        }
        returnArray = Arrays.columnsToRows(returnArray, stocks);
        result.setReturnMatrix(returnArray);
        result.calcCovariance();
        Arrays.printArray(returnArray);
    }







}
