package org.sapar;

import java.io.*;
import java.util.List;

/**
 * Entry point of the FileContentFilter application.
 * Written with ❤️ by Saparbek
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Starting FileContentFilter...");

        ArgumentParser parser = new ArgumentParser(args);
        FileWriterManager writerManager = new FileWriterManager(
                parser.getOutputPath(),
                parser.getPrefix(),
                parser.isAppendMode()
        );
        StatisticsCollector stats = new StatisticsCollector();

        List<String> inputFiles = parser.getInputFiles();
        if (inputFiles.isEmpty()) {
            System.err.println("Error: No input files specified.");
            System.exit(1);
        }

        // Main file loop
        for (String fileName : inputFiles) {
            System.out.println("Reading from file: " + fileName);
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    try {
                        if (DataTypeDetector.isInteger(line)) {
                            writerManager.writeInteger(line);
                            stats.recordInteger(Long.parseLong(line));
                        } else if (DataTypeDetector.isFloat(line)) {
                            writerManager.writeFloat(line);
                            stats.recordFloat(Double.parseDouble(line));
                        } else {
                            writerManager.writeString(line);
                            stats.recordString(line);
                        }
                    } catch (IOException ioEx) {
                        System.err.println("Failed to write line: \"" + line + "\" — " + ioEx.getMessage());
                    }
                }
            } catch (IOException ex) {
                System.err.println("Cannot read file: " + fileName + " — " + ex.getMessage());
            }
        }

        writerManager.closeAll();

        System.out.println("\nStatistics:");
        if (parser.isShortStats()) stats.printShortStatistics();
        else stats.printFullStatistics();

        System.out.println("\nDone. Filtered output written in txt files");
    }
}
