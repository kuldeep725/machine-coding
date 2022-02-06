package com.splitwise;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Formatter {

    private final DecimalFormat df;

    public Formatter() {
        df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
    }

    public double formatDouble(double value) {
        return Double.parseDouble(df.format(value));
    }

}
