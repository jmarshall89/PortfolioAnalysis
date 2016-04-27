package com.portfolio.builder;

import java.util.List;

/**
 * Created by jmarshall on 4/26/16.
 */
public final class MathHelper {

    public static Double mean(List<Double> list) {
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        double sum = 0;
        for (double val : list) {
            sum += val;
        }
        return sum / list.size();
    }
}
