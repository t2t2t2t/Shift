package com.filter.statistics;

import com.filter.core.ApplicationOptions;
import com.filter.core.DataType;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Statistics {

    private static StringStatistics stringStatistics = null;
    private static NumericStatistics integerStatistics = null;
    private static NumericStatistics floatStatistics = null;

    public void add(String str) {
        if(!ApplicationOptions.getInstance().isFullStatistics()&&!ApplicationOptions.getInstance().isShortStatistics()){
            return;
        }
        DataType type = DataType.detentionType(str);
        try {
            switch (type) {
                case STRING:
                    if (stringStatistics == null) {
                        stringStatistics = new StringStatistics();
                    }
                    stringStatistics.addString(str);
                    break;
                case INTEGER:
                    if (integerStatistics == null) {
                        integerStatistics = new NumericStatistics();
                    }
                    integerStatistics.addNum(new BigInteger(str));
                    break;
                case FLOAT:
                    if (floatStatistics == null) {
                        floatStatistics = new NumericStatistics();
                    }
                    floatStatistics.addNum(new BigDecimal(str.replace(",",".")));
                    break;
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing value: " + str + " as " + type);
        }
    }

    public void longStatistics() {

        if (stringStatistics != null) {
            printCentered("String statistics");
            stringStatistics.longStatic();
        }

        if (integerStatistics != null) {
            printCentered("Integer statistics");
            integerStatistics.longStatic();
        }

        if (floatStatistics != null) {
            printCentered("Float statistics");

            floatStatistics.longStatic();
        }

    }

    public void shortStatistics() {
        if (stringStatistics != null) {
            printCentered("String statistics");
            stringStatistics.shortStatic();
        }

        if (integerStatistics != null) {
            printCentered("Integer statistics");
            integerStatistics.shortStatic();
        }

        if (floatStatistics != null) {
            printCentered("Float statistics");
            floatStatistics.shortStatic();
        }

    }
    private void printCentered(String text) {
        int width=50;
        int padding = (width - text.length()) / 2;
        padding = Math.max(0, padding);

        String format = "%" + (padding + text.length()) + "s%n";
        System.out.printf(format, "-".repeat(text.length()));
        System.out.printf(format, text);

    }
}
