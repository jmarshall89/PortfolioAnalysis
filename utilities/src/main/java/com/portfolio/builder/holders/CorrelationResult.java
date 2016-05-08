package com.portfolio.builder.holders;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jmarshall on 5/3/16.
 */
public class CorrelationResult {
    Map<String, Integer> stockIndexLocation = new HashMap<>();
    double[][] returnMatrix;
    double[] correlations;
    PearsonsCorrelation corr;

    public void addStock(String ticker, int i) {
        stockIndexLocation.put(ticker, i);
    }

    public void setReturnMatrix(double[][] returnMatrix) {
        this.returnMatrix = returnMatrix;
    }

    public double[][] getReturnMatrix() {
        return returnMatrix;
    }

    public void calcCovariance() {
        corr = new PearsonsCorrelation(getReturnMatrix());
    }

    public PearsonsCorrelation getCorr() {
        return corr;
    }

}
