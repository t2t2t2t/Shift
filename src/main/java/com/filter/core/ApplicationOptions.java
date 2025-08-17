package com.filter.core;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApplicationOptions {

    private static ApplicationOptions instance;

    public ApplicationOptions() {
    }
    public static ApplicationOptions getInstance() {
        if (instance == null) {
            instance = new ApplicationOptions();
        }
        return instance;
    }

    @Parameter(names = {"-o", "--output"},
            description = "Output directory path")
    private String outputPath = ".";

    @Parameter(names = {"-p", "--prefix"},
            description = "Prefix for output filenames")
    private String filenamePrefix = "";

    @Parameter(names = {"-a", "--append"},
            description = "Append to existing files",
            arity = 0)
    private boolean appendMode = false;

    @Parameter(names = {"-s", "--short-stat"},
            description = "Show short statistics",
            arity = 0)
    private boolean shortStatistics = false;

    @Parameter(names = {"-f", "--full-stat"},
            description = "Show full statistics",
            arity = 0)
    private boolean fullStatistics = false;

    @Parameter(description = "Input files")
    private List<String> inputFiles = new ArrayList<>();

    public static ApplicationOptions parse(String[] args) {
        ApplicationOptions options = getInstance();

        JCommander jCommander = JCommander.newBuilder().addObject(options).build();

        jCommander.parse(args);


        if (options.inputFiles.isEmpty()) {
            System.out.println("Не указаны входные файлы");
        }

        String separator = options.outputPath.isEmpty() ? "" : File.separator;
        String fullPath = options.outputPath + separator;

        if (!fullPath.isEmpty()) {
            File outputDir = new File(fullPath);
            if (!outputDir.exists()) {
                if (outputDir.mkdirs()){
                    System.out.println("Create directory: " + fullPath);
                }
                else {
                    System.out.println("Create directory: " + fullPath + " failed");
                }
            }
        }

        return options;

    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getFilenamePrefix() {
        return filenamePrefix;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public boolean isShortStatistics() {
        return shortStatistics;
    }

    public boolean isFullStatistics() {
        return fullStatistics;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }
}
