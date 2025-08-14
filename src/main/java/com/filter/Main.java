package com.filter;


import com.filter.statistics.Statistics;
import com.filter.core.ApplicationOptions;
import com.filter.io.FileReader;

public class Main {

    public static void main(String[] args) {
        ApplicationOptions.parse(args);

        Statistics statistics = new Statistics();
        FileReader.loadData(statistics);
        if (ApplicationOptions.getInstance().isShortStatistics()){
            statistics.shortStatistics();
        }
        if (ApplicationOptions.getInstance().isFullStatistics()){
            statistics.longStatistics();
        }

    }
}