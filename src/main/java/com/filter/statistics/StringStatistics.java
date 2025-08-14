package com.filter.statistics;

import com.filter.core.ApplicationOptions;

public class StringStatistics {
    private long maxSize=Long.MIN_VALUE;
    private long minSize=Long.MAX_VALUE;
    private String minString="";
    private String maxString="";
    private long count=0;

    public void addString(String str){
        count+=1;
        if (ApplicationOptions.getInstance().isFullStatistics()){
            if(str.length()>maxSize){
                maxString=str;
                maxSize=str.length();
            }
            if(str.length()<minSize){
                minString=str;
                minSize=str.length();
            }
        }

    }
    public void longStatic() {

        System.out.println("┌──────────────────────┬──────────────────────┐");
        System.out.printf ("│ %-20s │ %20s │%n", "String Metric", "Value");
        System.out.println("├──────────────────────┼──────────────────────┤");
        System.out.printf ("│ %-20s │ %20d │%n", "Total strings", count);
        //System.out.printf ("│ %-20s │ %20s │%n", "Longest string", maxString);
        printRow(maxString);
        System.out.printf ("│ %-20s │ %20d │%n", "Longest length", maxSize);
        System.out.printf ("│ %-20s │ %20s │%n", "Shortest string", minString);
        System.out.printf ("│ %-20s │ %20d │%n", "Shortest length", minSize);
        System.out.println("└──────────────────────┴──────────────────────┘");
    }
    public void shortStatic() {

        System.out.println("┌──────────────────────┬──────────────────────┐");
        System.out.printf ("│ %-20s │ %20s │%n", "Count", count);
        System.out.println("└──────────────────────┴──────────────────────┘");
    }


    private void printRow(String value) {
        final int MAX_WIDTH = 20;
        String label="Longest string";

        if (value == null) {
            value = "N/A";
        }

        if (value.length() <= MAX_WIDTH) {
            System.out.printf("│ %-20s │ %20s │%n", label, value);
        } else {
            System.out.printf("│ %-20s │ %20s │%n", label, value.substring(0, MAX_WIDTH));

            String secondPart = value.substring(MAX_WIDTH, Math.min(value.length(), MAX_WIDTH * 2));
            System.out.printf("│ %-20s │ %20s │%n", "", secondPart);
            
            if (value.length() > MAX_WIDTH * 2) {
                System.out.printf("│ %-20s │ %20s │%n", "", "...");
            }
        }
    }

}
