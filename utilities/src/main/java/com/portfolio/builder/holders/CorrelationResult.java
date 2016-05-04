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

    public void setReturnMatrix(Double[][] returnMatrix) {
        double[][] prim = new double[returnMatrix.length][];
        for (int i= 0; i < returnMatrix.length; i++) {
            double[] inside = new double[returnMatrix[i].length];
            for (int j = 0; j < returnMatrix[i].length; j++) {
                inside[j] = returnMatrix[i][j];
            }
            prim[i] = inside;
        }
        this.returnMatrix = prim;
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
