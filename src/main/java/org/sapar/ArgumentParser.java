package org.sapar;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {
    private boolean shortStats = false;
    private boolean fullStats = false;
    private boolean appendMode = false;
    private String outputPath = "";
    private String prefix = "";
    private List<String> inputFiles = new ArrayList<>();

    public ArgumentParser(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s":
                    shortStats = true;
                    break;
                case "-f":
                    fullStats = true;
                    break;
                case "-a":
                    appendMode = true;
                    break;
                case "-o":
                    if (i + 1 < args.length) {
                        outputPath = args[++i];
                    } else {
                        System.err.println("Error: Missing value for -o flag.");
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        prefix = args[++i];
                    } else {
                        System.err.println("Error: Missing value for -p flag.");
                    }
                    break;
                default:
                    // Anything else is considered a file
                    inputFiles.add(args[i]);
                    break;
            }
        }

        // Validation: At least one stat mode should be selected
        if (!shortStats && !fullStats) {
            shortStats = true; // Default to short stats if none provided
        }
    }

    public boolean isShortStats() {
        return shortStats;
    }

    public boolean isFullStats() {
        return fullStats;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }
}
