package com.bspbtests.utility.dataprocessing;

public class StringProcessing {

    public static String splitStringByTextAndGetPart(String string, String separator, int partIndex) {
        String[] parts = string.split(separator);
        if (parts.length > partIndex) {
            return parts[partIndex];
        } else return "";
    }
}
