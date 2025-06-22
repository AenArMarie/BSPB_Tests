package com.bspbtests.utility.dataprocessing;

public class NumericComparisons {

    public static boolean equalsWithMargin(double a, double b, double margin) {
        double tol = Math.abs(margin);
        if (Double.compare(a, b) == 0) {
            return true;
        }
        double diff = Math.abs(a - b);
        return diff <= tol;
    }
}
