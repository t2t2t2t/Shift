package com.filter.io;

import com.filter.statistics.Statistics;
import com.filter.core.ApplicationOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileReader {

    public static void loadData(Statistics statistics ) {
        List<String> files = ApplicationOptions.getInstance().getInputFiles();

        FileWriter fw = new FileWriter();

        for (int i = 0; i < files.size(); ++i) {

            String file = files.get(i);
            File filePath = new File(file);

            if (!filePath.exists()) {
                System.out.println(filePath.getAbsolutePath() + " does not exist");
                continue;
            }
            String line;
            try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {

                while ((line = reader.readLine()) != null) {
                    statistics.add(line);
                    fw.writeFile(line);
                }

            } catch (IOException e) {
                System.err.println("Error :" + filePath.getAbsolutePath());
            }

        }
        fw.close();
    }

}
