package org.sapar;

public class StatisticsCollector {
    // Integers
    private long intCount = 0;
    private long intMin = Long.MAX_VALUE;
    private long intMax = Long.MIN_VALUE;
    private long intSum = 0;

    // Floats
    private long floatCount = 0;
    private double floatMin = Double.MAX_VALUE;
    private double floatMax = -Double.MAX_VALUE;
    private double floatSum = 0.0;

    // Strings
    private long stringCount = 0;
    private int minStringLength = Integer.MAX_VALUE;
    private int maxStringLength = Integer.MIN_VALUE;

    // Record
    public void recordInteger(long value) {
        intCount++;
        intMin = Math.min(intMin, value);
        intMax = Math.max(intMax, value);
        intSum += value;
    }

    public void recordFloat(double value) {
        floatCount++;
        floatMin = Math.min(floatMin, value);
        floatMax = Math.max(floatMax, value);
        floatSum += value;
    }

    public void recordString(String value) {
        stringCount++;
        int len = value.length();
        minStringLength = Math.min(minStringLength, len);
        maxStringLength = Math.max(maxStringLength, len);
    }

    // Print
    public void printShortStatistics() {
        System.out.println("=== Short Statistics ===");
        System.out.println("Integers count: " + intCount);
        System.out.println("Floats count: " + floatCount);
        System.out.println("Strings count: " + stringCount);
    }

    public void printFullStatistics() {
        System.out.println("=== Full Statistics ===");

        // Integers
        System.out.println("Integers count: " + intCount);
        if (intCount > 0) {
            System.out.println("Min: " + intMin);
            System.out.println("Max: " + intMax);
            System.out.println("Sum: " + intSum);
            System.out.println("Average: " + (intSum / (double) intCount));
        }

        // Floats
        System.out.println("Floats count: " + floatCount);
        if (floatCount > 0) {
            System.out.println("Min: " + floatMin);
            System.out.println("Max: " + floatMax);
            System.out.println("Sum: " + floatSum);
            System.out.println("Average: " + (floatSum / floatCount));
        }

        // Strings
        System.out.println("Strings count: " + stringCount);
        if (stringCount > 0) {
            System.out.println("Shortest length: " + minStringLength);
            System.out.println("Longest length: " + maxStringLength);
        }
    }
}
