package org.sapar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser(args);
        FileWriterManager writerManager = new FileWriterManager(
                parser.getOutputPath(),
                parser.getPrefix(),
                parser.isAppendMode()
        );
        StatisticsCollector statsCollector = new StatisticsCollector();

        List<String> inputFiles = parser.getInputFiles();
        if (inputFiles.isEmpty()) {
            System.err.println("Error: No input files specified.");
            System.exit(1);
        }

        for (String fileName : inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) {
                        continue; // skip empty lines
                    }
                    try {
                        if (DataTypeDetector.isInteger(line)) {
                            writerManager.writeInteger(line);
                            statsCollector.recordInteger(Long.parseLong(line));
                        } else if (DataTypeDetector.isFloat(line)) {
                            writerManager.writeFloat(line);
                            statsCollector.recordFloat(Double.parseDouble(line));
                        } else {
                            writerManager.writeString(line);
                            statsCollector.recordString(line);
                        }
                    } catch (IOException e) {
                        System.err.println("Error writing line: \"" + line + "\" from file " + fileName + " - " + e.getMessage());
                        // Continue processing other lines
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + fileName + " - " + e.getMessage());
                // Continue processing other files
            }
        }

        // Close all writers
        writerManager.closeAll();

        // Print statistics
        if (parser.isShortStats()) {
            statsCollector.printShortStatistics();
        } else if (parser.isFullStats()) {
            statsCollector.printFullStatistics();
        }

        System.out.println("Processing completed.");
    }
}
