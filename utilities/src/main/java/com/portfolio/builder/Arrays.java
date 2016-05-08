package com.portfolio.builder;

/**
 * Created by jmarshall on 5/7/16.
 */
public class Arrays {

    /**
     * Given arrays representing columns, this method will convert each item in the various
     * arrays into an array or row arrays
     * @param index starting index to convert to rows
     * @param mainArray Array of row arrays
     * @param arrays column arrays to be converted to row arrays
     * @return mainArray, which is passed in
     */
    private static double[][] columnsToRows(int index, double[][] mainArray, double[]... arrays) {
        if (index >= mainArray.length || isEmpty(arrays)) {
            return mainArray;
        }
        double[] row = new double[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            row[i] = arrays[i][index];
        }
        mainArray[index] = row;
        return columnsToRows(++index, mainArray, arrays);
    }

    /**
     * Overwrites the private columnsToRows method, as it's not necessary for the user to
     * provide an index. That's simply to allow for recursion.
     * @param mainArray Array of row arrays, instantiated by the user
     * @param arrays column arrays to be converted to row arrays
     * @return mainArray object
     */
    public static double[][] columnsToRows(double[][] mainArray, double[] arrays) {
        int index = 0;
        return columnsToRows(index, mainArray, arrays);
    }

    /**
     * Helper method for testing if given arrays are all empty. The test is all arrays have
     * length equal to 0
     * @param arrays indefinite arrays to test
     * @return true if all arrays are empty, false otherwise
     */
    public static boolean isEmpty(double[]... arrays) {
        for (double[] array : arrays) {
            if (array.length != 0) {
                return false;
            }
        }
        return true;
    }






}
