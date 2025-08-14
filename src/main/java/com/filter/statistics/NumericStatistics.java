package com.filter.statistics;

import com.filter.core.ApplicationOptions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class NumericStatistics {
    private BigDecimal max = null;
    private BigDecimal min = null;
    private BigDecimal sum = BigDecimal.ZERO;
    private BigDecimal count = BigDecimal.ZERO;

    public void addNum(Number num) {

        if (num == null) {
            return;
        }

        BigDecimal bdNum = getBD(num);
        sum=sum.add(bdNum);
        count=count.add(BigDecimal.ONE);
        if (ApplicationOptions.getInstance().isFullStatistics()) {
            if (max == null || bdNum.compareTo(max) > 0) {
                max = bdNum;
            }
            if (min == null || bdNum.compareTo(min) < 0) {
                min = bdNum;
            }
        }
    }

    public BigDecimal getBD(Number num) {
        if (num instanceof BigDecimal) {
            return (BigDecimal) num;
        } else if (num instanceof BigInteger) {
            return new BigDecimal((BigInteger) num);
        } else if (num instanceof Double) {
            return new BigDecimal((Double) num);
        }
        return BigDecimal.ZERO;
    }

    public void longStatic() {
        if (count.equals(BigDecimal.ZERO)) {
            System.out.println("No data to display.");
            return;
        }

        BigDecimal average = sum.divide(count, 3, RoundingMode.HALF_UP);

        System.out.println("┌──────────────────────┬──────────────────────┐");
        System.out.printf ("│ %-20s │ %20s │%n", "Metric", "Value");
        System.out.println("├──────────────────────┼──────────────────────┤");
        System.out.printf ("│ %-20s │ %20s │%n", "Count", compactNum(count));
        System.out.printf ("│ %-20s │ %20s │%n", "Max", compactNum(max));
        System.out.printf ("│ %-20s │ %20s │%n", "Min", compactNum(min));
        System.out.printf ("│ %-20s │ %20s │%n", "Average", compactNum(average));
        System.out.printf ("│ %-20s │ %20s │%n", "Sum", compactNum(sum));
        System.out.println("└──────────────────────┴──────────────────────┘");
    }

    public void shortStatic() {
        System.out.println("┌──────────────────────┬──────────────────────┐");
        System.out.printf ("│ %-20s │ %20s │%n", "Count", count.toPlainString());
        System.out.println("└──────────────────────┴──────────────────────┘");
    }

    private String compactNum(BigDecimal num) {
        if (num == null) return "N/A";
        if (num.compareTo(BigDecimal.ZERO) == 0) return "0";
        return num.round(new java.math.MathContext(3, java.math.RoundingMode.HALF_UP)).toString();
    }

}
