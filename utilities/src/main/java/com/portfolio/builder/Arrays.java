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
        if (index >= mainArray.length || isEmpty(index, arrays)) {
            return mainArray;
        }
        double[] row = new double[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            //bug..make sure to test length of these arrays when built
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
    public static double[][] columnsToRows(double[][] mainArray, double[]... arrays) {
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

    public static boolean isEmpty(int index, double[]... arrays) {
        for (double[] array : arrays) {
            if (index < array.length) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            double[] inside = array[i];
            System.out.println("Outer Array " + i);
            for (int j = 0; j < inside.length; j++) {
                Double val = inside[j];
                String message = val == null ?
                        "      Val is null"
                        :"       " + val;
                System.out.println(message);
            }
        }
    }

    /**
     * Method for copying one array to the other. This is a safe method, as it will not return a null. If the length
     * of the copyTo array is less than copyFrom array, then it will simply copy until the copyTo array is full. If
     * the copyFrom is shorter than the copyTo, then it will copy all available units.
     * WARNING: DOES NOT COPY ARRAYS WITHIN THE MAIN ARRAY, SIMPLY COPIES OVER THEIR REFERENCE.
     * @param copyTo The array to copy to
     * @param copyFrom The array to be copied
     * @return copied array
     */
    public static double[][] copy(double[][] copyTo, double[][] copyFrom) {
        if (copyTo.length == 0 || copyFrom.length == 0) {
            return copyTo;
        }
        int minlength = Math.min(copyTo.length, copyFrom.length);
        for (int i = 0; i < minlength; i++) {
            copyTo[i] = copyFrom[i]; // Make sure to copy the actual array, rather than provide a reference to it
        }
        return copyTo;
    }



}
