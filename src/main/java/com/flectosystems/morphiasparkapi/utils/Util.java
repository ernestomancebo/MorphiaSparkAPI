package com.flectosystems.morphiasparkapi.utils;

import java.util.Arrays;

/**
 * Created by Ernesto Mancebo T on 1/19/15.
 */
public final class Util {

    public static double[] getLocationFromString(String s) throws NumberFormatException {
        return Arrays.stream(s.split(","))
                .mapToDouble(position -> Double.parseDouble(position))
                .toArray();
    }

    private Util() {
    }
}
