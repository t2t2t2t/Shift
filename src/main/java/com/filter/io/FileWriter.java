package com.filter.io;

import com.filter.core.DataType;
import com.filter.core.ApplicationOptions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class FileWriter implements AutoCloseable {
    private final Map<DataType, BufferedWriter> writerMap = new EnumMap<>(DataType.class);

    private final ApplicationOptions options = ApplicationOptions.getInstance();

    private boolean isClosed = false;

    public void writeFile(String str) {


        if (isClosed) {
            System.out.println("Resource is closed or null input");
            return;
        }

        String path = options.getOutputPath();
        String separator = path.isEmpty() ? "" : File.separator;

        boolean append = options.isAppendMode();
        DataType dataType = DataType.detentionType(str);

        String nameFile = options.getFilenamePrefix() + dataType.getFileName();
        String fullPath = path + separator + nameFile;



        if (!writerMap.containsKey(dataType)) {
            try {
                synchronized (writerMap) {
                    writerMap.put(dataType, new BufferedWriter(new java.io.FileWriter(fullPath, append)));
                }
            } catch (IOException e) {
                System.out.println("Don`t find path: " + fullPath);
            }
        }

        try {
            synchronized (writerMap) {
                writerMap.get(dataType).write(str + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error writing to:  " + fullPath);
        }

    }

    @Override
    public void close() {
        if (isClosed) {
            return;
        }
        isClosed = true;
        for (BufferedWriter bf : writerMap.values()) {
            try {
                synchronized (bf) {
                    bf.close();
                }
            } catch (IOException e) {
                System.out.println("Close error: " + e.getMessage());
            }
        }
    }
}


