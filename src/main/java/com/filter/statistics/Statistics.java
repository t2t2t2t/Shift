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
            System.out.println("String statistics");
            stringStatistics.longStatic();
        }

        if (integerStatistics != null) {
            System.out.println("Integer statistics");
            integerStatistics.longStatic();
        }

        if (floatStatistics != null) {
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.printf ("│ %-45s │%n", "Float statistics");

            floatStatistics.longStatic();
        }

    }

    public void shortStatistics() {
        if (stringStatistics != null) {
            System.out.println("String statistics");
            stringStatistics.shortStatic();
        }

        if (integerStatistics != null) {
            System.out.println("Integer statistics");
            integerStatistics.shortStatic();
        }

        if (floatStatistics != null) {
            System.out.println("Float statistics");
            floatStatistics.shortStatic();
        }

    }
}
