package com.portfolio.builder;

import java.util.LinkedList;

/**
 * Created by jmarshall on 4/19/16.
 */
public class MainTester {
    public static void main(String[] args) {

//        Portfolio portfolio = PortfolioBuilder.buildPortfolioFromFiles();
//        portfolio.setupTest();
        LinkedList<Double> array1 = new LinkedList<Double>() {{add(1d); add(2d); add(3d);}};
        LinkedList<Double> array2 = new LinkedList<Double>() {{add(4d); add(5d); add(6d);}};
        LinkedList<LinkedList<Double>> mainArray = new LinkedList<LinkedList<Double>>();
        mainArray = buildArray(mainArray, array1, array2);
        System.out.println(mainArray);
    }

    public static LinkedList<LinkedList<Double>> buildArray(LinkedList<LinkedList<Double>> mainArray, LinkedList<Double>... arrays) {
        if (arrays.length == 0 || isEmpty(arrays)) {
            return mainArray;
        }
        LinkedList<LinkedList<Double>> fullList = new LinkedList<LinkedList<Double>>();
        LinkedList<Double> row = new LinkedList<>();
        for (int i = 0; i < arrays.length; i++) {
            row.add(arrays[i].getFirst());
        }
        fullList.add(row);
        mainArray.add(row);
        removeOne(arrays);
        return buildArray(mainArray, arrays);

    }

    public static boolean isEmpty(LinkedList<Double>... arrays) {
        for (LinkedList<Double> array : arrays) {
            if (!array.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void removeOne(LinkedList<Double>... arrays) {
        if (arrays.length == 0) return;
        for (int i = 0; i < arrays.length; i++) {
            arrays[i].remove(0);
        }
    }


}
