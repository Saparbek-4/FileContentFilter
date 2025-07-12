package org.sapar;

import java.util.ArrayList;
import java.util.List;

/**
 * Command-line argument parser that supports:
 * - Output directory (-o)
 * - File name prefix (-p)
 * - Append mode (-a)
 * - Short or full statistics (-s / -f)
 * - List of input file names
 */
public class ArgumentParser {

    private boolean shortStats = false;
    private boolean fullStats = false;
    private boolean appendMode = false;
    private String outputPath = "";
    private String prefix = "";
    private final List<String> inputFiles = new ArrayList<>();

    public ArgumentParser(String[] args) {
        System.out.println("Parsing arguments...");
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-s" -> shortStats = true;
                case "-f" -> fullStats = true;
                case "-a" -> appendMode = true;
                case "-o" -> {
                    if (i + 1 < args.length) outputPath = args[++i];
                    else System.err.println("Missing value after -o (output path).");
                }
                case "-p" -> {
                    if (i + 1 < args.length) prefix = args[++i];
                    else System.err.println("Missing value after -p (file prefix).");
                }
                default -> inputFiles.add(args[i]);
            }
        }

        // Default if nothing specified
        if (!shortStats && !fullStats) {
            shortStats = true;
        }

        System.out.println("Input files to process: " + inputFiles);
    }

    public boolean isShortStats() { return shortStats; }

    public boolean isFullStats() { return fullStats; }

    public boolean isAppendMode() { return appendMode; }

    public String getOutputPath() { return outputPath; }

    public String getPrefix() { return prefix; }

    public List<String> getInputFiles() { return inputFiles; }
}
