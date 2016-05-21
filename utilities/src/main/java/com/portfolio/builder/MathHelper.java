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
        return list.stream()
                .reduce(0d, Double::sum)
                / list.size();
    }

    public static Double stdDeviation(List<Double> list) {
        if (list.size() == 0) {
            return 0d;
        }
        if (list.size() == 1) {
            return 0d;
        }
        Double mean = mean(list);
        Double sumSquaredDievations =
                list.stream()
                .map(a -> Math.pow((a - mean), 2))
                .reduce(Double::sum)
                .get();
        return Math.pow((sumSquaredDievations / list.size()), (1.00/2.00));
    }
}
